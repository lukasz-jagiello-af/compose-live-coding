package pl.lukaszjagiello.composelivecoding.screens.customlayout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEachIndexed
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun CompressedLayoutsScreen(
    modifier: Modifier = Modifier,
) {
    var amount by rememberSaveable { mutableIntStateOf(4) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )

        CompressedCustomLayout {
            GenerateContent(amount = amount)
        }
    }
}

@Composable
fun CompressedCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val totalWidth = placeables.sumOf { it.width }
        val compensation = if (totalWidth > constraints.maxWidth) {
            (totalWidth - constraints.maxWidth) / (placeables.size - 1)
        } else {
            0
        }
        var offsetX = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.fastForEachIndexed { index, placeable ->
                placeable.place(offsetX, index)
                offsetX += placeable.width - compensation
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompressedLayoutsScreenPreview() {
    ComposeLiveCodingTheme {
        CompressedLayoutsScreen()
    }
}
