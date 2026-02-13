package pl.lukaszjagiello.composelivecoding.screens.subcomposelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import kotlin.math.ceil

@Composable
fun DefferedCompositionScreen(
    modifier: Modifier = Modifier,
) {
    var amount by remember { mutableIntStateOf(4) }
    val content = @Composable { GenerateContent(amount = amount) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )
        DefferedCompositionLayout(content = content)
    }
}

@Composable
fun DefferedCompositionLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { },
) {
    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->
        val contentMeasurables = subcompose("content", content)
        val contentPlaceables = contentMeasurables.map { it.measure(constraints) }

        val itemsInRow = constraints.maxWidth / contentPlaceables.first().width
        val itemHeight = contentPlaceables.first().height

        var isOverflowing = false
        val itemsToPlace = if (contentPlaceables.size > itemsInRow * itemsInRow) {
            isOverflowing = true
            itemsInRow * itemsInRow
        } else {
            contentPlaceables.size
        }

        val parentWidth = itemsInRow * contentPlaceables.first().width
        val parentHeight = ceil(itemsToPlace / itemsInRow.toFloat()).toInt() * itemHeight

        layout(parentWidth, parentHeight) {
            (0..<itemsToPlace).forEach { index ->
                val placeable = contentPlaceables[index]
                val xPosition = index % itemsInRow * placeable.width
                val yPosition = index / itemsInRow * itemHeight
                placeable.place(xPosition, yPosition)
            }
            if (isOverflowing) {
                val overflowMeasurables = subcompose("overflow") {
                    Text(
                        text = "... there is more",
                        modifier = Modifier
                            .background(Color.Gray)
                            .fillMaxWidth()
                            .wrapContentSize()
                            .padding(4.dp)
                    )
                }
                val overflowPlaceable: Placeable = overflowMeasurables.first().measure(constraints)
                overflowPlaceable.place(
                    x = parentWidth - overflowPlaceable.width,
                    y = parentHeight - overflowPlaceable.height
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefferedCompositionScreenPreview() {
    ComposeLiveCodingTheme {
        DefferedCompositionScreen()
    }
}
