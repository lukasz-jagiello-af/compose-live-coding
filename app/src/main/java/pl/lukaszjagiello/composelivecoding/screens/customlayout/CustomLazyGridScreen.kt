package pl.lukaszjagiello.composelivecoding.screens.customlayout

import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastRoundToInt
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import kotlin.math.sqrt

@Composable
fun CustomLazyGridScreen(
    modifier: Modifier = Modifier,
) {

    var amount by rememberSaveable { mutableIntStateOf(4) }
    val content = remember { @Composable { GenerateContent(amount = amount) } }
    var dragOffset by remember { mutableStateOf(IntOffset(0, 0)) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )

        SquareLazyGrid(
            amount = amount,
            offset = dragOffset,
            onDrag = { dragDelta ->
                dragOffset += dragDelta
                println("§ Dragged: $dragDelta")
            },
            content = content,
        )
    }
}

@Composable
fun SquareLazyGrid(
    modifier: Modifier = Modifier,
    amount: Int,
    offset: IntOffset = IntOffset.Zero,
    onDrag: (dragDelta: IntOffset) -> Unit = {},
    content: @Composable () -> Unit = { }
) {
    val col = sqrt(amount.toDouble()).fastRoundToInt()
    Layout(
        modifier = modifier.draggable2D(
            state = rememberDraggable2DState { delta ->
                onDrag(delta.round())
            }
        ),
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                val row = index / col
                val column = index % col
                val xPosition = (column * placeable.width) + offset.x
                val yPosition = (row * placeable.height) + offset.y

                placeable.placeRelative(xPosition, yPosition)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLazyGridScreenPreview() {
    ComposeLiveCodingTheme {
        CustomLazyGridScreen()
    }
}
