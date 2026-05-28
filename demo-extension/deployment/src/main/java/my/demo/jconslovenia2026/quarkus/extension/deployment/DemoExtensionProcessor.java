package my.demo.jconslovenia2026.quarkus.extension.deployment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.quarkus.bootstrap.model.ApplicationModel;
import io.quarkus.builder.Version;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ApplicationInfoBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.pkg.builditem.CurateOutcomeBuildItem;
import io.quarkus.maven.dependency.ResolvedDependency;
import io.quarkus.vertx.http.deployment.RouteBuildItem;
import my.demo.jconslovenia2026.quarkus.extension.runtime.DemoRecorder;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

class DemoExtensionProcessor {

    private static final String FEATURE = "demo-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    RouteBuildItem createInfoRoute(DemoExtensionConfig config,
                                   DemoRecorder recorder,
                                   CurateOutcomeBuildItem curateOutcomeBuildItem,
                                   ApplicationInfoBuildItem applicationInfoBuildItem) throws JsonProcessingException {
        ApplicationModel applicationModel = curateOutcomeBuildItem.getApplicationModel();
        ResolvedDependency appArtifact = applicationModel.getAppArtifact();
        // create the buildInfo object
        Map<String, Object> buildInfo = new LinkedHashMap<>();
        buildInfo.put("name", applicationInfoBuildItem.getName());
        buildInfo.put("artifactId", appArtifact.getArtifactId());
        buildInfo.put("groupId", appArtifact.getGroupId());
        buildInfo.put("version", appArtifact.getVersion());
        buildInfo.put("buildTime", ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()));
        buildInfo.put("quarkusVersion", Version.getVersion());
        // serialize buildInfo into a JSON string
        String data = JsonMapper.builder().build().writeValueAsString(buildInfo);
        // create a RouteBuildItem with recorded bytecode for Vert.x handler so it could respond with the JSON at runtime
        return RouteBuildItem.builder()
                .route(config.path())
                .handler(recorder.createHandler(data))
                .build();
    }
}
