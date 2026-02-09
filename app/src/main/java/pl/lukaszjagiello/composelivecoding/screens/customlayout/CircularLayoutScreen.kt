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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun CircularLayoutScreen(
    modifier: Modifier = Modifier,
) {
    var amount by rememberSaveable { mutableIntStateOf(4) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )

        CircularCustomLayout {
            GenerateContent(amount = amount)
        }
    }
}

@Composable
fun CircularCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }

        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val centerX = width / 2f
        val centerY = height / 2f

        // Largest child (used to keep children fully inside the bounds).
        val maxChildHalfWidth = (placeables.maxOf { it.width } / 2f)
        val maxChildHalfHeight = (placeables.maxOf { it.height } / 2f)

        // safe radius so all child are inside layout
        val radius = min(
            (width / 2f) - maxChildHalfWidth,
            (height / 2f) - maxChildHalfHeight,
        ).coerceAtLeast(0f)

        // even angle spacing
        val angleStep = (2.0 * PI / placeables.size)
        // start at the top (-90*)
        val startAngle = -PI / 2.0

        layout(width, height) {
            placeables.fastForEachIndexed { index, placeable ->
                val angle = startAngle + (index * angleStep)
                val childCenterX = centerX + radius * cos(angle).toFloat()
                val childCenterY = centerY + radius * sin(angle).toFloat()

                val x = (childCenterX - placeable.width / 2f).roundToInt()
                val y = (childCenterY - placeable.height / 2f).roundToInt()

                placeable.place(x, y)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircularLayoutScreenPreview() {
    ComposeLiveCodingTheme {
        CircularLayoutScreen()
    }
}
