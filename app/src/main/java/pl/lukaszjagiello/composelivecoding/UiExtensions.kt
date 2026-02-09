package pl.lukaszjagiello.composelivecoding

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged

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
