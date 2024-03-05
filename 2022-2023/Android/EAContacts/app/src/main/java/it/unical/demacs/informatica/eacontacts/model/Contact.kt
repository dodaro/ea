package it.unical.demacs.informatica.eacontacts.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.ByteArrayOutputStream
import java.util.UUID


class BitmapConverter {
    @TypeConverter
    fun convertBitmap2ByteArray(bitmap: Bitmap) : ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun convertByteArray2Bitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}


@TypeConverters(BitmapConverter::class)
@Entity(tableName = "contacts")
class Contact(@PrimaryKey val id: String = UUID.randomUUID().toString(),
              @ColumnInfo(name="first_name") val firstName: String,
              @ColumnInfo(name="last_name") val lastName: String,
              @ColumnInfo(name="phone_number") val phoneNumber: String,
              @ColumnInfo(name="preferred") var preferred: Boolean = false,
              @ColumnInfo(name="image", typeAffinity = ColumnInfo.BLOB) val image: Bitmap? = null) {
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