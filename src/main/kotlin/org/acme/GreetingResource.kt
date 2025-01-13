package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter

@Path("/")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    fun hello(
        @QueryParam("fred")
        fred: Fred?) = "Hello from Quarkus REST"

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hi")
    fun hi(
        @QueryParam("fred")
        fred: Fred) = "Hello from Quarkus REST"
}
enum class Fred {
    BOB
}
