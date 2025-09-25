package com.example.gallery

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import com.example.gallery.ui.theme.GalleryTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryScreen()
                }
            }
        }
    }
}
@Composable
fun GalleryContent(selectedImageUri: Uri?, onPickClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onPickClick) {
            Text(text = "📷 Chọn ảnh từ thư viện")
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (selectedImageUri != null) {
            // hiển thị ảnh từ URI (Coil)
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.size(250.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        } else {
            // Placeholder khi chưa chọn ảnh (preview safe)
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .background(Color(0xFFCCCCCC)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Chưa có ảnh")
            }
        }
    }
}

/** Composable chứa launcher (không dùng cho Preview) */
@Composable
fun GalleryScreen() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    GalleryContent(selectedImageUri) {
        // mở picker
        launcher.launch("image/*")
    }
}

/** Preview dùng GalleryContent (không có launcher) */
@Preview(showBackground = true)
@Composable
fun GalleryPreview() {
    // preview an toàn, truyền null + empty onPick
    GalleryContent(selectedImageUri = null, onPickClick = {})
}

