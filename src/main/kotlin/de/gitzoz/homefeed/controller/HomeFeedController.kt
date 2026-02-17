package de.gitzoz.homefeed.controller

import de.gitzoz.homefeed.model.BaseModuleData
import de.gitzoz.homefeed.service.GreetingModuleService
import de.gitzoz.homefeed.service.ProductModuleService
import de.gitzoz.homefeed.service.SaleModuleService
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
    private val saleService: SaleModuleService
) {

    @GetMapping
    suspend fun getHomeFeed(): List<BaseModuleData> = coroutineScope {
        val greeting = async { greetingService.getModuleData() }
        val productPromotion = async { productModuleService.getModuleData() }
        val sale = async { saleService.getModuleData() }
        listOf(greeting.await(), productPromotion.await(), sale.await())
    }
}