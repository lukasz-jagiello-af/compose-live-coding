package pl.lukaszjagiello.composelivecoding.screens.subcomposelayout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.lukaszjagiello.composelivecoding.AmountSlider
import pl.lukaszjagiello.composelivecoding.GenerateContent
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

@Composable
fun BoxWithConstraintsScreen(
    modifier: Modifier = Modifier,
) {
    var amount by remember { mutableIntStateOf(4) }
    val content = @Composable { GenerateContent(amount = amount) }

    Column(modifier = modifier) {
        AmountSlider(
            amount = amount,
            onAmountChange = { amount = it },
        )
        BoxWithConstraintsLayout(content = content)
    }
}

@Composable
fun BoxWithConstraintsLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { },
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        if (maxWidth > maxHeight) {
            Row { content() }
        } else {
            Column { content() }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoxWithConstraintsScreenPreview() {
    ComposeLiveCodingTheme {
        BoxWithConstraintsScreen()
    }
}
