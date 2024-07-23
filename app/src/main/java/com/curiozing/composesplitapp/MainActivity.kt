package com.curiozing.composesplitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
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
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            HeaderView()
            Box(modifier = Modifier.padding(12.dp).border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4))) {
                FormView()
            }

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
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "$100", style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight(600)))
        }

    }
}

@Composable
fun FormView() {
    var totalValue by remember { mutableStateOf("") }
    var totalPersons by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = totalValue, onValueChange = {
                totalValue = it
            })
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Persons",
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(600))
            )
            Row(verticalAlignment = Alignment.CenterVertically) {

                RounderIconButton(Icons.Outlined.Delete) {
                    if(totalPersons != "0"){
                        totalPersons = (totalPersons.toInt() - 1).toString()
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = totalPersons, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(600)))
                Spacer(modifier = Modifier.width(8.dp))
                RounderIconButton(Icons.Filled.AddCircle) {
                    totalPersons = (totalPersons.toInt() + 1).toString()
                }


            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RounderIconButton(icon: ImageVector, onClick: () -> Unit) {
    Card(modifier = Modifier, shape = CircleShape, onClick = {
        onClick.invoke()
    }) {
        Icon(imageVector = icon, contentDescription = "", modifier = Modifier.padding(4.dp))
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
        MyApp()
    }
}