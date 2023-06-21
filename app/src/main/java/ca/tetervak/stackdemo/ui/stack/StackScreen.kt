package ca.tetervak.stackdemo.ui.stack

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.stackdemo.R
import ca.tetervak.stackdemo.domain.StackItem
import ca.tetervak.stackdemo.ui.theme.AppTheme


@Composable
fun StackScreen(
    viewModel: StackViewModel, modifier: Modifier = Modifier
) {
    val state: State<StackUiState> = viewModel.stackUiState.collectAsState()
    val itemList: List<StackItem> = state.value.items

    StackScreenBody(onPush = { viewModel.push(it) },
        onPop = { viewModel.pop() },
        itemList = itemList,
        modifier = modifier
    )
}

@Composable
fun StackScreenBody(
    onPush: (String) -> Unit,
    onPop: () -> Unit,
    itemList: List<StackItem>,
    modifier: Modifier = Modifier
) {
    var input: String by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(all = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.stack_demo_title),
            fontSize = 48.sp,
            color = colorResource(id = R.color.pink_700)
        )
        StackValueInputOutput(
            value = input,
            onChange = { input = it },
            modifier = Modifier
                .sizeIn(minWidth = 256.dp)
                .padding(top = 24.dp)
        )
        ButtonRow(onPush = {
            if (input.isNotBlank()) {
                onPush(input.trim())
                input = ""
                focusManager.clearFocus()
            }
        },
            onPop = {
                input = itemList.first().value
                onPop()
            },
            showPopButton = itemList.isNotEmpty(),
            modifier = Modifier
                .width(width = 256.dp)
                .padding(top = 24.dp)
        )
        Divider(
            color = Color.Gray, thickness = 2.dp, modifier = Modifier.padding(top = 24.dp)
        )
        if (itemList.isNotEmpty()) {
            StackContent(
                itemList = itemList, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.stack_empty_message),
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.orange_900),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .border(
                            width = 2.dp, color = colorResource(id = R.color.orange_900)
                        )
                        .padding(all = 16.dp)
                )
            }
        }
        Divider(
            color = Color.Gray, thickness = 2.dp
        )
    }
}

@Preview
@Composable
fun StackScreenBodyPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val itemList = listOf(
                StackItem(3, "Item C"), StackItem(2, "Item B"), StackItem(1, "Item A")
            )
            StackScreenBody(onPush = {}, onPop = {}, itemList = itemList
            )
        }
    }
}

@Composable
fun StackContent(
    itemList: List<StackItem>, modifier: Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp, horizontal = 8.dp
        ), horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
    ) {
        items(itemList) { stackItem ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(width = 256.dp)
                    .border(width = 2.dp, color = Color.Gray)
                    .padding(all = 16.dp)

            ) {
                Text(
                    text = "${stackItem.count}.", fontSize = 32.sp
                )
                Text(
                    text = stackItem.value,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.blue_900)
                )
            }
        }
    }
}

@Composable
fun ButtonRow(
    onPush: () -> Unit, onPop: () -> Unit, showPopButton: Boolean, modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier
    ) {
        Button(onClick = onPush) {
            Icon(
                imageVector = Icons.Default.ArrowDownward, contentDescription = null
            )
            Text(
                text = stringResource(R.string.push_button_label), fontSize = 20.sp
            )
        }
        if (showPopButton) {
            Button(onClick = onPop) {
                Icon(
                    imageVector = Icons.Filled.ArrowUpward, contentDescription = null
                )
                Text(
                    text = stringResource(R.string.pop_button_label), fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun StackValueInputOutput(
    value: String, onChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    TextField(
        label = { Text(text = stringResource(R.string.input_output_label)) },
        value = value,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        textStyle = TextStyle.Default.copy(
            fontSize = 32.sp, color = colorResource(id = R.color.blue_900)
        ),
        modifier = modifier.border(width = 2.dp, color = Color.Gray)
    )
}
