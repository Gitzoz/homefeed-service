package de.gitzoz.homefeed.controller

import de.gitzoz.homefeed.model.GreetingModule
import de.gitzoz.homefeed.model.ProductPromotionModule
import de.gitzoz.homefeed.model.SaleModule
import de.gitzoz.homefeed.service.GreetingModuleService
import de.gitzoz.homefeed.service.ProductModuleService
import de.gitzoz.homefeed.service.SaleModuleService
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeFeedControllerTest {

    private val greetingService: GreetingModuleService = mock()
    private val productModuleService: ProductModuleService = mock()
    private val saleService: SaleModuleService = mock()

    @Test
    fun `getHomeFeed returns all modules in expected order`() = runTest {
        val greeting = GreetingModule("Hello", "/promo")
        val productPromotion = ProductPromotionModule("Hot deals", "/teaser.jpg", emptyList())
        val sale = SaleModule("/sale", "/sale.jpg")

        whenever(greetingService.getModuleData()).thenReturn(greeting)
        whenever(productModuleService.getModuleData()).thenReturn(productPromotion)
        whenever(saleService.getModuleData()).thenReturn(sale)

        val controller = HomeFeedController(greetingService, productModuleService, saleService)

        val result = controller.getHomeFeed()

        assertEquals(listOf(greeting, productPromotion, sale), result)
    }

    @Test
    fun `getHomeFeed propagates exception when service fails`() = runTest {
        val failure = RuntimeException("boom")

        whenever(greetingService.getModuleData()).thenThrow(failure)

        val controller = HomeFeedController(greetingService, productModuleService, saleService)

        val exception = assertThrows(RuntimeException::class.java) {
            kotlinx.coroutines.runBlocking { controller.getHomeFeed() }
        }

        assertEquals("boom", exception.message)
    }
}
