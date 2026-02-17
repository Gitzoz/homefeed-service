package de.gitzoz.homefeed.model

enum class ModuleType {
    GREETING,
    PRODUCT_PROMOTION,
    TEASER,
}

abstract class BaseModuleData(
    val type: ModuleType,
)
