package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(color = Color.Red),
//                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.background(color = Color.Gray)) {
                        TipCalculatorScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TipCalculatorScreen() {
    var serviceCostAmountInput by remember { mutableStateOf("") }
//    var tipPercentage by remember { mutableStateOf("") }
    val amount = serviceCostAmountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)
    val randomBillAmount = 0;

    Column(
        modifier = Modifier
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.tip_calculator_heading),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        Button(
            elevation = ButtonDefaults.elevation(defaultElevation = 20.dp),
            onClick = { serviceCostAmountInput = (10..300).random().toString() }

        ) {
            Text(text = "Generate a random Bill Amount")
        }
        Spacer(Modifier.height(16.dp))
        EditServiceCostField(
            value = serviceCostAmountInput,
            onValueChange = { serviceCostAmountInput = it }
        )
//        Spacer(Modifier.height(24.dp))
//        Text(
//            text = stringResource(R.string.tip_amount, tip),
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold
//        )

//  buttons area
        Spacer(Modifier.height(24.dp))
        Row {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

            ) {
                Text(text = "10%")
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

            ) {
                Text(text = "15%")
            }
        }
        Row {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

            ) {
                Text(text = "18%")
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text(text = "20%")
            }
        }
//  end of buttons area

//  total area
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.bill_amount, serviceCostAmountInput),
//            text = stringResource(R.string.bill_amount, String.format("%.2f", "10")),
//            text = stringResource(R.string.bill_amount, NumberFormat.getCurrencyInstance().format(serviceCostAmountInput)),
//            text = fillBillAmount(serviceCostAmountInput),
//            text = NumberFormat.getCurrencyInstance().format(serviceCostAmountInput),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
//            text = stringResource(R.string.tip_amount, 10.0, tip),
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.height(4.dp))
        Text(
//            text = stringResource(R.string.total, tip, serviceCostAmountInput), need to reata a function to add values
//            text = stringResource(R.string.total, sumUp(tip, serviceCostAmountInput),
            text = stringResource(R.string.total, tip, serviceCostAmountInput),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
//  end of totals area
    }
}

//private fun fillBillAmount(v: String): String {
//    println("received: " + v)
//    return NumberFormat.getCurrencyInstance().format(v)
//}

//private fun sumUp(v1: String, v2: String ): String {
//    println("received: " + v)
//    val total =
//    return String.format("%.2f", v);
//}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditServiceCostField(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.service_cost)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        TipCalculatorScreen()
    }
}
