package it.unical.demacs.informatica.eacontacts2024

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.unical.demacs.informatica.eacontacts2024.model.Contact
import it.unical.demacs.informatica.eacontacts2024.ui.theme.Typography

@Composable
fun SingleContact(contact: Contact) {
    // Single contact is shown as a card
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp), modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Column {
            // Inside the column, we have a row with the image of the contact,
            // a text with the name of the contact,
            // another row with the phone number,
            // another row with two buttons for calling and send a sms

            // The row with the image is centered horizontally and vertically
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                if(contact.image == null) { // If the image is null we show a default image
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = stringResource(R.string.defaultAccount),
                        modifier = Modifier
                            .size(128.dp)
                            .padding(5.dp)
                    )
                }
                else {
                    Image(
                        bitmap = contact.image.asImageBitmap(),
                        contentDescription = "${stringResource(R.string.imageOf)} ${contact.firstName} ${contact.lastName}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(128.dp).clip(CircleShape).padding(5.dp)
                    )
                }
            }
            // Show first name and last name of the contact
            Text(text = "${contact.firstName} ${contact.lastName}", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = TextStyle(fontSize = Typography.titleLarge.fontSize))
            // Show the row with the phone number
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Text(text = "${stringResource(id = R.string.phoneNumberLabel)}:", modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = contact.phoneNumber)
            }
            // Make a phone call or send a sms
            Row (modifier = Modifier.padding(20.dp)){
                val context = LocalContext.current
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contact.phoneNumber, null))
                    try {
                        context.startActivity(intent)
                    } catch (_: SecurityException) {
                    }
                }) {
                    Icon(Icons.Filled.Phone, contentDescription = "Phone number", modifier = Modifier.size(20.dp))
                }
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact.phoneNumber, null))
                    try {
                        context.startActivity(intent)
                    } catch (_: SecurityException) {
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Phone number", modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}