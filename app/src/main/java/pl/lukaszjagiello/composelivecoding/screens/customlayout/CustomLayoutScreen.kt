package pl.lukaszjagiello.composelivecoding.screens.customlayout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEach
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun CustomLayoutScreen(
    modifier: Modifier = Modifier,
) {

    var amount by rememberSaveable { mutableIntStateOf(4) }
    val content = remember { @Composable { GenerateContent(amount = amount, false) } }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )
        CustomLayout(content = content)
    }
}

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints.copy(maxWidth = 150)) }
        val parentHeight = placeables.sumOf { it.height }
        layout(constraints.maxWidth, parentHeight) {
            var xPosition = 0
            placeables.fastForEach {
                it.place(xPosition, xPosition / 2)
                xPosition += it.width + 10
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutScreenPreview() {
    ComposeLiveCodingTheme {
        CustomLayoutScreen()
    }
}
