package pl.lukaszjagiello.composelivecoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.screens.subcomposelayout.BoxWithConstraintsScreen
import pl.lukaszjagiello.composelivecoding.screens.subcomposelayout.DefferedCompositionScreen
import pl.lukaszjagiello.composelivecoding.screens.subcomposelayout.DifferenceScreen
import pl.lukaszjagiello.composelivecoding.screens.subcomposelayout.MultipleContentsComparisionScreen
import pl.lukaszjagiello.composelivecoding.screens.subcomposelayout.SingleSubcomposeLayoutScreen
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLiveCodingTheme {
                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainWithBottomTabs()
                    }
                }
            }
        }
    }
}

private val tabs = listOf(
    "Single Content", "Multiple Contents", "Difference",
    "Deffered Content", "BoxWithConstraints"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainWithBottomTabs() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                OutlinedTextField(
                    value = tabs[selectedIndex],
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    tabs.forEachIndexed { index, tab ->
                        DropdownMenuItem(
                            text = { Text(tab) },
                            onClick = {
                                selectedIndex = index
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            when (selectedIndex) {
                0 -> SingleSubcomposeLayoutScreen()
                1 -> MultipleContentsComparisionScreen()
                2 -> DifferenceScreen()
                3 -> DefferedCompositionScreen()
                4 -> BoxWithConstraintsScreen()
                else -> {}
            }
        }
    }
}
