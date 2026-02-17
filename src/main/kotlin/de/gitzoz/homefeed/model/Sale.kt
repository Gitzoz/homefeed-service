package de.gitzoz.homefeed.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("SALES")
data class Sale(
    @Id val id: String? = null,
    val deepLink: String,
    val imageUrl: String,
)

fun Sale.toModule(): SaleModule {
    return SaleModule(this.deepLink, this.imageUrl)
}

data class SaleModule(
    var deepLink: String,
    var imageUrl: String,
): BaseModuleData(ModuleType.SALE)



