package de.gitzoz.homefeed.repository

import de.gitzoz.homefeed.model.Sale
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleRepository: CoroutineCrudRepository<Sale, Long>