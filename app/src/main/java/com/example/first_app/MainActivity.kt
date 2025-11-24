package com.example.first_app

import com.example.first_app.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_app.ui.theme.FirstappTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           FirstappTheme{
               Surface(
                   modifier = Modifier
                       .fillMaxSize()
                       .systemBarsPadding() // SafeArea kind
               ) {
                   ItemListScreen()

               }
           }
        }
    }
}

@Composable
fun ItemListScreen(){
    val items = remember { mutableStateListOf(ListItem("Item 1")) }
    val inputText = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(items){ index, item ->
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   RadioButton(
                       selected = item.isComplete,
                       onClick = {
                           items[index] = item.copy(isComplete = !item.isComplete)
                       }
                   )
                   Text(item.text)
                   Button(onClick = {items.removeAt(index)}) {
                       Text("Delete")
                   }
               }
            }
        }

        TextField(
            value = inputText.value,
            onValueChange = {inputText.value = it},
            label = {Text("Enter something")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
              if(inputText.value.isNotEmpty()){
                  items.add(ListItem(inputText.value))
                  inputText.value = ""
              }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) { Text("Add new Item")}
    }
}

data class ListItem(
    val text: String,
    var isComplete: Boolean = false
)