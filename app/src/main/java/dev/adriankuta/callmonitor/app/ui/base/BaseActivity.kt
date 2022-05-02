package dev.adriankuta.callmonitor.app.ui.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private val multiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            if (isGranted.containsValue(false)) {
                onPermissionsDenied()
            } else {
                onPermissionsGranted()
            }
        }

    fun askPermissions() {
        val requiredPermission =
            listOf(Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE)
        if (!hasPermissions(requiredPermission)) {
            multiplePermissionsLauncher.launch(requiredPermission.toTypedArray())
        }
    }


    private fun hasPermissions(permissions: List<String>): Boolean {
        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    open fun onPermissionsGranted() = Unit

    open fun onPermissionsDenied() = Unit
}