package pl.kamilszustak.read.ui.main.scanner

import android.os.Bundle
import android.view.View
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
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject

class ScannerFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentScannerBinding, ScannerViewModel>(R.layout.fragment_scanner) {

    override val viewModel: ScannerViewModel by viewModels(viewModelFactory)
    override val binding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var camera: Camera

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            val permission = Permission.CAMERA
            askForPermissions(permission) { result ->
                val isGranted = result.isAllGranted(permission)
                if (isGranted) {
                    initializaCameraProvider()
                } else {
                    errorToast(R.string.camera_permission_not_granted)
                }
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
            Timber.i(image.height.toString())
        }

        cameraProvider.unbindAll()
        camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview)
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
    }
}