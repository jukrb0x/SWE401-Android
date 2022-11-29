package com.brokenbrains.fitness

import android.graphics.drawable.Icon
import android.provider.ContactsContract
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.brokenbrains.fitness.ui.screens.HomeScreen
import com.brokenbrains.fitness.ui.screens.ProfileScreen


interface ScreenRoute{
    val route: String
    val displayName: String
    val icon: ImageVector
    val screen: @Composable () -> Unit
}

object Home : ScreenRoute {
    override val route = "Tabs/Home"
    override val displayName = "Home"
    override val icon =  Icons.Default.Home
    override val screen: @Composable () -> Unit = { HomeScreen() }
}

object HealthPlus : ScreenRoute {
    override val route = "Tabs/HealthPlus"
    override val displayName = "Health +"
    override val icon = Icons.Rounded.Add //Icon(painter=painterResource(R.drawable.ic_health_plus), "Health +")
    override val screen: @Composable () -> Unit = { ProfileScreen() }
}

object Sharing : ScreenRoute {
    override val route = "Tabs/Sharing"
    override val displayName = "Sharing"
    override val icon = Icons.Default.Share
    override val screen: @Composable () -> Unit = { ProfileScreen() }
}

val TabRoutes = listOf(
    Home, Sharing
)