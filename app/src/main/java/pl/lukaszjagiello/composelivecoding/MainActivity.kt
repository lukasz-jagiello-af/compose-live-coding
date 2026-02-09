package pl.lukaszjagiello.composelivecoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pl.lukaszjagiello.composelivecoding.screens.customlayout.CircularLayoutScreen
import pl.lukaszjagiello.composelivecoding.screens.customlayout.CompressedLayoutsScreen
import pl.lukaszjagiello.composelivecoding.screens.customlayout.CustomLayoutScreen
import pl.lukaszjagiello.composelivecoding.screens.customlayout.CustomLazyGridScreen
import pl.lukaszjagiello.composelivecoding.screens.customlayout.MultipleContentsScreen
import pl.lukaszjagiello.composelivecoding.screens.customlayout.SortingLayoutsScreen
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLiveCodingTheme {
                MainWithBottomTabs()
            }
        }
    }
}

private val tabs = listOf("Basic", "Compressed", "Circular", "Sorting", "Movable Grid", "Multiple")

@Composable
private fun MainWithBottomTabs() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        label = { Text(tab) },
                        icon = {},
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (selectedIndex) {
                0 -> CustomLayoutScreen()
                1 -> CompressedLayoutsScreen()
                2 -> CircularLayoutScreen()
                3 -> SortingLayoutsScreen()
                4 -> CustomLazyGridScreen()
                5 -> MultipleContentsScreen()
                else -> {}
            }
        }
    }
}
