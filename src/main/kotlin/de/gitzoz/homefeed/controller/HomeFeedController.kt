package de.gitzoz.homefeed.controller

import de.gitzoz.homefeed.model.GreetingDto
import de.gitzoz.homefeed.service.GreetingService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("homefeed/")
class HomeFeedController(
    private val greetingService: GreetingService
) {

    @GetMapping
    suspend fun getHomeFeed(): GreetingDto = coroutineScope {
        val greeting = async { greetingService.getGreeting() }
        greeting.await()
    }
}