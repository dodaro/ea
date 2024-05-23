package it.unical.demacs.informatica.eacontacts2024.viewmodels

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import it.unical.demacs.informatica.eacontacts2024.model.AppDatabase
import it.unical.demacs.informatica.eacontacts2024.model.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.LinkedList

/*fun generateRandomFirstName(): String {
    val names = listOf(
        "Marco", "Giulia", "Francesco", "Sara", "Luca",
        "Chiara", "Alessandro", "Laura", "Matteo", "Valentina",
        "Davide", "Elena", "Simone", "Alice", "Giovanni",
        "Martina", "Andrea", "Beatrice", "Filippo", "Elisa",
        "Lorenzo", "Federica", "Riccardo", "Michela", "Tommaso",
        "Cristina", "Alessio", "Silvia", "Giacomo", "Roberta",
        "Michele", "Giorgia", "Stefano", "Valeria", "Antonio",
        "Erika", "Pietro", "Serena", "Enrico", "Daniela",
        "Salvatore", "Jessica", "Angelo", "Monica", "Claudio",
        "Sofia", "Giuseppe", "Camilla", "Rocco", "Linda"
    )

    return names.random()
}

fun generateRandomLastName(): String {
    val surnames = listOf(
        "Rossi", "Bianchi", "Ferrari", "Russo", "Martini",
        "Romano", "Ricci", "Gallo", "Conti", "Esposito",
        "Ricciardi", "Rinaldi", "Leone", "Bruno", "Barbieri",
        "Marino", "Galli", "Santoro", "Greco", "Pellegrini",
        "Fabbri", "Moretti", "Caruso", "De Luca", "Giordano",
        "Vitale", "Serra", "Coppola", "Lombardi", "Farina",
        "Martino", "Longo", "Mancini", "Riva", "Donati",
        "Costa", "Mazza", "Sala", "Fiore", "Pagano",
        "Villa", "De Santis", "Gatti", "Bellini", "Rossetti",
        "Ferri", "Grassi", "Lombardo", "Piras", "Battaglia"
    )

    return surnames.random()
}

fun generateRandomPhoneNumber(): String {
    val countryCode = "+39"
    val pre = listOf("320", "328", "329", "347", "348", "388")
    val number = (1000000..9999999).random()
    return "$countryCode ${pre[Random.nextInt(0, pre.size)]} $number"
}*/


class ContactViewModel(application: Application) : ViewModel() {
    private val _application = application
    val contacts : Flow<List<Contact>> = AppDatabase.getInstance(context = application.applicationContext).contactDao().getAllContacts()
    private val _searchList: MutableList<String> = LinkedList()
    val searchList: List<String> = _searchList
    /*init {
        repeat(100) {// Generate random contacts
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(context = application.applicationContext).contactDao().insert(
                    Contact(
                        firstName = generateRandomFirstName(),
                        lastName = generateRandomLastName(),
                        phoneNumber = generateRandomPhoneNumber()
                    )
                )
            }
        }
    }*/

    fun addContact(firstName: String, lastName: String, phoneNumber: String, image: Bitmap?): Boolean { // Add contact
        return try {
            val contact = Contact(firstName = firstName, lastName = lastName, phoneNumber = phoneNumber, image = image)
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(context = _application.applicationContext).contactDao().insert(contact = contact)
            }
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getContact(id: String) : Flow<Contact?> {
        return AppDatabase.getInstance(context = _application.applicationContext).contactDao().getContactById(id)
    }

    fun removePreferred(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contact.preferred = false
            AppDatabase.getInstance(context = _application.applicationContext).contactDao().update(contact = contact)
        }
    }

    fun setPreferred(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contact.preferred = true
            AppDatabase.getInstance(context = _application.applicationContext).contactDao().update(contact = contact)
        }
    }

    fun addSearchList(search: String) {
        if(!_searchList.contains(search))
            _searchList.add(search)
        if(_searchList.size > 5)
            _searchList.removeFirst()
    }
}