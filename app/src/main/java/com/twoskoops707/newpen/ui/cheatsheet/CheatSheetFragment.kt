package com.twoskoops707.newpen.ui.cheatsheet

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.R
import com.twoskoops707.newpen.databinding.FragmentCheatSheetBinding
import com.twoskoops707.newpen.databinding.ItemCheatCommandBinding
import com.twoskoops707.newpen.databinding.ItemCheatHeaderBinding

class CheatSheetFragment : Fragment() {

    private var _binding: FragmentCheatSheetBinding? = null
    private val binding get() = _binding!!

    sealed class CheatItem {
        data class Header(val title: String) : CheatItem()
        data class Command(val cmd: String, val description: String) : CheatItem()
    }

    private fun buildCheatItems(): List<CheatItem> {
        val items = mutableListOf<CheatItem>()

        items.add(CheatItem.Header("ESP32 Marauder (AWOK)"))
        listOf(
            "scanap" to "Scan access points",
            "scansta" to "Scan connected stations",
            "sniffbeacon" to "Passive beacon capture",
            "sniffprobe" to "Capture probe requests",
            "sniffpmkid" to "Capture PMKID handshakes",
            "sniffdeauth" to "Monitor deauth frames",
            "sniffbt" to "Bluetooth Classic scan",
            "attack -t deauth" to "Deauth all clients on selected AP",
            "attack -t beacon" to "Beacon spam flood",
            "evilportal" to "Start evil twin captive portal",
            "blespam" to "BLE advertisement spam",
            "select -a <idx>" to "Select AP by list index",
            "listap" to "Show scanned APs",
            "channel <n>" to "Set WiFi channel (1-13)",
            "stopscan" to "Stop all scanning/attacks",
            "clearap" to "Clear AP list",
            "help" to "Show all commands",
            "reboot" to "Restart AWOK"
        ).forEach { (cmd, desc) -> items.add(CheatItem.Command(cmd, desc)) }

        items.add(CheatItem.Header("Flipper Zero CLI (Serial)"))
        listOf(
            "help" to "List all commands",
            "device_info" to "Show device info",
            "rfid read" to "Read 125kHz card (hold card to back)",
            "rfid emulate <file>" to "Emulate saved RFID file",
            "nfc detect" to "Detect NFC field",
            "subghz rx <freq>" to "Receive on frequency (Hz)",
            "subghz tx_from_file <path>" to "Transmit .sub file",
            "subghz chat" to "Open Sub-GHz chat",
            "ir rx" to "Receive IR signal",
            "ir tx <protocol> <addr> <cmd>" to "Transmit IR",
            "ikey read" to "Read iButton key",
            "storage list /ext/" to "List SD card files",
            "storage read /ext/file.txt" to "Read file content",
            "power 5v 1" to "Enable 5V GPIO pin",
            "power 5v 0" to "Disable 5V GPIO pin",
            "power reboot" to "Reboot Flipper",
            "power off" to "Power off Flipper"
        ).forEach { (cmd, desc) -> items.add(CheatItem.Command(cmd, desc)) }

        items.add(CheatItem.Header("Termux / Kali Commands"))
        listOf(
            "pkg install nmap python python-pip curl wget tshark git dpkg -y" to "Install essential tools (no root needed)",
            "apt install libc++ libnl libpcap libsqlite openssl pcre zlib -y" to "Required libraries for aircrack-ng",
            "wget https://raw.githubusercontent.com/pitube08642/aircrack-ng-for-termux/main/dists/termux/aircrack-ng/binary-aarch64/aircrack-ng_3_1.7_aarch64.deb && dpkg -i aircrack-ng_3_1.7_aarch64.deb" to "Install aircrack-ng (not in default repo)",
            "nmap -sT -sV 192.168.1.0/24" to "TCP connect scan with version detection",
            "nmap -sn 192.168.1.0/24" to "Ping sweep (find live hosts)",
            "hashcat -m 22000 hash.hc22000 wordlist.txt" to "Crack WPA2 with wordlist",
            "hashcat -m 22000 hash.hc22000 -a 3 ?d?d?d?d?d?d?d?d" to "Brute force 8-digit PIN",
            "hashcat -m 22000 hash.hc22000 --show" to "Show cracked passwords",
            "tshark -r capture.pcap" to "Read PCAP file (no root needed)",
            "tshark -r capture.pcap -Y eapol" to "Filter EAPOL/handshake frames",
            "ss -tulpn" to "Show all active ports/connections",
            "curl ifconfig.me" to "Get your external IP address",
            "python3 -m http.server 8080" to "Start web server in current folder",
            "nethunter" to "Launch Kali NetHunter shell (after install)"
        ).forEach { (cmd, desc) -> items.add(CheatItem.Command(cmd, desc)) }

        items.add(CheatItem.Header("DuckyScript (BadUSB)"))
        listOf(
            "DELAY <ms>" to "Wait milliseconds",
            "STRING <text>" to "Type text as keyboard",
            "ENTER" to "Press Enter key",
            "GUI r" to "Windows Key + R (Run)",
            "GUI x" to "Windows Key + X (power menu)",
            "CTRL ALT t" to "Open terminal (Linux)",
            "CTRL ALT F2" to "Switch TTY (Linux)",
            "ALT F4" to "Close window",
            "CTRL c" to "Copy / Interrupt",
            "CTRL SHIFT i" to "DevTools (browser)",
            "CTRL l" to "Focus address bar",
            "REPEAT <n>" to "Repeat last command n times",
            "DEFAULT_DELAY <ms>" to "Set global delay between lines"
        ).forEach { (cmd, desc) -> items.add(CheatItem.Command(cmd, desc)) }

        return items
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheatSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = buildCheatItems()
        val adapter = CheatAdapter(items) { cmd ->
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("command", cmd)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
        }

        binding.rvCheatSheet.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCheatSheet.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class CheatAdapter(
        private val items: List<CheatItem>,
        private val onCommandCopy: (String) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val TYPE_HEADER = 0
        private val TYPE_COMMAND = 1

        override fun getItemViewType(position: Int): Int {
            return when (items[position]) {
                is CheatItem.Header -> TYPE_HEADER
                is CheatItem.Command -> TYPE_COMMAND
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == TYPE_HEADER) {
                val binding = ItemCheatHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            } else {
                val binding = ItemCheatCommandBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CommandViewHolder(binding)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (val item = items[position]) {
                is CheatItem.Header -> (holder as HeaderViewHolder).bind(item)
                is CheatItem.Command -> (holder as CommandViewHolder).bind(item)
            }
        }

        override fun getItemCount(): Int = items.size

        inner class HeaderViewHolder(private val binding: ItemCheatHeaderBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: CheatItem.Header) {
                binding.tvGroupTitle.text = item.title
            }
        }

        inner class CommandViewHolder(private val binding: ItemCheatCommandBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: CheatItem.Command) {
                binding.tvCommand.text = item.cmd
                binding.tvCommandDesc.text = item.description
                binding.root.setOnClickListener { onCommandCopy(item.cmd) }
            }
        }
    }
}
