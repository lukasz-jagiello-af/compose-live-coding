package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Yellow

@Composable
fun Demo0BasicScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .printConstraints("Row")
            .size(200.dp)
            .printConstraints("Row b")
    ) {}
}

@Preview(showBackground = true)
@Composable
fun Demo0BasicScreenPreview() {
    ComposeLiveCodingTheme {
        Demo0BasicScreen()
    }
}
