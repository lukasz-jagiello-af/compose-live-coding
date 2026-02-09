package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Magenta
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo3PaddingScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .width(150.dp),
    ) {
        Text(
            modifier = Modifier
                .printSizes("Text 1")
                .background(Green)
                .printConstraints("Text 1")
                .padding(10.dp)
                .printConstraints("Text 1 b")
                .printSizes("Text 1 b")
            ,
            text = "Text 1"
        )
        Text(
            modifier = Modifier
                .printSizes("Text 2")
                .background(Magenta)
                .printConstraints("Text 2"),
            text = "Text 2"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo3PaddingScreenPreview() {
    ComposeLiveCodingTheme {
        Demo3PaddingScreen()
    }
}
