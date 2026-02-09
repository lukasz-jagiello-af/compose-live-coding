package pl.lukaszjagiello.composelivecoding.screens.customlayout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEach
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun SortingLayoutsScreen(
    modifier: Modifier = Modifier,
) {
    var amount by rememberSaveable { mutableIntStateOf(20) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )

        SortingCustomLayout {
            GenerateContent(amount = amount, equalSize = false)
        }
    }
}

@Composable
fun SortingCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val sortedPlaceable = placeables.sortedByDescending { it.height }

            val placeableRows = mutableListOf<List<Placeable>>()
            var placeableRow = mutableListOf<Placeable>()
            var rowWidth = 0

            sortedPlaceable.fastForEach { placeable ->
                if (rowWidth + placeable.width > constraints.maxWidth) {
                    placeableRows.add(placeableRow)
                    placeableRow = mutableListOf()
                    rowWidth = 0
                }
                rowWidth += placeable.width
                placeableRow.add(placeable)
            }
            if (placeableRow.isNotEmpty()) {
                placeableRows.add(placeableRow)
            }

            var heightOffset = 0
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeableRows.fastForEach { row ->
                    println("§ New Row [${row.size}]")
                    val rowHeight = row.maxOf { it.height }
                    var widthOffset = 0
                    row.fastForEach { placeable ->
                        println("§ -- Placing at: x=$widthOffset y=$heightOffset")
                        placeable.place(widthOffset, heightOffset)
                        widthOffset += placeable.width
                    }
                    heightOffset += rowHeight
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SortingLayoutsScreenPreview() {
    ComposeLiveCodingTheme {
        SortingLayoutsScreen()
    }
}
