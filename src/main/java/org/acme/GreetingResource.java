package org.acme;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(
           @QueryParam("fred") Fred fred
    ) {
        System.out.println(fred);
        return "Hello from Quarkus REST";
    }
}
enum Fred {
    BOB
}
