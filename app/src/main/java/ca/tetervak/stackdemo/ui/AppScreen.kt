package ca.tetervak.stackdemo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.stackdemo.ui.stack.StackScreen
import ca.tetervak.stackdemo.ui.stack.StackViewModel
import ca.tetervak.stackdemo.ui.theme.AppTheme

@Composable
fun AppScreen() {
    val viewModel: StackViewModel = viewModel()
    StackScreen(viewModel)
}

@Preview
@Composable
fun AppScreenPreview(){
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppScreen()
        }
    }
}

