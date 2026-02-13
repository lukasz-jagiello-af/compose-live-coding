package pl.lukaszjagiello.composelivecoding.screens.subcomposelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun SingleSubcomposeLayoutScreen(
    modifier: Modifier = Modifier,
) {
    var amount by remember { mutableIntStateOf(3) }
    val content = @Composable { GenerateContent(amount = amount) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )
        SingleCustomLayout(content = content)
        Box(modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .height(10.dp))
        SingleCustomSubcomposeLayout(content = content)
    }
}

@Composable
fun SingleCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { },
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }

        layout(constraints.maxWidth, placeables.sumOf { it.height }) {
            var xPlace = 0
            placeables.fastForEach { placeable ->
                placeable.place(xPlace, xPlace)
                xPlace += placeable.width
            }
        }
    }
}

@Composable
fun SingleCustomSubcomposeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { },
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->
        val measurables = subcompose("content", content) // <- main difference between Layout and SubcomposeLayout
        val placeables = measurables.map { it.measure(constraints) }

        layout(constraints.maxWidth, placeables.sumOf { it.height }) {
            var xPlace = 0
            placeables.fastForEach { placeable ->
                placeable.place(xPlace, xPlace)
                xPlace += placeable.width
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleSubcomposeLayoutScreenPreview() {
    ComposeLiveCodingTheme {
        SingleSubcomposeLayoutScreen()
    }
}
