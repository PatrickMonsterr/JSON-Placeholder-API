package com.example.jsonplaceholderapi.ui.screens.ProfileScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current

    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val imagePath by viewModel.imagePath.collectAsState()

    var firstNameInput by remember { mutableStateOf(firstName) }
    var lastNameInput by remember { mutableStateOf(lastName) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    var imageFile: File? = null

    selectedImageUri?.let { uri ->
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, "profile.jpg")
        inputStream?.use { input -> file.outputStream().use { output -> input.copyTo(output) } }
        imageFile = file
    }

    if (imageFile == null && imagePath?.isNotBlank() == true) {
        val file = File(imagePath)
        if (file.exists()) {
            imageFile = file
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Moje dane", style = MaterialTheme.typography.headlineMedium)

            TextField(
                value = firstNameInput.toString(),
                onValueChange = { firstNameInput = it },
                label = { Text("Imię") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = lastNameInput.toString(),
                onValueChange = { lastNameInput = it },
                label = { Text("Nazwisko") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text("Wybierz zdjęcie profilowe")
            }

            imageFile?.let { file ->
                Image(
                    painter = rememberAsyncImagePainter(file),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.saveUserData(
                        firstNameInput.toString(),
                        lastNameInput.toString(),
                        (imageFile?.absolutePath ?: imagePath).toString()
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Zapisz")
            }

            Spacer(Modifier.height(24.dp))

            if (firstName?.isNotBlank() == true || lastName?.isNotBlank() == true) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3C))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        imageFile?.let { file ->
                            Image(
                                painter = rememberAsyncImagePainter(file),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(end = 16.dp)
                            )
                        }

                        Column {
                            Text(
                                "$firstName $lastName",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                            if (imageFile != null) {
                                Text(
                                    "Zdjęcie: ${imageFile.absolutePath}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        imageFile?.takeIf { it.exists() }?.delete()
                        viewModel.clearUserData()
                        firstNameInput = ""
                        lastNameInput = ""
                        selectedImageUri = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Usuń profil", color = Color.White)
                }

            }
        }
    }
}
