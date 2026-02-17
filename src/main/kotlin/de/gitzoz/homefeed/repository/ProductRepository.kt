package de.gitzoz.homefeed.repository

import de.gitzoz.homefeed.model.Product
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CoroutineCrudRepository<Product, Long> {
    suspend fun findAllByPromotionId(promotionId: Long?): List<Product> {
        return promotionId?.let { this.findAll().filter { product -> product.promotionId == it }.toList() } ?: emptyList()
    }
}