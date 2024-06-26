package it.unical.demacs.informatica.eacontacts2024

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import it.unical.demacs.informatica.eacontacts2024.viewmodels.ContactViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AllContacts(contactViewModel: ContactViewModel, navHostController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    val previousQueries by remember { mutableStateOf(contactViewModel.searchList) }
    var expanded by remember { mutableStateOf(false) }
    val myContacts = contactViewModel.contacts.collectAsState(initial = emptyList()).value.filter {
        if(searchText.trim().isEmpty())
            true
        else {
            var match = true
            val res = searchText.trim().split(regex = "(\\s)+".toRegex())
            for(part in res) {
                if(!it.firstName.contains(part, ignoreCase = true) &&
                !it.lastName.contains(part, ignoreCase = true)) {
                    match = false
                    break
                }
            }
            match
        }
    }
    LaunchedEffect(key1 = searchText) {
        if(searchText.isNotEmpty()) {
            delay(3000L)
            contactViewModel.addSearchList(searchText)
        }
    }

    // Lazy Column to show all the contacts
    LazyColumn(modifier = Modifier.padding(all = 3.dp)) {
        item {
            // Search bar with dropdown menu
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                TextField(
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    value = searchText,
                    onValueChange = { searchText = it; expanded = it.isNotEmpty() && contactViewModel.searchList.isNotEmpty() },
                    placeholder = { Text(text = stringResource(R.string.search)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                DropdownMenu(
                    expanded = expanded && contactViewModel.searchList.isNotEmpty(),
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(),
                    properties = PopupProperties(focusable = false)
                ) {
                    previousQueries.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = label) },
                            onClick = { searchText = label; expanded = false })
                    }
                }
            }
        }
        // Here sticky header is a way to create the blocked header with the letters of the contact
        // Group by is a map of a Char to a list of contacts
        myContacts.groupBy {
            if(it.lastName.isNotEmpty())
                it.lastName[0]
            else
                ' '
        }.forEach { (firstLetter, contacts) ->
            // For each element in the group, we show (1) the sticky header
            stickyHeader {
                // This is the card containing the single letter
                Card(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Row {
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "$firstLetter")
                    }
                }
            }
            // and (2) all contacts associated to the group
            items(contacts) { contact ->
                key(contact.id) { // Let's make the recomposition more efficient
                    // This a row containing all the Contacts information
                    Row(modifier = Modifier
                        .padding(20.dp)
                        .pointerInput(Unit) {
                            // Here with a double tap on the row we navigate on the screen of the contact
                            detectTapGestures(onDoubleTap = {
                                navHostController.navigate("allContacts/${contact.id}")
                            })
                        }) {
                        // Text containing first name and last name
                        Text(
                            text = "${contact.firstName} ${contact.lastName}",
                            modifier = Modifier.weight(1f)  // The text takes all the space it can
                        )
                        // Remember if the contact is preferred
                        var contactPreferred by rememberSaveable { mutableStateOf(contact.preferred) }
                        if (contactPreferred)
                            Icon(
                                Icons.Filled.Favorite, // Let's show the filled icon
                                contentDescription = stringResource(R.string.removeFromPreferred),
                                modifier = Modifier.clickable {
                                    contactPreferred = false; contactViewModel.removePreferred(contact) // Remove contact from preferred
                                })
                        else
                            Icon(
                                Icons.Filled.FavoriteBorder, // Let's show the empty icon
                                contentDescription = stringResource(R.string.addToPreferred),
                                modifier = Modifier.clickable {
                                    contactPreferred = true; contactViewModel.setPreferred(contact) // Add contact to preferred
                                })
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.info),
                            modifier = Modifier.clickable { navHostController.navigate("allContacts/${contact.id}") }) // Here with a click on the info button we navigate on the screen of the contact
                    }
                    HorizontalDivider() // Add a divider between each contact
                }
            }
        }
    }
}