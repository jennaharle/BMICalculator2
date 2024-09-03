package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import java.text.DecimalFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BmiCalculator()
                }
            }
        }
    }
}

@Composable
fun BmiCalculator() {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    val height = heightInput.toFloatOrNull() ?: 0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi = if (height > 0 && weight > 0) weight / (height / 100 * height / 100) else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bmi_calculator),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = heightInput,
            onValueChange = { heightInput = it },
            label = { Text(stringResource(id = R.string.height_cm)) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text(stringResource(id = R.string.weight_kg)) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* BMI lasketaan reaaliajassa, joten erillistä toimintoa ei tarvita */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.calculate_bmi))
        }

        Spacer(modifier = Modifier.height(16.dp))

        val formattedBmi = DecimalFormat("#.##").format(bmi).replace(',', '.')
        Text(
            text = stringResource(id = R.string.bmi_result, formattedBmi),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMICalculatorTheme {
        BmiCalculator()
    }
}