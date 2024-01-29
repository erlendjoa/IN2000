package no.uio.ifi.in2000.erlenjoa.oblig1.ui.palindrome


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.erlenjoa.oblig1.isPalindrome

@Composable
fun PalindromeScreen(func: () -> Unit) {
    PalindromeChecker(func)
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(func: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val keyCon = LocalSoftwareKeyboardController.current
        var result: Boolean by remember {mutableStateOf(false)}
        var text by remember {mutableStateOf("")}

        Column(

        ) {
            TextField(
                modifier = Modifier
                    .padding(10.dp)
                    .width(220.dp),
                value = text,
                onValueChange = { text = it},
                label = { Text("Skriv inn palindrom:") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyCon?.hide();
                        result = isPalindrome(text)
                    }
                )
            )

            Row() {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .width(100.dp)
                ) {
                    Text(
                        text = result.toString().uppercase(),
                        fontSize = 24.sp
                    )
                }

                Button(
                    onClick = {
                        keyCon?.hide();
                        result = isPalindrome(text)
                              },
                    modifier = Modifier
                        .height(35.dp)
                ) {
                    Text("CHECK")
                }
            }
        }
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
                Text("Go to UnitConverter")
        }
    }
}