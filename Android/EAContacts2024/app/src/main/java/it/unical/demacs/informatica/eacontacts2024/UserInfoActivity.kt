package it.unical.demacs.informatica.eacontacts2024

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import it.unical.demacs.informatica.eacontacts2024.ui.theme.Typography
import it.unical.demacs.informatica.eacontacts2024.viewmodels.UserInfoViewModel

@Composable
fun UserInfo(userInfoViewModel: UserInfoViewModel) {
    val applicationContext = LocalContext.current
    val showMap = remember { mutableStateOf(false) }
    val userInfoDataState by userInfoViewModel.userInfoData.collectAsState()
    // val userInfoDataState by userInfoViewModel.userInfoDataFlow.collectAsState(initial = UserInfoData(error = true))
    val modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    Spacer(modifier = modifier)
    if(showMap.value) {
        WebViewScreen(latitude = userInfoDataState.latitude, longitude = userInfoDataState.longitude)
    }
    else {
        Column {
           if (!userInfoDataState.error) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                    modifier = modifier
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.userInfo),
                            style = TextStyle(fontSize = Typography.titleLarge.fontSize),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp)
                        )
                        Text(text = userInfoDataState.countryCode, modifier = modifier)
                        Text(text = userInfoDataState.region, modifier = modifier)
                        Row {
                            Text(
                                text = "${userInfoDataState.latitude},${userInfoDataState.longitude}",
                                modifier = modifier.weight(1f),
                            )
                            IconButton(
                                onClick = { showMap.value = true },
                            ) {
                                Icon(Icons.Filled.Map, contentDescription = stringResource(id = R.string.showMap))
                            }
                            IconButton(
                                onClick = { openChromeTabView(applicationContext, userInfoDataState.latitude, userInfoDataState.longitude); },
                            ) {
                                Icon(Icons.Filled.LocationOn, contentDescription = stringResource(id = R.string.showGoogleMap))
                            }
                        }
                        Text(text = userInfoDataState.city, modifier = modifier)
                        Text(text = userInfoDataState.timeZone, modifier = modifier)
                        Text(text = userInfoDataState.ip, modifier = modifier)
                    }
                }
            }
            // If we use flow, this is not needed
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.Center) {
                Button(
                    onClick = { userInfoViewModel.retrieveUserInfo() },
                ) {
                    Text(text = stringResource(id = R.string.updateUserInfo))
                }
            }
        }
    }
}

@Composable
fun WebViewScreen(latitude: Double, longitude: Double) {
    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                this.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
            }
        },
        update = { web ->
            web.loadUrl("https://www.openstreetmap.org/#map=6/${latitude}/${longitude}")
        }
    )
}

fun openChromeTabView(context: Context, latitude: Double, longitude: Double) {
    CustomTabsIntent.Builder().build().launchUrl(context,
        "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude".toUri())
}