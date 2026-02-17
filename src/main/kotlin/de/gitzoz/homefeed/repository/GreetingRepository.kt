package de.gitzoz.homefeed.repository

import de.gitzoz.homefeed.model.Greeting
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface GreetingRepository : CoroutineCrudRepository<Greeting, Long>
