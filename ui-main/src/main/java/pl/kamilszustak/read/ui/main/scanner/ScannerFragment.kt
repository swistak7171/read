package pl.kamilszustak.read.ui.main.scanner

import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.google.common.util.concurrent.ListenableFuture
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentScannerBinding
import java.util.concurrent.Executors
import javax.inject.Inject

class ScannerFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentScannerBinding, ScannerViewModel>(R.layout.fragment_scanner) {

    override val viewModel: ScannerViewModel by viewModels(viewModelFactory)
    override val binding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var camera: Camera

    override fun onResume() {
        super.onResume()
        viewModel.dispatchEvent(ScannerEvent.OnResumed)
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ScannerAction.CameraPermissionAction -> {
                    when (action) {
                        ScannerAction.CameraPermissionAction.Unknown -> checkCameraPermission()
                        ScannerAction.CameraPermissionAction.Granted -> initializaCameraProvider()
                        ScannerAction.CameraPermissionAction.Denied -> errorToast(R.string.camera_permission_denied)
                        ScannerAction.CameraPermissionAction.PermanentlyDenied -> errorToast(R.string.camera_permission_permanently_denied)
                    }
                }

                is ScannerAction.Error -> {
                    errorToast(action.throwable.message ?: "")
                }

                is ScannerAction.BarcodeDetected -> {
                }
            }
        }
    }

    private fun checkCameraPermission() {
        lifecycleScope.launchWhenResumed {
            askForPermissions(Permission.CAMERA) { result ->
                val event = ScannerEvent.OnCameraPermissionResult(result)
                viewModel.dispatchEvent(event)
            }
        }
    }

    private fun initializaCameraProvider() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder()
            .build()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        val executor = Executors.newSingleThreadExecutor()
        imageAnalysis.setAnalyzer(executor) { image ->
            val event = ScannerEvent.OnImageCaptured(image)
            viewModel.dispatchEvent(event)
        }

        cameraProvider.unbindAll()
        camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview)
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
    }
}