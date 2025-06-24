package nz.co.jonker.breedlist.presentation

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import nz.co.jonker.breedlist.domain.BreedListItem
import nz.co.jonker.design.theme.DogsAppTheme
import org.junit.Rule
import org.junit.Test

class BreedRowSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        theme = "android:Theme.Material.Light.NoActionBar",
        showSystemUi = false,
    )

    @Test
    fun `snapshot breedRow with no subbreeds`() {
        paparazzi.snapshot() {
            DogsAppTheme {
                BreedRow(
                    breed = BreedListItem("australian"),
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun `snapshot breedRow with some subbreeds`() {
        paparazzi.snapshot() {
            DogsAppTheme {
                BreedRow(
                    breed = BreedListItem("australian", subBreeds = listOf("kelpie", "shepherd")),
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun `snapshot breedRow with lots of subbreeds`() {
        paparazzi.snapshot() {
            DogsAppTheme {
                BreedRow(
                    breed = BreedListItem("terrier", subBreeds = terrierSubBreeds),
                    onClick = {}
                )
            }
        }
    }
}
