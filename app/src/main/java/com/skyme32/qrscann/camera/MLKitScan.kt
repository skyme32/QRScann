package com.skyme32.qrscann.camera

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.skyme32.qrscann.ui.component.ScanCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MLKitScan(barcodeView: BarcodeView = viewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val liveBarcode: State<Barcode?> = barcodeView.barcode.observeAsState(null)

    var skipHalfExpanded by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(16.dp),
        sheetContent = {
            ScanCard(
                barcode = liveBarcode.value,
                context = context
            )
        }
    ) {
        CameraRecognitionView(
            context = context,
            lifecycleOwner = lifecycleOwner,
            extractedText = liveBarcode as MutableState<Barcode?>,
            state = state,
            scope = scope
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun CameraRecognitionView(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    extractedText: MutableState<Barcode?>,
    state: ModalBottomSheetState,
    scope: CoroutineScope,
    ) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProvider = cameraProviderFuture.get()
    val scanner = remember { BarcodeScanning.getClient() }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    cameraProviderFuture.cancel(true)

    Box {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                cameraProviderFuture.addListener({
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .apply {
                            setAnalyzer(
                                cameraExecutor,
                                ObjectDetectorImageAnalyzer(scanner, extractedText, state, scope)
                            )
                        }
                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageAnalysis,
                        preview
                    )
                }, executor)

                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )
    }
}

class BarcodeView : ViewModel() {
    private var _barcode: MutableLiveData<Barcode?> = MutableLiveData(null)
    val barcode: LiveData<Barcode?> = _barcode

    fun setBarcode(barcode: Barcode?) {
        _barcode.value = barcode
    }
}

@OptIn(ExperimentalMaterialApi::class)
class ObjectDetectorImageAnalyzer(
    private val scanner: BarcodeScanner,
    private var extractedText: MutableState<Barcode?>,
    private var state: ModalBottomSheetState,
    private var scope: CoroutineScope
    ) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image

        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(image)
                .addOnCompleteListener { barcodes ->

                    if (barcodes.isSuccessful) {

                        for (barcode in barcodes.result) {
                            extractedText.value = barcode
                            scope.launch { state.show() }
                        }
                    }
                    imageProxy.close()
                }
        }
    }
}

