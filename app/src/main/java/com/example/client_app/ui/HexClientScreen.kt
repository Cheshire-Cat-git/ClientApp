package com.example.client_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client_app.data.HexCode
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HexClientScreen(viewModel: HexViewModel) {
    val codes by viewModel.hexCodes.collectAsState()
    val isGenerating by viewModel.isServiceGenerating.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HEX Client") },
                actions = {
                    IconButton(onClick = { viewModel.deleteAllCodes() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear All")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            StatusIndicator(isGenerating)
            
            Spacer(modifier = Modifier.height(16.dp))

            ControlButtons(
                onStart = { viewModel.startGenerating() },
                onStop = { viewModel.stopGenerating() },
                isGenerating = isGenerating
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Received Codes:", style = MaterialTheme.typography.titleMedium)
            
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(codes, key = { it.id }) { code ->
                    HexCodeCard(
                        hexCode = code,
                        onDelete = { viewModel.deleteCode(code.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun StatusIndicator(isGenerating: Boolean) {
    val color = if (isGenerating) Color.Green else Color.Gray
    val text = if (isGenerating) "GENERATING" else "IDLE"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(12.dp),
            shape = androidx.compose.foundation.shape.CircleShape,
            color = color
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text("Service Status: $text", fontSize = 18.sp)
    }
}

@Composable
fun ControlButtons(
    onStart: () -> Unit,
    onStop: () -> Unit,
    isGenerating: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onStart,
            modifier = Modifier.weight(1f),
            enabled = !isGenerating,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Start")
        }
        Button(
            onClick = onStop,
            modifier = Modifier.weight(1f),
            enabled = isGenerating,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
        ) {
            Text("Stop")
        }
    }
}

@Composable
fun HexCodeCard(hexCode: HexCode, onDelete: () -> Unit) {
    val clipboardManager = LocalClipboardManager.current
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            clipboardManager.setText(AnnotatedString(hexCode.code))
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = hexCode.code,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = dateFormat.format(Date(hexCode.timestamp)),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
