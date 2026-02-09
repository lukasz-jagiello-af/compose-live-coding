package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.theme.Cyan
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Red
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo8IntrinsicScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(Cyan)
            .size(200.dp, 100.dp)
    ){
        Column (
            modifier = modifier
                .background(Yellow)
                .padding(10.dp)
                .width(IntrinsicSize.Max)
                .printSizes("Column")
                .printConstraints("Column")
        ) {
            Text(
                text = "Text 1",
                modifier = Modifier.
                    background(Red)
                    .fillMaxWidth()
                    .printSizes("Text 1")
                    .printConstraints("Text 1")
            )
            Text(
                text = "Text Longer 2",
                modifier = Modifier
                    .background(Green)
                    .fillMaxWidth()
                    .printSizes("Text 2")
                    .printConstraints("Text 2")
            )
            Text(
                text = "Text Longerer 3",
                modifier = Modifier
                    .background(Blue)
                    .fillMaxWidth()
                    .printSizes("Text 3")
                    .printConstraints("Text 3")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Demo8IntrinsicScreenPreview() {
    ComposeLiveCodingTheme {
        Demo8IntrinsicScreen()
    }
}
