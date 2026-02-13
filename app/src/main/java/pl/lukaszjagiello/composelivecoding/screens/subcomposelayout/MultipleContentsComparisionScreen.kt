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
import androidx.compose.runtime.saveable.rememberSaveable
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
import pl.lukaszjagiello.composelivecoding.GenerateHeader
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import kotlin.math.ceil

@Composable
fun MultipleContentsComparisionScreen(
    modifier: Modifier = Modifier,
) {

    var amount by rememberSaveable { mutableIntStateOf(3) }
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

        Box(modifier = Modifier.background(Color.Blue).fillMaxWidth().height(10.dp))

        MultipleContentsSubcomposeLayout(
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
        val rowMargin = 30

        val headerMeasurables = measurablesList[0]
        val contentMeasurables = measurablesList[1]

        val headerPlaceables = headerMeasurables.map { it.measure(constraints) }
        val contentPlaceables = contentMeasurables.map { it.measure(constraints) }

        val headerRowWidth = headerPlaceables.maxOf { it.width }
        val headerRowHeight = headerPlaceables.maxOf { it.height }
        val parentHeight = (headerRowHeight + rowMargin) +
            (headerRowHeight * 2 + rowMargin) *
            ceil(contentPlaceables.size / headerPlaceables.size.toFloat())

        layout(constraints.maxWidth, parentHeight.toInt()) {
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

@Composable
fun MultipleContentsSubcomposeLayout(
    modifier: Modifier = Modifier,
    headers: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->
        val rowMargin = 30

        val headerMeasurables = subcompose("header", headers)
        val contentMeasurables = subcompose("content", content)

        val headerPlaceables = headerMeasurables.map { it.measure(constraints) }
        val contentPlaceables = contentMeasurables.map { it.measure(constraints) }

        val headerRowWidth = headerPlaceables.maxOf { it.width }
        val headerRowHeight = headerPlaceables.maxOf { it.height }
        val parentHeight = (headerRowHeight + rowMargin) +
            (headerRowHeight * 2 + rowMargin) *
            ceil(contentPlaceables.size / headerPlaceables.size.toFloat())

        layout(constraints.maxWidth, parentHeight.toInt()) {
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
fun MultipleContentsComparisionScreenPreview() {
    ComposeLiveCodingTheme {
        MultipleContentsComparisionScreen()
    }
}
