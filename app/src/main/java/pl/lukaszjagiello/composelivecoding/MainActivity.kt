package pl.lukaszjagiello.composelivecoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import pl.lukaszjagiello.composelivecoding.theme.ComposeLiveCodingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLiveCodingTheme {
                Text("Hello World")
            }
        }
    }
}
