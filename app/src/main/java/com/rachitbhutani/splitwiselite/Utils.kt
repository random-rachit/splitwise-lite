package com.rachitbhutani.splitwiselite

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

fun Fragment.setComposeContent(content: @Composable () -> Unit) =
    ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                content.invoke()
            }
        }
    }