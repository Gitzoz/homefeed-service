package de.gitzoz.homefeed.controller

import de.gitzoz.homefeed.model.BaseModuleData
import de.gitzoz.homefeed.service.GreetingModuleService
import de.gitzoz.homefeed.service.ProductModuleService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("homefeed/")
class HomeFeedController(
    private val greetingService: GreetingModuleService,
    private val productModuleService: ProductModuleService,
) {

    @GetMapping
    suspend fun getHomeFeed(): List<BaseModuleData> = coroutineScope {
        val greeting = async { greetingService.getModuleData() }
        val productPromotion = async { productModuleService.getModuleData() }
        listOf(greeting.await(), productPromotion.await())
    }
}