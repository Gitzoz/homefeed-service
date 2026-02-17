package de.gitzoz.homefeed.service

import de.gitzoz.homefeed.service.ModuleService
import de.gitzoz.homefeed.model.ProductPromotionModule
import de.gitzoz.homefeed.repository.ProductPromotionRepository
import de.gitzoz.homefeed.repository.ProductRepository
import kotlinx.coroutines.flow.first
import org.springframework.stereotype.Service


@Service
class ProductModuleService(
    private val productPromotionRepository: ProductPromotionRepository,
    private val productRepository: ProductRepository,
) : ModuleService<ProductPromotionModule> {

    override suspend fun getModuleData(): ProductPromotionModule {
        val promotion = productPromotionRepository.findAll().first()
        val products = productRepository.findAllByPromotionId(promotion.id)
        return ProductPromotionModule(promotion.promotionText, promotion.teaserImageUrl, products)
    }

}