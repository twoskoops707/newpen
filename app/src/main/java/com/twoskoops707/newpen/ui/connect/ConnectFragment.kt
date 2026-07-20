package com.twoskoops707.newpen.ui.connect

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.ble.FlipperBleManager
import com.twoskoops707.newpen.databinding.FragmentConnectBinding
import com.twoskoops707.newpen.databinding.ItemBleDeviceBinding

class ConnectFragment : Fragment() {

    private var _binding: FragmentConnectBinding? = null
    private val binding get() = _binding!!

    private lateinit var bleManager: FlipperBleManager
    private val foundDevices = mutableListOf<BluetoothDevice>()
    private lateinit var deviceAdapter: BleDeviceAdapter

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            startBleScan()
        } else {
            updateStatus("Bluetooth permissions denied")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bleManager = FlipperBleManager(requireContext())

        deviceAdapter = BleDeviceAdapter(foundDevices) { device ->
            connectToDevice(device)
        }

        binding.rvDevices.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDevices.adapter = deviceAdapter

        binding.btnScan.setOnClickListener {
            checkPermissionsAndScan()
        }

        binding.btnPing.setOnClickListener {
            bleManager.sendCommand("ping\r\n")
            appendResponse("> ping")
        }

        binding.btnDeviceInfo.setOnClickListener {
            bleManager.sendCommand("device_info\r\n")
            appendResponse("> device_info")
        }

        binding.btnReboot.setOnClickListener {
            bleManager.sendCommand("power reboot\r\n")
            appendResponse("> power reboot")
        }

        binding.cardConnected.isVisible = false
        updateStatus("Ready. Tap 'Scan for Flipper' to begin.")
    }

    private fun checkPermissionsAndScan() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        val allGranted = permissions.all {
            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
        }

        if (allGranted) {
            startBleScan()
        } else {
            permissionLauncher.launch(permissions)
        }
    }

    private fun startBleScan() {
        foundDevices.clear()
        deviceAdapter.notifyDataSetChanged()
        updateStatus("Scanning...")
        binding.btnScan.isEnabled = false

        bleManager.startScan { device ->
            requireActivity().runOnUiThread {
                if (foundDevices.none { it.address == device.address }) {
                    foundDevices.add(device)
                    deviceAdapter.notifyItemInserted(foundDevices.size - 1)
                }
            }
        }

        binding.root.postDelayed({
            if (isAdded) {
                bleManager.stopScan()
                binding.btnScan.isEnabled = true
                val count = foundDevices.size
                updateStatus(if (count == 0) "No Flipper devices found." else "Found $count device(s). Tap to connect.")
            }
        }, 10000L)
    }

    private fun connectToDevice(device: BluetoothDevice) {
        val name = try { device.name ?: device.address } catch (e: SecurityException) { device.address }
        updateStatus("Connecting to $name...")
        binding.cardConnected.isVisible = false

        bleManager.connect(
            device,
            onConnected = {
                requireActivity().runOnUiThread {
                    updateStatus("Connected to $name")
                    binding.cardConnected.isVisible = true
                    binding.tvConnectedName.text = name
                }
            },
            onDisconnected = {
                requireActivity().runOnUiThread {
                    updateStatus("Disconnected from $name")
                    binding.cardConnected.isVisible = false
                }
            }
        )
    }

    private fun updateStatus(status: String) {
        binding.tvStatus.text = status
    }

    private fun appendResponse(text: String) {
        val current = binding.tvResponse.text.toString()
        binding.tvResponse.text = if (current.isEmpty()) text else "$current\n$text"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bleManager.disconnect()
        _binding = null
    }

    inner class BleDeviceAdapter(
        private val devices: MutableList<BluetoothDevice>,
        private val onDeviceClick: (BluetoothDevice) -> Unit
    ) : RecyclerView.Adapter<BleDeviceAdapter.BleDeviceViewHolder>() {

        inner class BleDeviceViewHolder(private val binding: ItemBleDeviceBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(device: BluetoothDevice) {
                val name = try { device.name ?: "Unknown" } catch (e: SecurityException) { "Unknown" }
                binding.tvDeviceName.text = name
                binding.tvDeviceAddress.text = device.address
                binding.root.setOnClickListener { onDeviceClick(device) }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleDeviceViewHolder {
            val binding = ItemBleDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return BleDeviceViewHolder(binding)
        }

        override fun onBindViewHolder(holder: BleDeviceViewHolder, position: Int) {
            holder.bind(devices[position])
        }

        override fun getItemCount(): Int = devices.size
    }
}
