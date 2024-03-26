//package signup
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.pdf.PdfRenderer
//import android.os.ParcelFileDescriptor
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavController
//import java.io.File
//import java.io.FileOutputStream
//
//fun copyAssetToCache(context: Context, assetFileName: String): File {
//    val file = File(context.cacheDir, assetFileName)
//    if (!file.exists()) {
//        context.assets.open(assetFileName).use { inputStream ->
//            FileOutputStream(file).use { outputStream ->
//                inputStream.copyTo(outputStream)
//            }
//        }
//    }
//    return file
//}
//
//fun renderPdfPageToBitmap(file: File, pageNumber: Int): Bitmap? {
//    try {
//        ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY).use { descriptor ->
//            PdfRenderer(descriptor).use { renderer ->
//                if (pageNumber in 0 until renderer.pageCount) {
//                    val page = renderer.openPage(pageNumber)
//                    val bitmap =
//                        Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
//                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//                    page.close()
//                    return bitmap
//                }
//            }
//        }
//    } catch (e: Exception) {
//        Log.e("PdfRenderer", "Error rendering PDF page", e)
//    }
//    return null
//}
//
//@Composable
//fun DisplayPdf(assetFileName: String, context: Context = LocalContext.current) {
//    val pdfFile = remember { copyAssetToCache(context, assetFileName) }
//    val pageCount = remember { mutableIntStateOf(0) }
//
//    // Determine the number of pages in the background
//    LaunchedEffect(pdfFile) {
//        ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY).use { descriptor ->
//            PdfRenderer(descriptor).use { renderer ->
//                pageCount.intValue = renderer.pageCount
//            }
//        }
//    }
//
//    LazyColumn {
//        items(pageCount.intValue) { pageNumber ->
//            val bitmap = remember { mutableStateOf<Bitmap?>(null) }
//
//            // Load each page bitmap asynchronously
//            LaunchedEffect(pageNumber) {
//                bitmap.value = renderPdfPageToBitmap(pdfFile, pageNumber)
//            }
//
//            bitmap.value?.let {
//                Image(
//                    bitmap = it.asImageBitmap(), contentDescription = "PDF Page $pageNumber",
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun PdfWebView(
//    navController: NavController
//) {
//
//    DisplayPdf(assetFileName = "privacy_policy.pdf")
//
//
////    AndroidView(factory = { context ->
////        WebView(context).apply {
////            webViewClient = WebViewClient() // Use default web view client
////            settings.javaScriptEnabled = true // Enable JavaScript
////            val privacyPolicy = "privacy_policy.pdf"
////            // Construct the URL for the Google Docs Viewer with the asset file URL
////            val assetUrl = "file:///android_asset/${privacyPolicy}"
////            val googleDocsUrl = "https://docs.google.com/viewer?url=$assetUrl"
////
////            // Load the URL
////            loadUrl(googleDocsUrl)
////        }
////    })
//
//    //if impactor then no need the list, Just need to show the Project heroes and Country manager
////    // Initialize WebView
////    val context = LocalContext.current
////    val webView = WebView(context).apply {
////        webViewClient = WebViewClient() // Handles redirects inside the WebView
////        settings.allowFileAccess = true
////    }
////
////    // Prepare the file URL
////    val fileUrl = "file:///android_asset/$privacy_policy.pdf"
////
////    // Use Google Docs Viewer to display the PDF
////    val googleDocsUrl = "http://docs.google.com/gview?embedded=true&url=$fileUrl"
////
////    AndroidView(factory = { webView }) { webView ->
////        webView.loadUrl(googleDocsUrl)
////    }
////}
////
/////**
//// * Copies a file from the assets folder to the app's internal storage.
//// *
//// * @param context The application context.
//// * @param fileName The name of the file in the assets folder.
//// * @return The path to the copied file in the app's internal storage.
//// */
////fun copyFileToInternalStorage(context: Context, fileName: String): String {
////    val assetManager = context.assets
////    val outFile = File(context.filesDir, fileName)
////    if (!outFile.exists()) {
////        try {
////            val inStream = assetManager.open(fileName)
////            val outStream = FileOutputStream(outFile)
////            val buffer = ByteArray(1024)
////            var read: Int
////            while (inStream.read(buffer).also { read = it } != -1) {
////                outStream.write(buffer, 0, read)
////            }
////            inStream.close()
////            outStream.flush()
////            outStream.close()
////        } catch (e: IOException) {
////            e.printStackTrace()
////        }
////    }
////    return outFile.path
//}