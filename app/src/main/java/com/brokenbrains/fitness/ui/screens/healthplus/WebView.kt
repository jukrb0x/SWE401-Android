package com.brokenbrains.fitness.ui.screens.healthplus

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewer() {
    val webURL = "https://www.healthline.com/nutrition/27-health-and-nutrition-tips#TOC_TITLE_HDR_22"
    WebIndex(webURL)
}

@Composable
fun WebIndex(webSite: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(webSite)
        }
    }, update = {
        it.loadUrl(webSite)
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WebViewer()
}