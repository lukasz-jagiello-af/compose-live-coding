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
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEach
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.GenerateHeader
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun MultipleContentsScreen(
    modifier: Modifier = Modifier,
) {

    var amount by rememberSaveable { mutableIntStateOf(4) }
    val headers = remember { @Composable { GenerateHeader(amount = 4) } }
    val content = remember { @Composable { GenerateContent(amount = amount, equalSize = false) } }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )

        MultipleContentsLayout(
            headers = headers,
            content = content,
        )
    }
}

@Composable
fun MultipleContentsLayout(
    modifier: Modifier = Modifier,
    headers: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        contents = listOf(headers, content),
    ) { measurablesList, constraints ->
        val placeablesList: List<List<Placeable>> = measurablesList.map { measurables ->
            measurables.map { measurable ->
                measurable.measure(constraints)
            }
        }
        val rowMargin = 30
        val headerPlaceables = placeablesList[0]
        val contentPlaceables = placeablesList[1]
        val headerRowWidth = headerPlaceables.maxOf { it.width }
        val headerRowHeight = headerPlaceables.maxOf { it.height }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var xPosition = 0
            headerPlaceables.fastForEach { placeable ->
                placeable.place(xPosition, 0)
                xPosition += placeable.width + rowMargin
            }

            contentPlaceables.forEachIndexed { index, placeable ->
                val row = index / headerPlaceables.size
                val column = index % headerPlaceables.size
                val xPosition = column * (headerRowWidth + rowMargin)
                val yPosition = headerRowHeight + rowMargin + (row * headerRowHeight * 2)
                placeable.place(xPosition, yPosition)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleContentsScreenPreview() {
    ComposeLiveCodingTheme {
        MultipleContentsScreen()
    }
}
