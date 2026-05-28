package my.demo.jconslovenia2026.quarkus.extension.runtime;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Vert.x handler to handle requests for the "info" requests.
 */
public class DemoHandler implements Handler<RoutingContext> {
    private final String data;

    public DemoHandler(String data) {
        this.data = data;
    }

    @Override
    public void handle(RoutingContext context) {
        context.response().putHeader("content-type", "text/plain")
                .end(data);
    }
}
