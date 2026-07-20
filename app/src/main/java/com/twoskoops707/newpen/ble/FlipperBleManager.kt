package com.twoskoops707.newpen.ble

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import java.util.UUID

class FlipperBleManager(private val context: Context) {

    var isConnected: Boolean = false
        private set

    var connectedDevice: BluetoothDevice? = null
        private set

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private var bleScanner: BluetoothLeScanner? = null
    private var gatt: BluetoothGatt? = null
    private var txCharacteristic: BluetoothGattCharacteristic? = null

    private var onConnectedCallback: (() -> Unit)? = null
    private var onDisconnectedCallback: (() -> Unit)? = null
    private var scanCallback: ((BluetoothDevice) -> Unit)? = null

    companion object {
        private val SERIAL_SERVICE_UUID = UUID.fromString("00003082-0000-1000-8000-00805f9b34fb")
        private val TX_CHAR_UUID = UUID.fromString("00003082-0000-1000-8000-00805f9b34fb")
        private val CLIENT_CHAR_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    }

    private val bleScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val device = result.device
            val name = try { device.name } catch (e: SecurityException) { null }
            if (name != null && name.contains("Flipper", ignoreCase = true)) {
                scanCallback?.invoke(device)
            }
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    isConnected = true
                    connectedDevice = gatt.device
                    try { gatt.discoverServices() } catch (e: SecurityException) { }
                    onConnectedCallback?.invoke()
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    isConnected = false
                    connectedDevice = null
                    txCharacteristic = null
                    onDisconnectedCallback?.invoke()
                }
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                gatt.services?.forEach { service ->
                    service.characteristics?.forEach { char ->
                        val props = char.properties
                        if (props and BluetoothGattCharacteristic.PROPERTY_WRITE != 0 ||
                            props and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE != 0
                        ) {
                            txCharacteristic = char
                        }
                        if (props and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0) {
                            try {
                                gatt.setCharacteristicNotification(char, true)
                                val descriptor = char.getDescriptor(CLIENT_CHAR_CONFIG)
                                if (descriptor != null) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        gatt.writeDescriptor(descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                                    } else {
                                        @Suppress("DEPRECATION")
                                        descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                                        @Suppress("DEPRECATION")
                                        gatt.writeDescriptor(descriptor)
                                    }
                                }
                            } catch (e: SecurityException) { }
                        }
                    }
                }
            }
        }

        @Suppress("DEPRECATION")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            // Response data received — extend with a callback if UI needs it
        }
    }

    fun startScan(callback: (BluetoothDevice) -> Unit) {
        if (!hasPermission()) return
        scanCallback = callback
        bleScanner = bluetoothAdapter?.bluetoothLeScanner
        try {
            bleScanner?.startScan(bleScanCallback)
        } catch (e: SecurityException) { }
    }

    fun stopScan() {
        try {
            bleScanner?.stopScan(bleScanCallback)
        } catch (e: SecurityException) { }
        bleScanner = null
    }

    fun connect(
        device: BluetoothDevice,
        onConnected: () -> Unit,
        onDisconnected: () -> Unit
    ) {
        onConnectedCallback = onConnected
        onDisconnectedCallback = onDisconnected
        try {
            gatt?.close()
            gatt = device.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE)
        } catch (e: SecurityException) { }
    }

    fun disconnect() {
        try {
            gatt?.disconnect()
            gatt?.close()
        } catch (e: SecurityException) { }
        gatt = null
        isConnected = false
        connectedDevice = null
        txCharacteristic = null
    }

    fun sendCommand(command: String) {
        val char = txCharacteristic ?: return
        val bytes = command.toByteArray(Charsets.UTF_8)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val writeType = if (char.properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE != 0) {
                    BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
                } else {
                    BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
                }
                gatt?.writeCharacteristic(char, bytes, writeType)
            } else {
                @Suppress("DEPRECATION")
                char.value = bytes
                @Suppress("DEPRECATION")
                gatt?.writeCharacteristic(char)
            }
        } catch (e: SecurityException) { }
    }

    private fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }
    }
}
