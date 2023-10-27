package com.example.latihan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.latihan5.Data.DataForm
import com.example.latihan5.Data.DataSource
import com.example.latihan5.Data.DataSource.jenis
import com.example.latihan5.Data.DataSource.status
import com.example.latihan5.ui.theme.Latihan5Theme
import com.example.latihan5.ui.theme.PurpleGrey40


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {            //Memanggil fungsi onCreate saat activity dijalankan
        super.onCreate(savedInstanceState)
        setContent {                                                //Menetapkan tampilan komponen menggunakan tema "Latihan5Theme"
            Latihan5Theme {                                         // Menggunakan Surface sebagai wadah konten
                Surface(
                    modifier = Modifier.fillMaxSize(),              // Mengisi seluruh layar
                    color = MaterialTheme.colorScheme.background    //Menetapkan warna latarbelakang
                ) {
                    TampilLayout()                                  //Memanggil komponen TampilLayout
                }
            }
        }
    }
}


@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    Card(                                                                       //Menggunakan Card sebagai wadah utama dengan properti-modifier
        modifier = modifier,                                                    //Modifier digunakan untuk menentukan tampilan atau perilaku Card.
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)         // Mengatur elevasi tampilan
    ){
        Column(                                                                 //Column untuk menyusun elemen secara vertikal
            verticalArrangement = Arrangement.spacedBy(10.dp),                  //Menentukan jarak vertikal antar elemen
            horizontalAlignment = Alignment.CenterHorizontally,                 //Menyusun ekemen secara horizontal di tengah
            modifier = Modifier.padding(20.dp)                                  //Menambah padding(ruang sekitar) ke dalam column
        ){
            TampilForm()                                                        //Memanggil komponen TampilForm untuk ditampilkan dalam column
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){

    var textNama by remember { mutableStateOf("")}                      //Inisialisasi variable dengan menggunakan remember untuk menyimpan perubahan nilai
    var textTlp by remember { mutableStateOf("") }
    var textAlmt by remember { mutableStateOf("") }
    var textEmail by remember{ mutableStateOf("") }

    val context = LocalContext.current                                      //Mendapatkan konteks saat ini
    val dataForm: DataForm                                                  //Mendeklarasikan variable dataForm dari tipe DataForm
    val uiState by cobaViewModel.uiState.collectAsState()                   //Mengambil nilai state dari ViewModel
    dataForm = uiState                                                      //Menetapkan dataForm dengan nilai dari ViewModel

    Row(modifier = Modifier){
        Icon(painter = painterResource(id = R.drawable.back), contentDescription = null, modifier = Modifier.size(20.dp))
        Text(text = "Register", fontWeight = FontWeight.Bold)
    }

    Text(
        text = "Create Your Account",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Black
    )

    OutlinedTextField(                                                      //Menampilkan OutlinedTextField untuk input nama lengkap dengan label "Nama Lengkap"
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Nama Lengkap")},
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(                                                      //Menampilkan OutlinedTextField untuk input nomor telepon dengan label "Telepon"
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telpon")},
        onValueChange = {
            textTlp = it
        }
    )
    OutlinedTextField(                                                      //Menampilkan OutlinedTextField untuk input alamat dengan label "Alamat".
        value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Email")},
        onValueChange = {
            textEmail = it
        }
    )
    SelectJK(
        options = jenis.map { id -> context.resources.getString(id)},       //Membuat pilihan berdasaekan dara di "jenis" dan mengonversi mereka jadi teks menggunakan resources.
        onSelectionChanged = { cobaViewModel.setjenisK(it)}    )            //Menghubungkan perubahan pilihan jenis kelamin ke ViewModel saat terjadi perubahan

    SelectSTS(                                                               //Menampilkan pilihan jenis kelamin
        options = status.map { id -> context.resources.getString(id)},       //Membuat pilihan berdasaekan dara di "jenis" dan mengonversi mereka jadi teks menggunakan resources.
        onSelectionChanged = { cobaViewModel.setSts(it)}    )

    OutlinedTextField(                                                      //Menampilkan OutlinedTextField untuk input alamat dengan label "Alamat".
        value = textAlmt,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "Alamat")},
        onValueChange = {
            textAlmt = it
        }
    )
    Button(                                                                 //Membuat tombol untuk mengirimkan data
        modifier = Modifier.fillMaxWidth(),                                 //Menentukan lebar tombol agar mengisi lebar layar penuh
        onClick = {
            cobaViewModel.BacaData(textNama, textTlp, textEmail, dataForm.sex, dataForm.sts, textAlmt)   //Menggunakan ViewModel untuk mengambil data yang dimasukkan dan mengirimkannya saat tombol di klik.
        }
    ) {
        Text(
            text = stringResource(R.string.register),                         //Menampilkan teks pada tombol dari sumber daya string
            fontSize = 16.sp                                                //Menentukan ukuran font teks pada tombol
        )
    }
    Spacer(modifier = Modifier.height(35.dp))                              //Menampilkan hasil data yang telah dimasukkan
    TextHasil(
        namanya = cobaViewModel.namaUsr,                                    //Menampilkan nama pengguna dari ViewModel
        telponnya = cobaViewModel.noTelp,
        emailnya = cobaViewModel.email,
        jenisnya = cobaViewModel.jenisKl,
        statusnya = cobaViewModel.status,
        alamatnya = cobaViewModel.alamat
    )
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }                            //Mendeklarasikan variable selectedValue untuk menyimpan nilai yang dipilih
        Text(
            text = "Jenis Kelamin: ")
        Row(modifier = Modifier.padding(5.dp)) {                                     //Membuat tampilan dalam bentuk kolom
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
}

@Composable
fun SelectSTS(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }                            //Mendeklarasikan variable selectedValue untuk menyimpan nilai yang dipilih
    Text(
        text = "Status: ")
    Row(modifier = Modifier) {                                     //Membuat tampilan dalam bentuk kolom
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun TextHasil (namanya: String, telponnya: String, emailnya: String, jenisnya: String, statusnya: String, alamatnya: String,){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text (
            text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text (
            text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text (
            text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text (
            text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text (
            text = "Status : " + statusnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text (
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    Latihan5Theme{
        TampilLayout()
    }
}