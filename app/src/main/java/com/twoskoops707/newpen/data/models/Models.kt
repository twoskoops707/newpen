package com.twoskoops707.newpen.data.models

enum class Hardware { FLIPPER, AWOK, BOTH, PHONE }
enum class Device { FLIPPER_CLI, MARAUDER, TERMUX, ANDROID, PC }

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
