package de.gitzoz.homefeed.service

import de.gitzoz.homefeed.model.GreetingModule
import de.gitzoz.homefeed.service.ModuleService
import de.gitzoz.homefeed.model.toModule
import de.gitzoz.homefeed.repository.GreetingRepository
import kotlinx.coroutines.flow.first
import org.springframework.stereotype.Service

@Service
class GreetingModuleService(private val greetingRepository: GreetingRepository) : ModuleService<GreetingModule> {

    override suspend fun getModuleData(): GreetingModule {
        return greetingRepository.findAll().first().toModule()
    }

}