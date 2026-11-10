package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1.ui.theme.Lab1Theme
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                Lab1App()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun Lab1App() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.MUSIC) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when(currentDestination){
                AppDestinations.MUSIC ->
                    MusicScreen(
                        username = "user",
                        modifier = Modifier.padding(innerPadding)
                    )
                AppDestinations.PLAYLISTS ->
                    PlaylistScreen(
                        username = "user",
                        modifier = Modifier.padding(innerPadding)
                    )
            }


        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    MUSIC("MUSIC", R.drawable.ic_play),
    PLAYLISTS("PLAYLISTS", R.drawable.ic_playlist),
}

@Composable
fun MusicScreen(username: String,
                modifier: Modifier = Modifier,
                appViewModel: AppViewModel = viewModel()) {
    val song by appViewModel.MusicScreenText.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Привіт $username! $song",
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {appViewModel.updateMusicScreenText("Зараз відтворюється пісня!") }
        ) {
            Text(
                text = "Відтворити пісню"
            )
        }
    }
}

@Composable
fun PlaylistScreen(username: String,
                   modifier: Modifier = Modifier,
                   appViewModel: AppViewModel = viewModel()) {
    val playlist by appViewModel.PlaylistScreenText.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Привіт $username! $playlist",
        )
        Button(
            modifier = Modifier,
            onClick = {appViewModel.updatePlaylistScreenText("Плейлист завантажено") }
        ) {
            Text(
                text = "Завантажити плейлист"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MusicScreenPreview() {
    Lab1Theme {
        MusicScreen("user")
    }
}