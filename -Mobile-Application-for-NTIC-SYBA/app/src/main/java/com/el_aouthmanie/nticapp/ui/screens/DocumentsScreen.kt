package com.el_aouthmanie.nticapp.ui.screens

import android.content.Intent
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

@Composable
fun DocumentsScreen(viewModel: DocumentsViewModel) {
    val documents by viewModel.documents.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val filteredDocs = documents.filter { it.name.contains(searchQuery, ignoreCase = true) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search documents") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { /* handle search */ })
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(filteredDocs.size) {
                val document = filteredDocs[it]
                DocumentItem(
                    document,
                    onDownload = { coroutineScope.launch { viewModel.downloadDocument(document) } },
                    onOpen = {
                        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), document.name)
                        if (file.exists()) {
                            val uri = FileProvider.getUriForFile(context, "com.el_aouthmanie.istanticapp.fileprovider", file)
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                setDataAndType(uri, "application/pdf")
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            context.startActivity(intent)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DocumentItem(document: Document, onDownload: () -> Unit, onOpen: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = document.name,
            modifier = Modifier.weight(1f).clickable{
                onOpen()
            }
        )
        if (!document.isDownloaded) {
            Button(onClick = onDownload) {
                Text("Download")
            }
        }
    }
}

data class Document(val name: String, val url: String, val isDownloaded: Boolean)

class DocumentsViewModel {
    private val _documents = MutableStateFlow(listOf<Document>())
    val documents: StateFlow<List<Document>> get() = _documents

    init {
        loadDocuments()
    }

    fun loadDocuments() {
        _documents.value = listOf(
            Document("Report 1", "https://ec-bievres.ac-versailles.fr/IMG/pdf/test_pdf.pdf", false),
            Document("Report 2", "https://example.com/report2.pdf", false)
        )
    }

    suspend fun downloadDocument(document: Document) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), document.name)
        withContext(Dispatchers.IO) {
            URL(document.url).openStream()
        }.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        _documents.value = _documents.value.map {
            if (it.name == document.name) it.copy(isDownloaded = true) else it
        }
    }
}
