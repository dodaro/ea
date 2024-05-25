package it.unical.demacs.informatica.eacontacts

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.unical.demacs.informatica.eacontacts.ui.theme.Typography
import it.unical.demacs.informatica.eacontacts.viewmodels.ContactFormViewModel
import it.unical.demacs.informatica.eacontacts.viewmodels.ContactViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactActivity(onDismissRequest : () -> Unit, contactViewModel: ContactViewModel, setBottomSheet: (Boolean) -> Unit, contactFormViewModel: ContactFormViewModel = ContactFormViewModel()) {
    val contactState by contactFormViewModel.contactState.collectAsState() // To keep the reference to all fields of the form
    val showError by remember { derivedStateOf { contactState.isFirstNameError || contactState.isLastNameError || contactState.isPhoneNumberError } } // Has to show an error?
    val sheetState = rememberModalBottomSheetState() // State of the component ModalBottom
    val scope = rememberCoroutineScope() // Scope to dispose the ModalBottom
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { contactFormViewModel.updateBitmap(it) } // Take a picture
    // To use this component you need to update the libraries to the latest version
    ModalBottomSheet(onDismissRequest = onDismissRequest, sheetState = sheetState) {
        // We define a column where elements are aligned to the center (horizontalAlignment) e arranged to the top of the screen (verticalArrangement)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            val commonModifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth() // Define a common modifier
            // Title of the page
            Text(text = stringResource(R.string.addContact),
                 style = TextStyle(fontSize = Typography.headlineSmall.fontSize),
                 textAlign = TextAlign.Center,
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(horizontal = 0.dp, vertical = 10.dp)
            )
            // Box containing the contact image or a default icon if the image is not available
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                contactState.bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = stringResource(R.string.contactImage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape) // We show the image in a circle
                            .padding(5.dp)
                    )
                } ?:
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.noImage),
                    modifier = Modifier
                        .size(128.dp)
                        .padding(5.dp)
                )
                // Here we have a card with the + button to upload the image
                Card(modifier=Modifier.align(Alignment.BottomCenter)) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(R.string.loadImageButton),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp)
                            .clickable { launcher.launch() /* here we launch the code to take a picture */ }
                    )
                }
            }
            // Field containing the first name
            OutlinedTextField(value = contactState.firstName,
                              onValueChange = { contactFormViewModel.updateFirstName(it) },
                              label = { Text(stringResource(R.string.firstNameLabel)) },
                              isError = contactState.isFirstNameError,
                              leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(R.string.personIcon)) },
                              modifier = commonModifier
            )
            // Field containing the last name
            OutlinedTextField(value = contactState.lastName,
                              onValueChange = { contactFormViewModel.updateLastName(it) },
                              label = { Text(stringResource(R.string.lastNameLabel)) },
                              isError = contactState.isLastNameError,
                              leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(R.string.personIcon)) },
                              modifier = commonModifier
            )
            // Field containing the phone number
            OutlinedTextField(value = contactState.phoneNumber,
                              onValueChange = { contactFormViewModel.updatePhoneNumber(it) },
                              label = { Text(stringResource(R.string.phoneNumberLabel)) },
                              isError = contactState.isPhoneNumberError,
                              leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = stringResource(R.string.phoneIcon)) },
                              modifier = commonModifier
            )
            // Show the error if the fields are not correct
            if(showError) {
                Text(text = stringResource(R.string.emptyError))
            }
            Button(modifier = Modifier.padding(20.dp), onClick = {
                // Check if the contact passes the validation
                if (contactViewModel.addContact(firstName = contactState.firstName, lastName = contactState.lastName, phoneNumber = contactState.phoneNumber, image = contactState.bitmap)) {
                    // If so, we can hide the bottom sheet
                    if (sheetState.isVisible) {
                        scope.launch {
                            sheetState.hide()
                            setBottomSheet(false)
                        }
                    }
                }
            }) {
                Text(stringResource(R.string.addContactButton))
            }
        }
    }
}