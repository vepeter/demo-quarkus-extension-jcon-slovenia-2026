package my.demo.jconslovenia2026.quarkus.extension.runtime;

import io.quarkus.runtime.annotations.Recorder;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

@Recorder
public class DemoRecorder {
    public Handler<RoutingContext> createHandler(String data) {
        return new DemoHandler(data);
    }
}
