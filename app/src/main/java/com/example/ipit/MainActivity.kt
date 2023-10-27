package com.example.ipit

//import fuel.Request

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ipit.ui.theme.IpitTheme
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.schedule
import java.util.*
import android.webkit.*
import androidx.compose.runtime.*
import android.content.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun Shed_page() {
    val webView = WebView(LocalContext.current).apply {
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        webViewClient = WebViewClient()
    }
    val htmldata = """<html><body style="background-color: #000; height:80vh;"><iframe style="width: 100vw; height: 2000px;" src="http://192.168.1.47:5000/get_schedule"></body></html>"""
    Column(Modifier.fillMaxSize()) {
        AndroidView(factory = { webView }) { view ->
            view.loadDataWithBaseURL(
                "http://192.168.1.47:5000/get_schedule",
                htmldata,
                "text/html",
                "UTF-8",
                null
            )
        }
    }
}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build()
        )
    super.onCreate(savedInstanceState)


    setContent {
        Shed_page()
        }
    }
}






//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
////    Text(
////        text = "Hello $name!",
////        modifier = modifier
////    )
//    TextField(
//        value = "Login",
//        onValueChange = {}
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    IpitTheme {
//        Greeting("Android")
//    }
//}

