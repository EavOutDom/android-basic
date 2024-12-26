package com.example.androidbasic

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.androidbasic.ui.theme.AndroidBasicTheme

class MainActivity : ComponentActivity() {
    private val airplaneModeReceiver = AirplaneModeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        registerReceiver(airplaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
        }

        setContent {
            AndroidBasicTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button( onClick = {
                        Intent(applicationContext, RunningService::class.java).also {
                            it.action = RunningService.ServiceState.STARTED.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Start Service")
                    }
                    Button( onClick = {
                        Intent(applicationContext, RunningService::class.java).also {
                            it.action = RunningService.ServiceState.STOPPED.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Stop Service")
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(airplaneModeReceiver)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello test changed $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidBasicTheme {
        Greeting("Android")
    }
}