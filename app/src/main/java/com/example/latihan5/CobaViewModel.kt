package com.example.latihan5

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.latihan5.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTelp: String by mutableStateOf("")
        private set
    var email: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
        private set
    var status: String by mutableStateOf("")
        private set
    var alamat: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm: String, tlp: String, jk: String, sts: String, almt: String, mail: String){
        namaUsr = nm;
        noTelp = tlp;
        email = mail;
        jenisKl = jk;
        status = sts;
        alamat = almt;
    }

    fun setjenisK(pilihJK: String){
        _uiState.update{currentState -> currentState.copy(sex = pilihJK)}
    }

    fun setSts(pilihSts: String){
        _uiState.update{currentState -> currentState.copy(sts = pilihSts)}
    }
}