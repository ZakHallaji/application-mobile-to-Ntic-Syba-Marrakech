//package com.el_aouthmanie.istanticapp.ui.screens
//
//import android.annotation.SuppressLint
//import android.graphics.Bitmap
//import android.util.Log
//import android.webkit.WebChromeClient
//import android.webkit.WebResourceError
//import android.webkit.WebResourceRequest
//import android.webkit.WebResourceResponse
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//
//import androidx.compose.material3.ExperimentalMaterial3Api
//
//import androidx.compose.material3.LinearProgressIndicator
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarDuration
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.viewinterop.AndroidView
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
////@SuppressLint("", "SetJavaScriptEnabled")
//@Composable
//fun ScheduleScreen(modifier: Modifier = Modifier) {
//    var isLoading by remember { mutableStateOf(true) }
//    var selectedGroup by remember { mutableStateOf("AM201") }
//    val webViewState = remember { mutableStateOf<WebView?>(null) }
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//
//    val erroraqured = remember {
//        mutableStateOf(false)
//    }
//
//    val url = "http://eplanner-syba.somee.com/accueil_grp.aspx?grp=$selectedGroup"
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("AHA") },
//                actions = {
//                    Text(text = "out of date")
//                }
//            )
//        },
//        snackbarHost = {
//            SnackbarHost(hostState = snackbarHostState)
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            if (isLoading) {
//                LinearProgressIndicator(
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//            AndroidView(
//                factory = { context ->
//                    WebView(context).apply {
//                        settings.javaScriptEnabled = true
//                        settings.setSupportZoom(true)
//                        settings.builtInZoomControls = true
//                        settings.displayZoomControls = false
//
//                        webViewClient = object : WebViewClient() {
//                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                                super.onPageStarted(view, url, favicon)
//                                view?.evaluateJavascript(
//                                    """
//            document.documentElement.style.visibility = 'hidden';
//            """.trimIndent(), null
//                                )
//                            }
//
//                            override fun onPageFinished(view: WebView?, url: String?) {
//                                super.onPageFinished(view, url)
//                                view?.evaluateJavascript(
//                                    """
//                                        (function() {
//                                            let style = document.createElement("style");
//                                            style.innerHTML = `
//                                                table {
//                                                    width: 100%;
//                                                    border-collapse: collapse;
//                                                    text-align: center;
//                                                    font-family: Arial, sans-serif;
//                                                    background-color: #f8f9fa;
//                                                    border-radius: 8px;
//                                                    overflow: hidden;
//                                                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
//                                                }
//
//                                                th, td {
//                                                    border: 1px solid #com.el_aouthmanie.nticapp.ui.screens.homeScreen.components.ddd;
//                                                    padding: 10px;
//                                                    font-size: 14px;
//                                                }
//
//                                                th {
//                                                    background-color: #007bff;
//                                                    color: white;
//                                                    font-weight: bold;
//                                                }
//
//                                                td:first-child {
//                                                    background-color: #e9ecef;
//                                                    font-weight: bold;
//                                                    width: 10%;
//                                                }
//
//                                                .plein {
//                                                    background-color: #ffc107;
//                                                    font-weight: bold;
//                                                    color: #000;
//                                                    border-left: 3px solid #ff9800;
//                                                }
//
//                                                .vide {
//                                                    background-color: #f0f0f0;
//                                                    color: #bbb;
//                                                }
//
//                                                a {
//                                                    text-decoration: none;
//                                                    color: #000;
//                                                    font-weight: bold;
//                                                }
//
//                                                a:hover {
//                                                    text-decoration: underline;
//                                                }
//
//                                                #nbHeure {
//                                                    margin-top: 10px;
//                                                    font-size: 16px;
//                                                    font-weight: bold;
//                                                    color: #333;
//                                                    text-align: right;
//                                                    padding-right: 20px;
//                                                }
//
//                                                /* Responsive design */
//                                                @media (max-width: 768px) {
//                                                    td, th {
//                                                        font-size: 12px;
//                                                        padding: 6px;
//                                                    }
//                                                }
//                                            `;
//                                            document.head.appendChild(style);
//                                        })();
//
//            (function() {
//                var elementToKeep = document.getElementById("emp");
//                if (!elementToKeep) {
//                    console.warn("Element with the given ID not found!");
//                    return;
//                }
//                document.body.innerHTML = "";
//                document.body.appendChild(elementToKeep);
//                document.body.style.backgroundColor = "#000000";
//                document.documentElement.style.visibility = 'visible';
//            })();
//            """.trimIndent(), null
//
//
//                                )
//
//                                val jsCode = """
//                    (function() {
//                        return new Promise(function(resolve, reject) {
//                            $.ajax({
//                                method: "POST",
//                                url: "Service1.asmx/ListeSeancesGrp",
//                                dataType: "json",
//                                contentType: "application/json; charset=utf-8",
//                                data: JSON.stringify({ user: "AM201", pass: "AM201", groupe: "AM201", periode: "17/02/2025" }),
//                                success: function(result) {
//                                    resolve(JSON.stringify(result.d)); // Return only the data
//                                },
//                                error: function() {
//                                    reject("Error fetching data");
//                                }
//                            });
//                        });
//                    })();
//                """.trimIndent()
//
//                                // Execute JavaScript in WebView
//                                view?.evaluateJavascript(jsCode) { result ->
//                                    Log.d("f",result)
//                                }
//
//                                isLoading = false
//                            }
//
//                            override fun onReceivedError(
//                                view: WebView?,
//                                request: WebResourceRequest?,
//                                error: WebResourceError?
//                            ) {
//                                super.onReceivedError(view, request, error)
//                                erroraqured.value = true
//                                scope.launch {
//                                    snackbarHostState.showSnackbar(
//                                        duration = SnackbarDuration.Long,
//                                        message = "error has aqured"
//                                    )
//                                }
//                                Log.d("f",error.toString())
//                            }
//
//                        }
//
//                        loadUrl(url)
//                        webViewState.value = this
//                    }
//                },
//                modifier = Modifier.fillMaxSize(),
//
//            )
//        }
//
//
//    }
//
//}
