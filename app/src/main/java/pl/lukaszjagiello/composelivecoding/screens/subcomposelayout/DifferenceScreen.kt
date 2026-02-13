package pl.lukaszjagiello.composelivecoding.screens.subcomposelayout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEach
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.GenerateHeader
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun DifferenceScreen(
    modifier: Modifier = Modifier,
) {

    val headers = remember { @Composable { GenerateHeader(amount = 1) } }
    val content = remember { @Composable { GenerateContent(amount = 2) } }

    Column(modifier = modifier) {
//        DiffLayout(
//            headers = headers,
//            content = content,
//        )
        DiffSubcomposeLayout(
            headers = headers,
            content = content,
        )
    }
}

@Composable
fun DiffLayout(
    modifier: Modifier = Modifier,
    headers: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        contents = listOf(headers, content),
    ) { measurablesList, constraints ->
        val headerMeasurables = measurablesList[0]

        val placeables = if (headerMeasurables.isEmpty()) {
            val contentMeasurables = measurablesList[1]
            contentMeasurables.map { it.measure(constraints) }
        } else {
            headerMeasurables.map { it.measure(constraints) }
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var xPosition =0
            placeables.fastForEach { placeable ->
                placeable.place(xPosition, 0)
                xPosition += placeable.width
            }
        }
    }
}

@Composable
fun DiffSubcomposeLayout(
    modifier: Modifier = Modifier,
    headers: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->
        val headerMeasurables = subcompose(0, headers)

        val placeables = if (headerMeasurables.isEmpty()) {
            val contentMeasurables = subcompose(1, content)
            contentMeasurables.map { it.measure(constraints) }
        } else {
            headerMeasurables.map { it.measure(constraints) }
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var xPosition =0
            placeables.fastForEach { placeable ->
                placeable.place(xPosition, 0)
                xPosition += placeable.width
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DifferenceScreenPreview() {
    ComposeLiveCodingTheme {
        DifferenceScreen()
    }
}
