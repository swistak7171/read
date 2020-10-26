package pl.kamilszustak.read.ui.main.scanner

import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.google.common.util.concurrent.ListenableFuture
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.activity.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.databinding.FragmentScannerBinding
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject

class ScannerFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentScannerBinding, ScannerViewModel>(R.layout.fragment_scanner) {

    override val viewModel: ScannerViewModel by viewModels(viewModelFactory)
    private val navigator: Navigator = Navigator()

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var camera: Camera
    private lateinit var imageCapture: ImageCapture

    override fun onResume() {
        super.onResume()
        viewModel.dispatchEvent(ScannerEvent.OnResumed)
    }

    override fun setListeners() {
        binding.scannerImageView.setOnClickListener { view ->
            view.isEnabled = false
            imageCapture.takePicture(Executors.newSingleThreadExecutor(), object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    ContextCompat.getMainExecutor(context)
                        .execute { view.isEnabled = true }

                    val event = ScannerEvent.OnImageCaptured(image)
                    viewModel.dispatchEvent(event)
                }

                override fun onError(exception: ImageCaptureException) {
                    ContextCompat.getMainExecutor(context)
                        .execute { view.isEnabled = true }

                    Timber.e(exception)
                }
            })
        }
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

                is ScannerAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.volume)
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

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        cameraProvider.unbindAll()
        camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview)
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
    }

    private inner class Navigator {
        fun navigateToBookEditFragment(volume: Volume) {
            val direction = ScannerFragmentDirections.actionScannerFragmentToNavigationBookEdit(volume = volume)
            navigate(direction)
        }
    }
}