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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        var totalPerHead = remember {
            mutableDoubleStateOf(0.0)
        }
        Column {
            HeaderView(totalPerHead)
            Box(modifier = Modifier
                .padding(12.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4))) {
                FormView(totalPerHead)
            }

        }
    }
}


@Composable
fun HeaderView(totalPerHead:MutableState<Double>) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "$${"%.1f".format(totalPerHead.value)}", style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight(600)))
        }

    }
}

@Composable
fun FormView(totalPerHead:MutableState<Double>) {
    var userTotalValue by remember { mutableStateOf(0.0) }
    var totalPersons by remember { mutableStateOf("1") }
    var tipValue by remember { mutableFloatStateOf(0f) }
    var tipPriceValue by remember { mutableDoubleStateOf(0.0) }
    var totalValue by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userTotalValue.toString(), onValueChange = {
                userTotalValue = it.toDouble()
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
                    if(totalPersons.toInt() > 1){
                        totalPersons = (totalPersons.toInt() - 1).toString()
                        totalPerHead.value = calculateTotalValue(userTotalValue = userTotalValue, tipTotal = tipPriceValue, numberOfPerson = totalPersons.toInt())
                       // onAction(totalPerHead.value)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = totalPersons, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(600)))
                Spacer(modifier = Modifier.width(8.dp))
                RounderIconButton(Icons.Filled.AddCircle) {
                    totalPersons = (totalPersons.toInt() + 1).toString()
                    totalPerHead.value = calculateTotalValue(userTotalValue = userTotalValue, tipTotal = tipPriceValue, numberOfPerson = totalPersons.toInt())
                   // onAction(totalPerHead.value)
                }


            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Tip ${(tipValue * 100).toInt()}%")
            tipPriceValue = (userTotalValue * (tipValue * 100).toInt()) / 100
            Text(text = "$${tipPriceValue}")
            totalPerHead.value = calculateTotalValue(userTotalValue = userTotalValue, tipTotal = tipPriceValue, numberOfPerson = totalPersons.toInt())
        }

        Slider(modifier = Modifier.padding(horizontal = 8.dp),
            steps = 9,
            value = tipValue, onValueChange = {newValue ->
            tipValue = newValue

            })

    }
}

fun calculateTotalValue(userTotalValue:Double,tipTotal:Double,numberOfPerson:Int):Double{
    val total = userTotalValue + tipTotal;
    return (total / numberOfPerson)
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