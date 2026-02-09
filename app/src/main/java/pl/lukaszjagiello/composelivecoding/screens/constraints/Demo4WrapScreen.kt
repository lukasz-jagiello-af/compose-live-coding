package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Cyan
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo4WrapScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .size(150.dp),
    ) {
        Text(
            modifier = Modifier
                .printSizes("Text 1")
                .padding(10.dp)
                .background(Green)
                .fillMaxSize()
                .printConstraints("Text 1")
                .wrapContentSize(unbounded = true) // unbounded
                .background(Cyan)
                .printConstraints("Text 1 b")
            ,
            text = "Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 Text 1 "
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo4WrapScreenPreview() {
    ComposeLiveCodingTheme {
        Demo4WrapScreen()
    }
}
