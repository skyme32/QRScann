package com.skyme32.qrscann.camera

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
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
import com.skyme32.qrscann.R
import com.skyme32.qrscann.ui.component.ScanCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


@ExperimentalMaterialApi
@Composable
fun MLKitScan(barcodeView: BarcodeView = viewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val liveBarcode: State<Barcode?> = barcodeView.barcode.observeAsState(null)

    val skipHalfExpanded by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
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

@ExperimentalMaterialApi
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
    val cameraProvider = cameraProviderFuture.get()
    var camera: Camera? = null
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val scanner = remember { BarcodeScanning.getClient() }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(R.drawable.ic_baseline_flashlight_on) }

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
                    camera = cameraProvider.bindToLifecycle(
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

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {

            IconButton(onClick = {
                camera?.let {

                    if (it.cameraInfo.hasFlashUnit()) {
                        flashEnabled = !flashEnabled
                        flashRes =
                            if (flashEnabled) R.drawable.ic_baseline_flashlight_off else R.drawable.ic_baseline_flashlight_on
                        it.cameraControl.enableTorch(flashEnabled)
                    }
                }
            }) {
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = flashRes),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

class BarcodeView : ViewModel() {
    private var _barcode: MutableLiveData<Barcode?> = MutableLiveData(null)
    val barcode: LiveData<Barcode?> = _barcode

    fun setBarcode(barcode: Barcode?) {
        _barcode.value = barcode
    }
}

@ExperimentalMaterialApi
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

            scanner
                .process(image)
                .addOnCompleteListener { barcodes ->

                    if (barcodes.isSuccessful) {
                        for (barcode in barcodes.result) {

                            if (barcode.format == Barcode.FORMAT_QR_CODE
                                || barcode.format == Barcode.FORMAT_DATA_MATRIX
                                || barcode.format == Barcode.FORMAT_CODABAR
                            ) {
                                extractedText.value = barcode
                                scope.launch { state.show() }
                            }
                        }
                    }
                    imageProxy.close()
                }
        }
    }
}

