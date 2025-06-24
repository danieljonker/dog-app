@file:OptIn(ExperimentalMaterial3Api::class)

package nz.co.jonker.design.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import nz.co.jonker.design.theme.DogsAppTheme

@Composable
fun TopAppBarComponent(text: String, onUpNavClicked: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        modifier = Modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (onUpNavClicked != null) {
                IconButton(onClick = onUpNavClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back to the previous screen"
                    )
                }
            }
        },
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    DogsAppTheme {
        TopAppBarComponent("This is a top bar")
    }
}
