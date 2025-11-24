package com.example.first_app.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.first_app.domain.model.ListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ItemsViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items = _items.asStateFlow()

    var inputText by mutableStateOf("")
        private set

    init {
        // Add some dummy data for preview
        _items.value = listOf(
            ListItem("Item 1"),
            ListItem("Item 2", isComplete = true),
            ListItem("Item 3")
        )
    }

    fun addItem() {
        if (inputText.isNotBlank()) {
            val newItem = ListItem(text = inputText)
            _items.update { it + newItem }
            inputText = ""
        }
    }

    fun toggleComplete(itemToToggle: ListItem) {
        _items.update { list ->
            list.map { item ->
                if (item.text == itemToToggle.text) { // Assuming text is unique for now
                    item.copy(isComplete = !item.isComplete)
                } else {
                    item
                }
            }
        }
    }

    fun deleteItem(itemToDelete: ListItem) {
        _items.update { list ->
            list.filterNot { it.text == itemToDelete.text } // Assuming text is unique for now
        }
    }

    fun onInputTextChanged(newText: String) {
        inputText = newText
    }
}