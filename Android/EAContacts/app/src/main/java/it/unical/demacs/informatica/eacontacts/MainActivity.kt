package it.unical.demacs.informatica.eacontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import it.unical.demacs.informatica.eacontacts.ui.theme.EAContactsTheme
import it.unical.demacs.informatica.eacontacts.viewmodels.ContactViewModel

class MainActivity : ComponentActivity() {
    private val contactViewModel: ContactViewModel = ContactViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EAContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(contactViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationView(navHostController: NavHostController, contactViewModel: ContactViewModel) {
    NavHost(navController = navHostController, startDestination = "allContacts") {
        composable("allContacts") {
            AllContacts(contactViewModel = contactViewModel, navHostController = navHostController)
        }
        composable("allContacts/{id}", arguments =
        listOf(navArgument("id") { type = NavType.StringType})) {
            it.arguments?.getString("id")?.let {
                id ->
                    contactViewModel.getContact(id)?.let {contact ->
                        SingleContact(contact = contact)
                    }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTopBar(navHostController: NavHostController) {
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val showBackIcon by remember(currentBackStackEntry) { derivedStateOf { navHostController.previousBackStackEntry != null } }
    TopAppBar(title = { Text(stringResource(R.string.app_name))},
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, contentDescription = stringResource(R.string.settings))
            }
        }
    )
}

@Composable
fun ContactBottomBar(selectedIndex: MutableState<Int>) {
    BottomAppBar {
        NavigationBar {
            NavigationBarItem(selected = selectedIndex.value == 0, onClick = { selectedIndex.value = 0 }, icon = {
                Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.home)) }
            )
            NavigationBarItem(selected = selectedIndex.value == 1, onClick = { selectedIndex.value = 1 }, icon = {
                Icon(Icons.Filled.Favorite, contentDescription = stringResource(R.string.preferred)) }
            )
        }
    }
}

@Composable
fun HomePage(contactViewModel: ContactViewModel) {
    val (shownBottomSheet, setBottomSheet)  = remember { mutableStateOf(false) }
    val navHostController = rememberNavController()
    val selectedIndex = remember { mutableStateOf(0) }
    Scaffold(topBar = { ContactTopBar(navHostController) }, bottomBar = { ContactBottomBar(selectedIndex) },
        floatingActionButton = { AddContactFloatingButton { setBottomSheet(true) } }, floatingActionButtonPosition = FabPosition.End) {
        Box(modifier = Modifier.padding(it)) {
            if(shownBottomSheet) {
                AddContactActivity(onDismissRequest = { setBottomSheet(false) }, contactViewModel = contactViewModel, setBottomSheet = setBottomSheet)
            }
            else {
                if (selectedIndex.value == 0) {
                    NavigationView(
                        navHostController = navHostController,
                        contactViewModel = contactViewModel
                    )
                } else {
                    FavoritesContacts(contactViewModel = contactViewModel)
                }
            }
        }
    }
}

@Composable
fun AddContactFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Create, contentDescription = stringResource(id = R.string.create))
    }
}
