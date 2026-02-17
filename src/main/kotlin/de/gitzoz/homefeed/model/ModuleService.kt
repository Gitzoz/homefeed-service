package de.gitzoz.homefeed.model

interface ModuleService<T: BaseModuleData> {

    suspend fun getModuleData(): T
}