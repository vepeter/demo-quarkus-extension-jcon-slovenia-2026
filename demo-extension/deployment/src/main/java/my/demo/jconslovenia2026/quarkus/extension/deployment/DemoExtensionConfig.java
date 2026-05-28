package my.demo.jconslovenia2026.quarkus.extension.deployment;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
@ConfigMapping(prefix = "quarkus.demo")
public interface DemoExtensionConfig {

    /**
     * Path to the endpoint to respond with the application information.
     *
     * @return path
     */
    @WithDefault("/demo")
    String path();
}
