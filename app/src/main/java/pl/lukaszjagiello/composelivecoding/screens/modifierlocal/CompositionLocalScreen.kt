package pl.lukaszjagiello.composelivecoding.screens.modifierlocal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Yellow

val LocalBackgroundColor = compositionLocalOf { Yellow }

@Composable
fun CompositionLocalScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(24.dp)) {

        val modifierSimple = Modifier.background(LocalBackgroundColor.current)
        val bgFromModifier = Modifier.bgFromModifier()
        val bgFromComposed = Modifier.bgFromComposed()
        val bgNode = Modifier.bgNode()

        ColorfulText("Text with color")
        ColorfulText("Text with modifierSimple", modifierSimple)
        ColorfulText("Text with bgFromModifier", bgFromModifier)
        ColorfulText("Text with bgFromComposed", bgFromComposed)
        ColorfulText("Text with bgNode", bgNode)

        Spacer(modifier = Modifier.padding(8.dp))

        CompositionLocalProvider(LocalBackgroundColor provides Blue) {
            ColorfulText("Text with color")
            ColorfulText("Text with modifierSimple", modifierSimple)
            ColorfulText("Text with bgFromModifier", bgFromModifier)
            ColorfulText("Text with bgFromComposed", bgFromComposed)
            ColorfulText("Text with bgNode", bgNode)
        }
    }
}

@Composable
fun Modifier.bgFromModifier(): Modifier {
    return this then Modifier.background(LocalBackgroundColor.current)
}

fun Modifier.bgFromComposed() = composed {
    background(LocalBackgroundColor.current)
}

@Composable
fun ColorfulText(
    text: String,
    modifier: Modifier? = null,
) {
    Text(
        modifier = modifier ?: Modifier.background(LocalBackgroundColor.current),
        text = text
    )
}

class BgModifierNode : Modifier.Node(), CompositionLocalConsumerModifierNode, DrawModifierNode {
    override fun ContentDrawScope.draw() {
        drawRect(color = currentValueOf(LocalBackgroundColor))
        drawContent()
    }
}

data object BgModifierElement : ModifierNodeElement<BgModifierNode>() {
    override fun create(): BgModifierNode {
        return BgModifierNode()
    }

    override fun update(node: BgModifierNode) {}
}

fun Modifier.bgNode() = this then BgModifierElement

@Preview(showBackground = true)
@Composable
fun CompositionLocalScreenPreview() {
    ComposeLiveCodingTheme {
        Surface {
            CompositionLocalScreen()
        }
    }
}
