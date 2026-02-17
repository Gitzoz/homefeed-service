package de.gitzoz.homefeed.service

import de.gitzoz.homefeed.model.BaseModuleData

interface ModuleService<T: BaseModuleData> {

    suspend fun getModuleData(): T
}