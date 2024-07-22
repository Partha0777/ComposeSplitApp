package com.curiozing.composesplitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiozing.composesplitapp.ui.theme.ComposeSplitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSplitAppTheme {
                MyApp {
                    Column {
                        HeaderView()
                        FormView()
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            content()
        }
    }
}

@Composable
fun HeaderView() {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "$100", style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight(600)))
        }

    }
}

@Composable
fun FormView(){
    var totalValue by remember{ mutableStateOf("") }

    Column(modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 12.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = totalValue, onValueChange = {
                totalValue = it
        })
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeSplitAppTheme {
        MyApp {
            HeaderView()
            FormView()
        }
    }
}