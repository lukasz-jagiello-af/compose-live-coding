package pl.lukaszjagiello.composelivecoding.screens.constraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow
import pl.lukaszjagiello.composelivecoding.printConstraints
import pl.lukaszjagiello.composelivecoding.printSizes
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun Demo6AspectRatioScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Yellow)
            .printConstraints("Row")
            .aspectRatio(1f)
            .printConstraints("Row b")
        ,
    ) {
        Text(
            modifier = Modifier
                .background(Green)
                .printSizes("Text ")
                .printConstraints("Text ")
                .fillMaxSize()
                .printConstraints("Text b")
                .aspectRatio(9/16f,true)
                .printConstraints("Text c")
                .printSizes("Text b")
            ,
            text = "Text"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Demo6AspectRatioScreenPreview() {
    ComposeLiveCodingTheme {
        Demo6AspectRatioScreen()
    }
}
