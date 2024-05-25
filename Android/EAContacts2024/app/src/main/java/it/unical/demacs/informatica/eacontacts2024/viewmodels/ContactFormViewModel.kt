package it.unical.demacs.informatica.eacontacts2024.viewmodels

import android.graphics.Bitmap
import it.unical.demacs.informatica.eacontacts2024.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// All data of a contact
data class ContactState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val bitmap: Bitmap? = null,
    val isFirstNameError: Boolean = !Contact.validateFirstName(firstName = firstName),
    val isLastNameError: Boolean = !Contact.validateLastName(lastName = lastName),
    val isPhoneNumberError: Boolean = !Contact.validatePhoneNumber(phoneNumber = phoneNumber)
)

// View Model containing elements for the form to add contacts
class ContactFormViewModel {
    private val _contactState = MutableStateFlow(ContactState())
    val contactState: StateFlow<ContactState> = _contactState.asStateFlow()

    fun updateFirstName(firstName: String) {
        // When the first name is updated, then we also check if there is an error
        val hasError = !Contact.validateFirstName(firstName = firstName)
        _contactState.value = _contactState.value.copy(
            firstName = firstName,
            isFirstNameError = hasError
        )
    }

    fun updateLastName(lastName: String) {
        // When the last name is updated, then we also check if there is an error
        val hasError = !Contact.validateLastName(lastName = lastName)
        _contactState.value = _contactState.value.copy(
            lastName = lastName,
            isLastNameError = hasError
        )
    }

    fun updatePhoneNumber(phoneNumber: String) {
        // When the phone number is updated, then we also check if there is an error
        val hasError = !Contact.validatePhoneNumber(phoneNumber = phoneNumber)
        _contactState.value = _contactState.value.copy(
            phoneNumber = phoneNumber,
            isPhoneNumberError = hasError
        )
    }

    fun updateBitmap(bitmap: Bitmap?) {
        _contactState.value = _contactState.value.copy(
            bitmap = bitmap
        )
    }
}