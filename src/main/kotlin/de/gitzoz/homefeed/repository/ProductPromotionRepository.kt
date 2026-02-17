package de.gitzoz.homefeed.repository

import de.gitzoz.homefeed.model.ProductPromotion
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductPromotionRepository : CoroutineCrudRepository<ProductPromotion, Long>