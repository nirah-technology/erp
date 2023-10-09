package io.nirahtech.erp.webapp.infrastructure;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class WebResource {

    public static final String RESOURCE_STATIC_FOLDER = "/WEB-INF/static/";
    private static WebResource instance = null;
    
    public static WebResource getInstance() {
        if (instance == null) {
            instance = new WebResource();
        }
        return instance;
    }


    private WebResource() { }

    public synchronized Optional<String> retrieveFile(final InputStream resourceToLoad) {
        Optional<String> fileContent = Optional.empty();
        if (Objects.nonNull(resourceToLoad)) {
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(resourceToLoad))) {
                fileContent = Optional.ofNullable(reader.lines().collect(Collectors.joining("\n")));
            } catch (final Exception exception) {
                // Ignore
            }
        }
        return fileContent;
    }


}
