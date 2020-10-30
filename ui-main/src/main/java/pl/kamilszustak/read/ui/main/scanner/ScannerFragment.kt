package pl.kamilszustak.read.ui.main.scanner

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
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

    override fun onResume() {
        super.onResume()
        viewModel.dispatchEvent(ScannerEvent.OnResumed)
    }

    override fun setListeners() {
        binding.scanButton.setOnClickListener { view ->
            val executor = Executors.newSingleThreadExecutor()
            binding.cameraView.takePicture(executor, object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val event = ScannerEvent.OnImageCaptured(image)
                    viewModel.dispatchEvent(event)
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.e(exception)
                }
            })
        }

        binding.torchButton.setOnClickListener {
            viewModel.dispatchEvent(ScannerEvent.OnTorchButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ScannerAction.CameraPermissionAction -> {
                    when (action) {
                        ScannerAction.CameraPermissionAction.Unknown -> checkCameraPermission()
                        ScannerAction.CameraPermissionAction.Granted -> initializeCameraView()
                        ScannerAction.CameraPermissionAction.Denied -> errorToast(R.string.camera_permission_denied)
                        ScannerAction.CameraPermissionAction.PermanentlyDenied -> errorToast(R.string.camera_permission_permanently_denied)
                    }
                }

                is ScannerAction.ChangeTorchState -> {
                    binding.cameraView.enableTorch(action.isEnabled)
                }

                is ScannerAction.Error -> {
                    errorToast(action.throwable.message ?: "")
                }

                is ScannerAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.volume, action.isbn)
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

    private fun initializeCameraView() {
        binding.cameraView.bindToLifecycle(viewLifecycleOwner)
    }

    private inner class Navigator {
        fun navigateToBookEditFragment(volume: Volume? = null, isbn: String? = null) {
            val direction = ScannerFragmentDirections.actionScannerFragmentToNavigationBookEdit(
                volume = volume,
                isbn = isbn
            )
            navigate(direction)
        }
    }
}