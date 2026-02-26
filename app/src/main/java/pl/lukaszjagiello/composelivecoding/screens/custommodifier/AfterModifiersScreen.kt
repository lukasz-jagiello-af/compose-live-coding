package pl.lukaszjagiello.composelivecoding.screens.custommodifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Red
import pl.lukaszjagiello.composelivecoding.theme.Yellow

@Composable
fun AfterModifiersScreen(
    modifier: Modifier = Modifier,
) {
    val padding = 32.dp
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = padding)
    ) {
        var selectedItem by remember { mutableIntStateOf(0) }
        Button(
            onClick = {
                selectedItem++
                if (selectedItem > 3) selectedItem = 0
            }
        ) {
            Text("SelectedItem: $selectedItem")
        }
        Text(
            text = "ABC Fruits",
            modifier = Modifier.applyCustomStyle(selectedItem == 0)

        )
        HorizontalDivider(thickness = 4.dp, modifier = Modifier.minusPaddingPro(padding))
        Text(
            text = "Apples",
            modifier = Modifier.applyCustomStyle(selectedItem == 1)
        )
        Text(
            text = "Bananas",
            modifier = Modifier.applyCustomStyle(selectedItem == 2)
        )
        Text(
            text = "Cherries",
            modifier = Modifier.applyCustomStyle(selectedItem == 3)
        )
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(
                    constraints.copy(
                        minWidth = constraints.minWidth + padding.roundToPx() * 2,
                        maxWidth = constraints.maxWidth + padding.roundToPx() * 2,
                    )
                )
                layout(placeable.width, placeable.height) {
                    placeable.place(0, 0)
                }
            }
        )
    }
}

@Deprecated("Causes recompositions")
fun Modifier.minusPadding(padding: Dp) = this.layout { measurable, constraints ->
    val placeable = measurable.measure(
        constraints.copy(
            minWidth = constraints.minWidth + padding.roundToPx() * 2,
            maxWidth = constraints.maxWidth + padding.roundToPx() * 2,
        )
    )
    layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
    }
}

fun Modifier.applyCustomStyle(isSelected: Boolean): Modifier {
    return this
        .background(Yellow)
        .padding(vertical = 16.dp, horizontal = 24.dp)
        .fillMaxWidth()
        .applyIf(isSelected) {
            padding(horizontal = 16.dp).background(Red)
        }
}

fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier {
    return if (condition) {
        this.then(Modifier.modifier())
    } else {
        this
    }
}

class MinusPaddingNode(var padding: Dp) : Modifier.Node(), LayoutModifierNode {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(
            constraints.copy(
                minWidth = constraints.minWidth + padding.roundToPx() * 2,
                maxWidth = constraints.maxWidth + padding.roundToPx() * 2,
            )
        )
        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }

}

data class MinusPaddingElement(val padding: Dp) : ModifierNodeElement<MinusPaddingNode>() {
    override fun create(): MinusPaddingNode {
        return MinusPaddingNode(padding)
    }

    override fun update(node: MinusPaddingNode) {
        node.padding = padding
        node.invalidateMeasurement()
    }
}

fun Modifier.minusPaddingPro(padding: Dp) = this then MinusPaddingElement(padding)

@Preview(showBackground = true)
@Composable
fun AfterModifiersScreenPreview() {
    ComposeLiveCodingTheme {
        AfterModifiersScreen()
    }
}
