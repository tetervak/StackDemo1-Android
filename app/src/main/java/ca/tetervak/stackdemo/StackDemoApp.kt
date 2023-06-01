package ca.tetervak.stackdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ca.tetervak.stackdemo.theme.StackDemoTheme

@Composable
fun StackDemoApp() {
    StackDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StackDemoScreen()
        }
    }
}

