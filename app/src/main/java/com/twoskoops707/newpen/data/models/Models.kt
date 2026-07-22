package com.twoskoops707.newpen.data.models

enum class Hardware { FLIPPER, AWOK, BOTH, PHONE }
enum class Device { FLIPPER_CLI, MARAUDER, TERMUX, ANDROID, PC }
enum class WifiBoard(val id: String, val displayName: String, val shortName: String, val desc: String) {
    AWOK_DUAL_MINI_V3("awok", "AWOK DUAL MINI V3", "AWOK", "standalone screen + Flipper GPIO"),
    FLIPPER_WIFI_DEV_BOARD("devboard", "FLIPPER WIFI DEV BOARD", "DEV BOARD", "ESP32-S2, controlled via Flipper app"),
    NONE("none", "FLIPPER ONLY", "NONE", "no WiFi/BLE module attached")
}

data class Command(
    val label: String,
    val value: String,
    val device: Device
)

data class WorkflowStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val commands: List<Command> = emptyList(),
    val tips: List<String> = emptyList(),
    val warning: String? = null
)

data class Workflow(
    val id: String,
    val title: String,
    val subtitle: String,
    val categoryId: String,
    val hardware: List<Hardware>,
    val prerequisites: List<String>,
    val steps: List<WorkflowStep>,
    val youtubeUrl: String? = null
)

data class Category(
    val id: String,
    val title: String,
    val icon: String,
    val description: String,
    val workflowCount: Int = 0
)
