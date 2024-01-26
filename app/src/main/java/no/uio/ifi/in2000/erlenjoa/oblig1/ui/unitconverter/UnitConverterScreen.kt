package no.uio.ifi.in2000.erlenjoa.oblig1.ui.unitconverter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.erlenjoa.oblig1.ConverterUnits
import no.uio.ifi.in2000.erlenjoa.oblig1.converter

@Composable
fun UnitConverterScreen(func: () -> Unit) {
    UnitConverter(func)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UnitConverter(func: () -> Unit) {

    val keyCon = LocalSoftwareKeyboardController.current
    var result: String by remember { mutableStateOf("0") }
    var converterText: String by remember { mutableStateOf("") }
    val options = listOf(
        ConverterUnits.OUNCE.toString(),
        ConverterUnits.GALLON.toString(),
        ConverterUnits.CUP.toString(),
        ConverterUnits.HOGSHEAD.toString()
    )
    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedOptionText: String by remember { mutableStateOf(options[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(

        ) {
            TextField(
                modifier = Modifier
                    .width(150.dp)
                    .padding(10.dp),

                value = converterText,
                onValueChange = {converterText = it},
                label = { Text("Convert:") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyCon?.hide();
                        result = converter(converterText.toInt(), enumValueOf(selectedOptionText)).toString()
                    }
                )
            )

            ExposedDropdownMenuBox(
                modifier = Modifier
                    .width(150.dp)
                    .padding(10.dp),
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Unit:") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = result,
            color = Color.Black,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(
            onClick = { func() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Go to Palindrome")
        }
    }
}
