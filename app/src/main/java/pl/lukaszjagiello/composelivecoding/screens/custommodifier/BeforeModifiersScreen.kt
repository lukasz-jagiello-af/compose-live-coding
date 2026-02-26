package pl.lukaszjagiello.composelivecoding.screens.custommodifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Red
import pl.lukaszjagiello.composelivecoding.theme.Yellow

@Composable
fun BeforeModifiersScreen(
    modifier: Modifier = Modifier,
) {
    val padding = 32.dp
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = padding)
    ) {
        var selectedItem by remember { mutableIntStateOf(0) }
        Button(
            onClick = {
                selectedItem++
                if (selectedItem > 3) selectedItem = 0
            }
        ) {
            Text("SelectedItem: $selectedItem")
        }
        Text(
            text = "ABC Fruits",
            modifier = Modifier
                .background(Yellow)
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = if (selectedItem == 0) 16.dp else 0.dp)
                .background(if (selectedItem == 0) Red else Yellow)

        )
        HorizontalDivider(thickness = 4.dp)
        Text(
            text = "Apples",
            modifier = Modifier
                .background(Yellow)
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = if (selectedItem == 1) 16.dp else 0.dp)
                .background(if (selectedItem == 1) Red else Yellow)
        )
        Text(
            text = "Bananas",
            modifier = Modifier
                .background(Yellow)
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = if (selectedItem == 2) 16.dp else 0.dp)
                .background(if (selectedItem == 2) Red else Yellow)
        )
        Text(
            text = "Cherries",
            modifier = Modifier
                .background(Yellow)
                .padding(vertical = 16.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = if (selectedItem == 3) 16.dp else 0.dp)
                .background(if (selectedItem == 3) Red else Yellow)
        )
        HorizontalDivider(thickness = 4.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun BeforeModifiersScreenPreview() {
    ComposeLiveCodingTheme {
        BeforeModifiersScreen()
    }
}
