package it.unical.demacs.informatica.eacontacts2024.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

data class UserInfoData(
    val countryCode: String = "",
    val region: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val city: String = "",
    val timeZone: String = "",
    val ip: String = "",
    val error: Boolean = false
)

class UserInfoViewModel : ViewModel() {
    private val _userInfoData = MutableStateFlow(UserInfoData())
    val userInfoData: StateFlow<UserInfoData> = _userInfoData.asStateFlow()

    init {
        retrieveUserInfo()
    }

    fun retrieveUserInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://get.geojs.io/v1/ip/geo.json")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            try {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                val jsonObject = JSONObject(response)
                _userInfoData.value = _userInfoData.value.copy(
                    countryCode = jsonObject.getString("country_code"),
                    region = jsonObject.getString("region"),
                    latitude = jsonObject.getDouble("latitude"),
                    longitude = jsonObject.getDouble("longitude"),
                    city = jsonObject.getString("city"),
                    timeZone = jsonObject.getString("timezone"),
                    ip = jsonObject.getString("ip"),
                    error = false)
            } catch (e: Exception) {
                _userInfoData.value = _userInfoData.value.copy(error = true)
            }
        }
    }

    /*** Alternative approach to retrieve user info every 10 seconds ****/
    val userInfoDataFlow: Flow<UserInfoData> = automaticRetrieve()

    private fun automaticRetrieve() : Flow<UserInfoData> {
        return flow {
            while (true) {
                val userInfo = getUserInfo()
                emit(userInfo)
                delay(10000)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun getUserInfo() : UserInfoData {
        val url = URL("https://get.geojs.io/v1/ip/geo.json")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        try {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()
            val jsonObject = JSONObject(response)
            return UserInfoData(
                countryCode = jsonObject.getString("country_code"),
                region = jsonObject.getString("region"),
                latitude = jsonObject.getDouble("latitude"),
                longitude = jsonObject.getDouble("longitude"),
                city = jsonObject.getString("city"),
                timeZone = jsonObject.getString("timezone"),
                ip = jsonObject.getString("ip"),
                error = false)
        } catch (e: Exception) {
            return UserInfoData(error = true)
        }
    }


}