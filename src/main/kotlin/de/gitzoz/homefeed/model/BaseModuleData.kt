package de.gitzoz.homefeed.model

enum class ModuleType {
    GREETING,
    PRODUCT_PROMOTION,
    SALE,
}

abstract class BaseModuleData(
    val type: ModuleType,
)
