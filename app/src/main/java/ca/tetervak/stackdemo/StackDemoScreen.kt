package ca.tetervak.stackdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.stackdemo.model.StackItem

@Preview
@Composable
fun StackDemoScreen(viewModel: MainViewModel = viewModel()) {

    var input: String by rememberSaveable { mutableStateOf("") }
    val itemList: List<StackItem> by viewModel.stackItemsFlow.collectAsState(initial = emptyList())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
    ) {
        Text(
            text = "Stack Demo",
            fontSize = 48.sp,
        )
        StackValueInputOutput(
            value = input,
            onChange = { input = it },
            modifier = Modifier
                .sizeIn(minWidth = 256.dp)
                .padding(top = 24.dp)
        )
        ButtonRow(
            onPush = {
                viewModel.push(input)
                input = ""
            },
            onPop = {
                input = itemList[0].value
                viewModel.pop()
            },
            modifier = Modifier
                .width(width = 256.dp)
                .padding(top = 24.dp)
        )
        Divider(
            color = Color.Gray,
            thickness = 2.dp,
            modifier = Modifier.padding(top = 24.dp)
        )
        StackContent(
            itemList = itemList,
            modifier = Modifier
                .width(width = 256.dp)
                .weight(1f)
        )
        Divider(
            color = Color.Gray,
            thickness = 2.dp
        )
    }
}

@Composable
fun StackContent(itemList: List<StackItem>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(itemList) { stackItem ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(width = 1.dp, color = Color.Gray)
                    .padding(all = 16.dp)

            ) {
                Text(
                    text = "${stackItem.count}.",
                    fontSize = 32.sp
                )
                Text(
                    text = stackItem.value,
                    fontSize = 32.sp,
                    color = Color.Blue
                )
            }
        }
    }
}

@Composable
fun ButtonRow(
    onPush: () -> Unit,
    onPop: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Button(onClick = onPush) {
            Icon(
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = null
            )
            Text(text = "Push")
        }
        Button(onClick = onPop) {
            Icon(
                imageVector = Icons.Filled.ArrowUpward,
                contentDescription = null
            )
            Text(text = "Pop")
        }
    }
}

@Composable
fun StackValueInputOutput(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    TextField(
        label = { Text(text = "Input / Output") },
        value = value,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        singleLine = true,
        textStyle = TextStyle.Default.copy(fontSize = 32.sp, color = Color.Blue),
        modifier = modifier
    )
}
