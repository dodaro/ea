package it.unical.demacs.informatica.eacontacts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.unical.demacs.informatica.eacontacts.viewmodels.ContactViewModel
import it.unical.demacs.informatica.eacontacts.ui.theme.Typography

@Composable
fun FavoritesContacts(contactViewModel: ContactViewModel) {
    val preferred = contactViewModel.contacts.collectAsState(initial = emptyList()).value.filter { it.preferred  }
    if(preferred.isNotEmpty()) {
        LazyColumn {
            items(preferred) { contact ->
                key(contact.id) {
                    SingleContact(contact = contact)
                }
            }
        }
    }
    else {
        Text(text = stringResource(R.string.noPreferred), style = TextStyle(fontSize = Typography.titleLarge.fontSize), textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 80.dp))
    }
}