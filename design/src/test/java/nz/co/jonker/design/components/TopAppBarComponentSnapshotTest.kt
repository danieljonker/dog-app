package nz.co.jonker.design.components

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import nz.co.jonker.design.theme.DogsAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class TopAppBarComponentSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6,
        theme = "android:Theme.Material.Light.NoActionBar",
        showSystemUi = false,
    )

    @Test
    fun `snapshot topAppBarComponent`(
        @TestParameter(
            "",
            "TopAppBarComponent",
            "really long string do do do do do do do do etc.",
        ) toolbarText: String
    ) {
        paparazzi.snapshot() {
            DogsAppTheme {
                TopAppBarComponent(toolbarText)
            }
        }
    }
}
