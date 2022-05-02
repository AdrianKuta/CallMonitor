package dev.adriankuta.callmonitor.app.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adriankuta.callmonitor.app.tools.CallLogManager
import dev.adriankuta.callmonitor.model.CallLog
import kotlinx.coroutines.launch
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val callLogManager: CallLogManager
) : AndroidViewModel(application) {

    val callLogs by lazy { MutableLiveData<List<CallLog>>() }
    val ipAddress by lazy { MutableLiveData<String>() }

    fun refreshCallLogs() {
        viewModelScope.launch {
            val callsHistory = callLogManager.getCallHistory()
            callLogs.postValue(callsHistory)
        }
    }

    fun startServer() {
        ipAddress.postValue(getIpAddress() + ":5000")
        callLogManager.startCallMonitoring()
    }

    fun stopServer() {
        ipAddress.postValue("")
        callLogs.postValue(emptyList())
        callLogManager.stopCallMonitoring()
    }

    private fun getIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }
}