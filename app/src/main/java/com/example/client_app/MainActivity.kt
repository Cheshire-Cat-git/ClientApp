package com.example.client_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.client_app.ui.HexClientScreen
import com.example.client_app.ui.HexViewModel
import com.example.client_app.ui.theme.ClientAppTheme

class MainActivity : ComponentActivity() {
    
    private val viewModel: HexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClientAppTheme {
                HexClientScreen(viewModel = viewModel)
            }
        }
    }
}
