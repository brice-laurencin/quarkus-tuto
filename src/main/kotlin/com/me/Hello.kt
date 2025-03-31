package com.me

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Path("/")
class Hello {
    @Inject
    lateinit var clockService: ClockService
    @GET
    fun `what time is it`(): String {
        return clockService.currentTime().toString()
    }
}

@ApplicationScoped
class ClockService {
    fun currentTime(): ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
}
