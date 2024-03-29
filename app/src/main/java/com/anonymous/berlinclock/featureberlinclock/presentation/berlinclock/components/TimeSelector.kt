package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.anonymous.berlinclock.R
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.TIME_DELIMITER
import com.anonymous.berlinclock.core.util.TIME_MAX_VALUE
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.anonymous.berlinclock.core.util.TIME_SELECTOR_INPUT_MAX_LENGTH
import com.anonymous.berlinclock.core.util.TestTags

@Composable
fun TimeSelector(
    showBerlinTime: (String) -> Unit
) {
    var selectedHour by remember { mutableStateOf("") }
    var selectedMinute by remember { mutableStateOf("") }
    var selectedSecond by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .semantics {
                contentDescription = TestTags.TIME_SELECTOR
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = selectedHour,
                onValueChange = {
                    if (it.isEmpty() ||
                        (it.isDigitsOnly() &&
                                it.length <= TIME_SELECTOR_INPUT_MAX_LENGTH &&
                                it.toInt() in TIME_MIN_VALUE..HOUR_MAX_VALUE)
                    ) {
                        selectedHour = it
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                placeholder = { Text(text = stringResource(id = R.string.hour)) },
                singleLine = true,
                modifier = Modifier
                    .width(80.dp)
                    .padding(2.dp)
                    .semantics {
                        contentDescription = TestTags.HOUR_SELECTOR
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = selectedMinute,
                onValueChange = {
                    if (it.isEmpty() ||
                        (it.isDigitsOnly() &&
                                it.length <= TIME_SELECTOR_INPUT_MAX_LENGTH &&
                                it.toInt() in TIME_MIN_VALUE..TIME_MAX_VALUE)
                    ) {
                        selectedMinute = it
                    }
                },
                placeholder = { Text(text = stringResource(id = R.string.minute)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier
                    .width(80.dp)
                    .padding(2.dp)
                    .semantics {
                        contentDescription = TestTags.MINUTE_SELECTOR
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = selectedSecond,
                onValueChange = {
                    if (it.isEmpty() ||
                        (it.isDigitsOnly() &&
                                it.length <= TIME_SELECTOR_INPUT_MAX_LENGTH &&
                                it.toInt() in TIME_MIN_VALUE..TIME_MAX_VALUE)
                    ) {
                        selectedSecond = it
                    }
                },
                placeholder = { Text(text = stringResource(id = R.string.second)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier
                    .width(80.dp)
                    .padding(2.dp)
                    .semantics {
                        contentDescription = TestTags.SECOND_SELECTOR
                    }
            )

        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                showBerlinTime("$selectedHour$TIME_DELIMITER$selectedMinute$TIME_DELIMITER$selectedSecond")
                focusManager.clearFocus()
            },
            modifier = Modifier
                .semantics {
                    contentDescription = TestTags.SHOW_BERLIN_TIME_BUTTON
                },
            enabled = selectedHour.isNotEmpty() &&
                    selectedMinute.isNotEmpty() &&
                    selectedSecond.isNotEmpty()

        ) {
            Text(stringResource(R.string.show_berlin_time))
        }
    }
}