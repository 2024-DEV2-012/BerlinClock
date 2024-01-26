package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.anonymous.berlinclock.MainActivity
import com.anonymous.berlinclock.core.util.TestTags
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.navutils.BerlinClockNavGraph
import com.anonymous.berlinclock.ui.theme.BerlinClockTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ClockScreenTest {

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        setContentForActivity()
    }

    private fun setContentForActivity() {
        composeRule.activity.setContent {
            BerlinClockTheme {
                BerlinClockNavGraph()
            }
        }
    }

    @Test
    fun validateTopBarIsVisible() {
        composeRule.onNodeWithTag(TestTags.TOP_BAR).assertIsDisplayed()
    }
}