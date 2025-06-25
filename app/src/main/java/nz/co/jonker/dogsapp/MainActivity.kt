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
import nz.co.jonker.breedlist.presentation.BreedListScreen
import nz.co.jonker.breedview.presentation.BreedViewScreen
import nz.co.jonker.design.theme.DogsAppTheme
import nz.co.jonker.networking.BreedList
import nz.co.jonker.networking.BreedView
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DogsAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
                    modifier = Modifier.fillMaxSize()
                ) {

                    NavHost(
                        navController,
                        startDestination = BreedList,
                    ) {
                        composable<BreedList> {
                            BreedListScreen(
                                koinViewModel()
                            ) { navController.navigate(BreedView(it)) }
                        }
                        composable<BreedView> {
                            BreedViewScreen(
                                koinViewModel()
                            ) { navController.navigateUp() }
                        }
                    }
                }
            }
        }
    }
}

