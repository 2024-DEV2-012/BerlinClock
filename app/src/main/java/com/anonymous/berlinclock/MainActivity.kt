package com.anonymous.berlinclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.navutils.BerlinClockNavGraph
import com.anonymous.berlinclock.ui.theme.BerlinClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BerlinClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClockUI()
                }
            }
        }
    }
}

@Composable
fun ClockUI() {
    BerlinClockNavGraph()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BerlinClockTheme {
        ClockUI()
    }
}