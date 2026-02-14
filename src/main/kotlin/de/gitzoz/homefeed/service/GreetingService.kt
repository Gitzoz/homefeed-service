package de.gitzoz.homefeed.service

import de.gitzoz.homefeed.model.GreetingDto
import de.gitzoz.homefeed.model.toDto
import de.gitzoz.homefeed.repository.GreetingRepository
import kotlinx.coroutines.flow.first
import org.springframework.stereotype.Service

@Service
class GreetingService(private val greetingRepository: GreetingRepository) {

    suspend fun getGreeting(): GreetingDto {
        return greetingRepository.findAll().first().toDto()
    }
}