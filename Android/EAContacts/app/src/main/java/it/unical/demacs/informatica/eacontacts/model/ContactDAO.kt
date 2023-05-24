package it.unical.demacs.informatica.eacontacts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO {

    @Query("select * from contacts order by last_name, first_name")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("select * from contacts where id=:id")
    fun getContactById(id: String): Flow<Contact?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)
}