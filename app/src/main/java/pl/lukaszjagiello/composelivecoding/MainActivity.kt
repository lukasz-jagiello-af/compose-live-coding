package pl.lukaszjagiello.composelivecoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo0BasicScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo1SizeScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo2FillScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo3PaddingScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo4WrapScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo5RequiredScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo6AspectRatioScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo7WeightScreen
import pl.lukaszjagiello.composelivecoding.screens.constraints.Demo8IntrinsicScreen
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLiveCodingTheme {
                Demo0BasicScreen()
//                Demo1SizeScreen()
//                Demo2FillScreen()
//                Demo3PaddingScreen()
//                Demo4WrapScreen()
//                Demo5RequiredScreen()
//                Demo6AspectRatioScreen()
//                Demo7WeightScreen()
//                Demo8IntrinsicScreen()
            }
        }
    }
}
