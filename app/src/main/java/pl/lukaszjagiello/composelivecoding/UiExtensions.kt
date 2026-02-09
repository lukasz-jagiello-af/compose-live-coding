package pl.lukaszjagiello.composelivecoding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import kotlin.random.Random

fun Modifier.printConstraints(tag: String = ""): Modifier {
    return layout { measurable, constraints ->
        Log.d("§", "$tag: \t $constraints")
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

fun Modifier.printSizes(tag: String = ""): Modifier {
    return this.onSizeChanged { intSize ->
        Log.i("§", "$tag: \t $intSize")
    }
}


@Composable
fun GenerateHeader(amount: Int = 5, equalSize: Boolean = true) = repeat(amount) { index ->
    val size = if (equalSize) 80.dp else Random.nextInt(20, 80).dp
    val modifier = Modifier
        .background(Color(Random.nextInt()))
        .width(size)
        .height(size / 2)
        .wrapContentSize()
    Box {
        Text(modifier = modifier, text = "Header: $index")
    }
}

@Composable
fun GenerateContent(amount: Int = 5, equalSize: Boolean = true) = repeat(amount) { index ->
    val size = if (equalSize) 80.dp else Random.nextInt(20, 80).dp
    val modifier = Modifier
        .background(Color(Random.nextInt()))
        .size(size)
        .wrapContentSize()
    Box {
        Text(modifier = modifier, text = "$index")
    }
}

@Composable
fun AmountSlider(
    amount: Int,
    onAmountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Slider(
            value = amount.toFloat(),
            onValueChange = { onAmountChange(it.toInt().coerceIn(1, 30)) },
            valueRange = 1f..30f,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = amount.toString(),
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}
