package pl.lukaszjagiello.composelivecoding.screens.modifierlocal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.modifier.ModifierLocalModifierNode
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.Blue
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Cyan
import pl.lukaszjagiello.composelivecoding.theme.Green
import pl.lukaszjagiello.composelivecoding.theme.Yellow

val CompositionLocalBackgroundColor = compositionLocalOf { Yellow }
val ModifierLocalBackgroundColor = modifierLocalOf { Cyan }

@Composable
fun LocalComparisonScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .providerNode()
//            .modifierLocalProvider(ModifierLocalBackgroundColor){Red}
    ) {
        CompositionLocalProvider(
            CompositionLocalBackgroundColor provides Blue
        ) {
            Text(
                text = "CompositionLocalBackgroundColor",
                modifier = Modifier.background(CompositionLocalBackgroundColor.current)
            )
        }

        ModifierLocalText()
    }
}

@Composable
fun ModifierLocalText(
    modifier: Modifier = Modifier,
) {
    var bgColor by remember { mutableStateOf(Green) }
    Text(
        modifier = modifier
            .consumerNode(),
//            .modifierLocalConsumer{
//                bgColor = ModifierLocalBackgroundColor.current
//            }
//            .background(bgColor),
        text = "ModifierLocalBackgroundColor"
    )
}

class ProviderModifierNode : Modifier.Node(), ModifierLocalModifierNode {
    override val providedValues: ModifierLocalMap = modifierLocalMapOf(ModifierLocalBackgroundColor to Yellow)
}

data object ProviderModifierElement : ModifierNodeElement<ProviderModifierNode>() {
    override fun create(): ProviderModifierNode = ProviderModifierNode()
    override fun update(node: ProviderModifierNode) {}
}

fun Modifier.providerNode() = this then ProviderModifierElement

class ConsumerModifierNode : Modifier.Node(), ModifierLocalModifierNode, DrawModifierNode {
    override fun ContentDrawScope.draw() {
        drawRect(color = ModifierLocalBackgroundColor.current)
        drawContent()
    }
}

data object ConsumerModifierElement : ModifierNodeElement<ConsumerModifierNode>() {
    override fun create(): ConsumerModifierNode = ConsumerModifierNode()
    override fun update(node: ConsumerModifierNode) {}
}

fun Modifier.consumerNode() = this then ConsumerModifierElement

@Preview(showBackground = true)
@Composable
fun LocalComparisonScreenPreview() {
    ComposeLiveCodingTheme {
        Surface {
            LocalComparisonScreen()
        }
    }
}
