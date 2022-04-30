package dev.adriankuta.callmonitor.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.R

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                handlePermissionGranted()
            } else {
                handlePermissionDenied()
            }
        }

    override fun onStart() {
        super.onStart()
        setupPermissions()
    }


    private fun setupPermissions() {

        val requiredPermission = Manifest.permission.READ_CALL_LOG
        when {
            ContextCompat.checkSelfPermission(
                this,
                requiredPermission
            ) == PackageManager.PERMISSION_GRANTED -> handlePermissionGranted()
            shouldShowRequestPermissionRationale(requiredPermission) -> showRationaleDialog(getString(
                R.string.rationale_title), getString(R.string.rationale_desc), requiredPermission)
            else -> {
                requestPermissionLauncher.launch(requiredPermission)
            }

        }
    }

    private fun showRationaleDialog(title: String, message: String, permission: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> requestPermissionLauncher.launch(permission) }
        builder.create().show()
    }

    abstract fun onPermissionsGranted()


    private fun handlePermissionGranted() {
        onPermissionsGranted()
    }

    private fun handlePermissionDenied() {

    }
}