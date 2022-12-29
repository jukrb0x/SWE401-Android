package com.brokenbrains.fitness.ui.screens.healthplus

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.brokenbrains.fitness.ui.screens.browse.components.BrowsePage

@Composable
fun ArticleWebViewer(navigateTo: (route: String) -> Unit, onBack: () -> Unit) {
    val webURL =
        "https://www.healthline.com/nutrition/27-health-and-nutrition-tips#TOC_TITLE_HDR_22"
    BrowsePage(title = "Healthline", navigateTo = navigateTo, onBack = { onBack() }) {
        ArticleWebIndex(webURL)
    }
}


@Composable
fun ArticleWebIndex(webSite: String) {
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
private fun DefaultPreview() {
    ArticleWebViewer(navigateTo = {}, onBack = {})
}