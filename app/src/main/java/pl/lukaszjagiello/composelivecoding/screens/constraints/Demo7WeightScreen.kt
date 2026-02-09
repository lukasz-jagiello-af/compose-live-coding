package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Red
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo7WeightScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .size(200.dp, 50.dp)
            .printConstraints("Row"),
    ) {
        Text(
            modifier = Modifier
                .background(Red)
                .printSizes("Text 1")
                .printConstraints("Text 1")
            ,
            text = "Text 1"
        )
        Text(
            modifier = Modifier
                .background(Green)
                .printSizes("Text 2")
                .printConstraints("Text 2")
                .weight(1f)
            ,
            text = "Text 2"
        )
        Text(
            modifier = Modifier
                .background(Blue)
                .printSizes("Text 3")
                .printConstraints("Text 3")
            ,
            text = "Text 3"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo7WeightScreenPreview() {
    ComposeLiveCodingTheme {
        Demo7WeightScreen()
    }
}
