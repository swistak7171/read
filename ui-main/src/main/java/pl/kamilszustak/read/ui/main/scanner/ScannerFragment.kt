package pl.kamilszustak.read.ui.main.scanner

import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.google.android.material.tabs.TabLayout
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.OnSwipeListener
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
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
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    val event = ScannerEvent.OnTabSelected(tab.position)
                    viewModel.dispatchEvent(event)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        binding.cameraView.setOnTouchListener(object : OnSwipeListener(context) {
            override fun onSwipeLeft() {
                val event = ScannerEvent.OnSwiped(ScannerSwipeDirection.LEFT)
                viewModel.dispatchEvent(event)
            }

            override fun onSwipeRight() {
                val event = ScannerEvent.OnSwiped(ScannerSwipeDirection.RIGHT)
                viewModel.dispatchEvent(event)
            }
        })

        binding.scanButton.setOnClickListener {
            viewModel.dispatchEvent(ScannerEvent.OnScanButtonClicked)
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

                is ScannerAction.TakePicture -> {
                    takePicture(action)
                }

                is ScannerAction.Error -> {
                    when {
                        action.messageResourceId != null -> errorToast(action.messageResourceId)
                        action.throwable != null -> errorToast(action.throwable.message ?: "")
                    }
                }

                is ScannerAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.volume, action.isbn)
                }

                is ScannerAction.NavigateToTextSelectionFragment -> {
                    navigator.navigateToTextSelectionFragment(action.imageUri)
                }
            }
        }

        viewModel.selectedMode.observe(viewLifecycleOwner) { mode ->
            if (mode == null) return@observe

            val tab = binding.tabLayout.getTabAt(mode.index)
            val selectedTabIndex = binding.tabLayout.selectedTabPosition
            if (tab != null && selectedTabIndex != mode.index) {
                binding.tabLayout.selectTab(tab)
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

    private fun takePicture(action: ScannerAction.TakePicture) {
        val executor = Executors.newSingleThreadExecutor()
        if (action.file == null) {
            binding.cameraView.takePicture(executor, object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val event = ScannerEvent.OnImageCaptured(image)
                    viewModel.dispatchEvent(event)
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.e(exception)
                }
            })
        } else {
            val options = ImageCapture.OutputFileOptions.Builder(action.file)
                .build()

            val callback = object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = action.file.toUri()
                    val event = ScannerEvent.OnImageSaved(uri)
                    viewModel.dispatchEvent(event)
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.e(exception)
                }
            }

            binding.cameraView.takePicture(options, executor, callback)
        }
    }

    private inner class Navigator {
        fun navigateToBookEditFragment(volume: Volume? = null, isbn: String? = null) {
            val direction = ScannerFragmentDirections.actionScannerFragmentToNavigationBookEdit(
                volume = volume,
                isbn = isbn
            )
            navigate(direction)
        }

        fun navigateToTextSelectionFragment(uri: Uri) {
            val direction = ScannerFragmentDirections.actionScannerFragmentToTextSelectionFragment(uri)
            navigate(direction)
        }
    }
}