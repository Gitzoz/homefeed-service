package de.gitzoz.homefeed.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("GREETING")
data class Greeting(
    @Id val id: Long? = null,
    val greetingText: String,
    val promotionLink: String
)

fun Greeting.toModule(): GreetingModule {
    return GreetingModule(this.greetingText, this.promotionLink)
}

data class GreetingModule(
    val greetingText: String,
    val promotionLink: String
) : BaseModuleData(ModuleType.GREETING)

