package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow

@Composable
fun Demo1SizeScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
//            .printConstraints("Row")
            .size(300.dp, 100.dp),
//            .printConstraints("Row b")
    ) {
        Text(
            modifier = Modifier
                .background(Green)
                .printSizes("Text 1")
                .printConstraints("Text 1 a")
                .width(150.dp)
                .size(size = 100.dp)
                .printConstraints("Text 1 b"),
            text = "Text 1"
        )
        Text(
            modifier = Modifier
                .background(Blue)
                .printSizes("Text 2")
                .printConstraints("Text 2 a")
                .height(height = 150.dp)
//                .widthIn(min = 100.dp)
                .defaultMinSize(150.dp)
                .printConstraints("Text 2 b"),
            text = "Text 2"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo1SizeScreenPreview() {
    ComposeLiveCodingTheme {
        Demo1SizeScreen()
    }
}
