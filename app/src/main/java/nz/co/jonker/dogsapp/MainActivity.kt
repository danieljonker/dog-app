package nz.co.jonker.dogsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import nz.co.jonker.breedlist.presentation.BreedListScreen
import nz.co.jonker.design.theme.DogsAppTheme
import nz.co.jonker.dogsapp.di.initialiseKoin

//todo: move to feature modules
@Serializable
object BreedList

@Serializable
object BreedView

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        typically this would be done in a splash screen or application class,
        but to save time for this test I've just put it here
        * */
        initialiseKoin(application)

        enableEdgeToEdge()
        setContent {
            DogsAppTheme() {
                val navController = rememberNavController()
                Scaffold(
                    contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
                    modifier = Modifier.fillMaxSize()
                ) {

                    NavHost(
                        navController,
                        startDestination = BreedList,
                    ) {
                        composable<BreedList> { BreedListScreen() }
//                        composable<BreedView> { BreedViewScreen(...) }
                    }
                }
            }
        }
    }
}

