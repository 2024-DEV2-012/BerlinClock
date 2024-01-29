package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anonymous.berlinclock.R
import com.anonymous.berlinclock.core.util.TestTags
import com.anonymous.berlinclock.core.util.TestTags.TOP_BAR
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.components.BerlinClock
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.components.TimeSelector
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.components.ToggleButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClockScreen(
    state: StateFlow<ClockState>
) {
    val clockState by state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag(TOP_BAR)
                        )
                    },
                )
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding),
                contentAlignment = Alignment.TopCenter

            ) {
                var showTimeSelector by remember { mutableStateOf(false) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    ToggleButton() { isToggleOn ->
                        showTimeSelector = !isToggleOn
                    }
                    if (showTimeSelector) {
                        TimeSelector()
                    }
                    NormalTime()
                    BerlinClock(clockState = clockState)
                }
            }
        }
    }
}

@Composable
fun NormalTime() {
    Text(
        modifier = Modifier
            .padding(15.dp)
            .testTag(TestTags.NORMAL_TIME),
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
        text = "Time",
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun ClockPreview() {
    ClockScreen(
        state = MutableStateFlow(
            ClockState()
        )
    )
}
