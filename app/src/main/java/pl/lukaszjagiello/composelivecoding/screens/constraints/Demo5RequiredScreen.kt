package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo5RequiredScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .size(150.dp),
    ) {
        Text(
            modifier = Modifier
                .background(Green)
                .printSizes("Text 1")
                .printConstraints("Text 1")
//                .size(200.dp)
                .requiredSize(160.dp)
                .printConstraints("Text 1 b")
                .printSizes("Text 1 b")
            ,
            text = "Text"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo5RequiredScreenPreview() {
    ComposeLiveCodingTheme {
        Demo5RequiredScreen()
    }
}
