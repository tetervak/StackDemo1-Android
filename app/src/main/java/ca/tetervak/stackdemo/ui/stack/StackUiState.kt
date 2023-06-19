package ca.tetervak.stackdemo.ui.stack

import ca.tetervak.stackdemo.domain.StackItem

data class StackUiState(val items: List<StackItem> = emptyList())