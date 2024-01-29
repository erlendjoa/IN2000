package no.uio.ifi.in2000.erlenjoa.oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import no.uio.ifi.in2000.erlenjoa.oblig1.ui.theme.Erlenjoa_oblig1Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.erlenjoa.oblig1.ui.palindrome.PalindromeScreen
import no.uio.ifi.in2000.erlenjoa.oblig1.ui.unitconverter.UnitConverterScreen

enum class ConverterUnits {
    OUNCE,
    CUP,
    GALLON,
    HOGSHEAD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Erlenjoa_oblig1Theme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController: NavHostController = rememberNavController()
                    val palindromePath: String = "palindrome"
                    val unitconverterPath: String = "unitconverter"

                    NavHost(
                        navController = navController,
                        startDestination = palindromePath
                    ) {
                        composable(route = palindromePath) {
                            PalindromeScreen({ navController.navigate(unitconverterPath) })
                        }
                        composable(route = unitconverterPath) {
                            UnitConverterScreen({ navController.navigate(palindromePath) })
                        }
                    }
                }
            }
        }
    }
}


