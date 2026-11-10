package com.example.lab1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {
    private val _MusicScreenText = MutableStateFlow("Жодних пісень не відтворюється")
    val MusicScreenText : StateFlow<String> = _MusicScreenText.asStateFlow()
    fun updateMusicScreenText(newString: String){
        _MusicScreenText.value = newString
    }

    private val _PlaylistScreenText = MutableStateFlow("Ніякого плейлисту не завантажено")
    val PlaylistScreenText : StateFlow<String> = _PlaylistScreenText.asStateFlow()
    fun updatePlaylistScreenText(newString : String){
        _PlaylistScreenText.value = newString
    }
}