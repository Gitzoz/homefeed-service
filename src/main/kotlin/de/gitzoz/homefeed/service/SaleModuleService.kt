package de.gitzoz.homefeed.service

import de.gitzoz.homefeed.model.ModuleService
import de.gitzoz.homefeed.model.SaleModule
import de.gitzoz.homefeed.model.toModule
import de.gitzoz.homefeed.repository.SaleRepository
import kotlinx.coroutines.flow.first
import org.springframework.stereotype.Service

@Service
class SaleModuleService(private val saleRepository: SaleRepository) : ModuleService<SaleModule> {

    override suspend fun getModuleData(): SaleModule {
        return saleRepository.findAll().first().toModule()
    }

}