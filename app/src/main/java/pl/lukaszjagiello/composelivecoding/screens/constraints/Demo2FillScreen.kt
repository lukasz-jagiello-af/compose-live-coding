package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo2FillScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .size(300.dp, 100.dp)
            .printConstraints("Row")
        ,
    ) {
        Text(
            modifier = Modifier
                .printSizes("Text 1")
                .background(Green)
                .printConstraints("Text 1")
                .fillMaxSize(0.5f)
                .printConstraints("Text 1 b"),
            text = "Text 1"
        )
        Text(
            modifier = Modifier
                .printSizes("Text 2")
                .background(Blue)
                .printConstraints("Text 2")
                .fillMaxSize(0.5f)
                .printConstraints("Text 2 b")
            ,
            text = "Text 2"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo2FillScreenPreview() {
    ComposeLiveCodingTheme {
        Demo2FillScreen()
    }
}
