package pl.lukaszjagiello.composelivecoding.screens.modifierlocal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.modifier.ModifierLocalModifierNode
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme
import pl.lukaszjagiello.composelivecoding.theme.Yellow

val LocalClickState = modifierLocalOf { mutableStateOf(false) }

@Composable
fun AdvancedModifiersScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Click on gray area.")
        Row(
            modifier = Modifier
                .padding(12.dp)
                .highlightProvider()
                .clickable {
                    println("Clicked on gray area, still works")
                }
                .background(Color.DarkGray)
                .padding(32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                Modifier
                    .size(60.dp)
                    .background(Color.White)
                    .receiveGlow(Color.Magenta)
            )
            Column(
                Modifier
                    .size(60.dp)
//                    .highlightProvider()
                    .background(Color.White)
                    .receivePadding()
                    .receiveGlow(Color.Green)
            ) {}
            Text(
                text = "Content",
                modifier = Modifier
                    .background(Color.White)
                    .receivePadding()
                    .receiveGlow(Color.Yellow)
            )
        }
    }
}

class HighlightProviderNode : Modifier.Node(),
    ModifierLocalModifierNode,
    PointerInputModifierNode {

    private var isClicked = mutableStateOf(false)
    override val providedValues = modifierLocalMapOf(LocalClickState to isClicked)

    override fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize) {
        if (pass == PointerEventPass.Main && pointerEvent.type != PointerEventType.Move) {
            when (pointerEvent.type) {
                PointerEventType.Press -> isClicked.value = true
                PointerEventType.Release -> isClicked.value = false
            }
        }
    }

    override fun onCancelPointerInput() {}
}

data object HighlightProviderElement : ModifierNodeElement<HighlightProviderNode>() {
    override fun create() = HighlightProviderNode()
    override fun update(node: HighlightProviderNode) {}
}

fun Modifier.highlightProvider() = this then HighlightProviderElement

class GlowNode(var glowColor: Color) : Modifier.Node(),
    ModifierLocalModifierNode,
    DrawModifierNode {

    override fun ContentDrawScope.draw() {
        val clickedMaybe = LocalClickState.current.value
        if (clickedMaybe) {
            drawRect(glowColor, size = size)
        }
        drawContent()
    }
}

data class GlowElement(val color: Color) : ModifierNodeElement<GlowNode>() {
    override fun create() = GlowNode(color)
    override fun update(node: GlowNode) {
        node.glowColor = color
    }
}

fun Modifier.receiveGlow(color: Color = Color.Cyan) = this then GlowElement(color)

class PaddingNode : Modifier.Node(), LayoutModifierNode, ModifierLocalModifierNode {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val isClicked = LocalClickState.current.value
        val placeable = measurable.measure(constraints)
        val padding = (if (isClicked) 16 else 0).dp.roundToPx()
        val width = placeable.width + padding * 2
        val height = placeable.height + padding * 2
        return layout(width, height) {
            placeable.place(padding, padding)
        }
    }
}

data object PaddingElement : ModifierNodeElement<PaddingNode>() {
    override fun create() = PaddingNode()
    override fun update(node: PaddingNode) {}
}

fun Modifier.receivePadding() = this then PaddingElement

@Preview(showBackground = true)
@Composable
fun AdvancedModifiersScreenPreview() {
    ComposeLiveCodingTheme {
        AdvancedModifiersScreen()
    }
}
