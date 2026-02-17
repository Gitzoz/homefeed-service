package de.gitzoz.homefeed.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("PRODUCTS")
data class Product(
    @Id val id: Long? = null,
    val promotionId: Long,
    val name: String,
    val price: Double,
    val imageUrl: String,
)

@Table("PRODUCT_PROMOTION")
data class ProductPromotion(
    @Id val id: Long? = null,
    val name: String,
    val promotionText: String,
    val teaserImageUrl: String,
)

data class ProductPromotionModule(
    val promotionText: String,
    val teaserImageUrl: String,
    val products: List<Product>,
) : BaseModuleData(ModuleType.PRODUCT_PROMOTION)
