package nz.co.jonker.breedlist.presentation

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import nz.co.jonker.breedlist.utils.TestData
import nz.co.jonker.design.theme.DogsAppTheme
import org.junit.Rule
import org.junit.Test

class BreedListScreenSnapshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        theme = "android:Theme.Material.Light.NoActionBar",
        showSystemUi = false,
    )

    @Test
    fun `snapshot breed list screen`() {
        paparazzi.snapshot() {
            DogsAppTheme {
                BreedListScreenUi(
                    items = TestData.mappedResponse,
                    onBreedClickedHandler = {},
                )
            }
        }
    }
}
