package it.unical.demacs.informatica.eacontacts.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import it.unical.demacs.informatica.eacontacts.model.Contact
import kotlin.random.Random

fun generateRandomFirstName(): String {
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
}

class ContactViewModel : ViewModel() {

    private val _list : SnapshotStateList<Contact> = mutableStateListOf() // All contacts
    private val _map : MutableMap<String, Contact> = mutableMapOf() // Map contact id to contact
    var groups: Map<Char, List<Contact>> = mapOf() // Map first letter to list of contacts (needed for sticky headers)
    val contacts = _list // Exposed contacts

    init {
        repeat(100) {// Generate random contacts
            _list.add(Contact(firstName = generateRandomFirstName(), lastName = generateRandomLastName(), phoneNumber = generateRandomPhoneNumber()))
        }
        for(c: Contact in _list) { // Add contacts in the map
            _map[c.id] = c
        }
        sortAndRefreshGroups() // Sort to show contacts in alphabetical way
    }

    private fun sortAndRefreshGroups() {
        _list.sortWith(compareBy<Contact>{it.lastName}.thenBy { it.firstName}) // Sort contacts
        groups = _list.groupBy { // Create groups
            if(it.lastName.isNotEmpty())
                it.lastName[0]
            else
                ' '
        }
    }

    fun addContact(firstName: String, lastName: String, phoneNumber: String, image: Bitmap?): Boolean { // Add contact
        return try {
            val contact = Contact(firstName = firstName, lastName = lastName, phoneNumber = phoneNumber, image = image)
            _map[contact.id] = contact
            _list.add(contact)
            sortAndRefreshGroups() // After each addition we sort the entire list: this can be done in a more efficient way!
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getContact(id: String) : Contact? {
        return _map[id]
    }

    fun removePreferred(contact: Contact) {
        contact.preferred = false
    }

    fun setPreferred(contact: Contact) {
        contact.preferred = true
    }
}