package it.unical.demacs.informatica.eacontacts.model

import android.graphics.Bitmap
import java.util.UUID

class Contact(val id: String = UUID.randomUUID().toString(),
              val firstName: String,
              val lastName: String,
              val phoneNumber: String,
              var preferred: Boolean = false,
              val image: Bitmap? = null) {
    init {
        if (!validateFirstName(firstName))
            throw IllegalArgumentException("First name cannot be empty and greater than 30 characters")
        if (!validateLastName(lastName))
            throw IllegalArgumentException("Last name cannot be empty and greater than 30 characters")
        if (!validatePhoneNumber(phoneNumber))
            throw IllegalArgumentException("Phone number cannot be empty")
    }

    // A "way" to make static methods. A companion object is bounded to the Contact class
    // and the internal methods can be accessed by using the notation Contact.validate...
    companion object {
        fun validateFirstName(firstName: String): Boolean {
            if (firstName.length in 1..30)
                return true
            return false
        }

        fun validateLastName(lastName: String): Boolean {
            if (lastName.length in 1..30)
                return true
            return false
        }

        fun validatePhoneNumber(phoneNumber: String): Boolean {
            if (phoneNumber.isNotEmpty())
                return true
            return false
        }
    }
}