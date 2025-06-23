package nz.co.jonker.design.components

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import nz.co.jonker.design.theme.DogsAppTheme
import org.junit.Test

class TopAppBarComponentSnapshotTest {


    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        theme = "android:Theme.Material.Light.NoActionBar",
        showSystemUi = false,
    )

    @Test
    fun `snapshot topAppBarComponent`() {
        paparazzi.snapshot() {
            DogsAppTheme {
                TopAppBarComponent("TopAppBarComponent")
            }
        }
    }

}