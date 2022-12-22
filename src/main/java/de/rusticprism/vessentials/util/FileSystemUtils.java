package de.rusticprism.vessentials.util;

import de.rusticprism.vessentials.VEssentials;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FileSystemUtils {

    /**
     * Visits the resources at the given {@link Path} within the resource
     * path of the given {@link Class}.
     *
     * @param target The target class of the resource path to scan
     * @param consumer The consumer to visit the resolved path
     * @param firstPathComponent First path component
     * @param remainingPathComponents Remaining path components
     */
    public static boolean visitResources(Class<?> target, Consumer<Path> consumer,
                                         String firstPathComponent, String... remainingPathComponents)
            throws IOException {
        final URL knownResource = VEssentials.class.getClassLoader()
                .getResource("lang/messages.toml");
        if (knownResource == null) {
            throw new IllegalStateException(
                    "messages.toml does not exist, don't know where we are");
        }
        if (knownResource.getProtocol().equals("jar")) {
            // Running from a JAR
            String jarPathRaw = knownResource.toString().split("!")[0];
            URI path = URI.create(jarPathRaw + "!/");

            try (FileSystem fileSystem = FileSystems.newFileSystem(path, Map.of("create", "true"))) {
                Path toVisit = fileSystem.getPath(firstPathComponent, remainingPathComponents);
                if (Files.exists(toVisit)) {
                    consumer.accept(toVisit);
                    return true;
                }
                return false;
            }
        } else {
            // Running from the file system
            URI uri;
            List<String> componentList = new ArrayList<>();
            componentList.add(firstPathComponent);
            componentList.addAll(Arrays.asList(remainingPathComponents));

            try {
                URL url = target.getClassLoader().getResource(String.join("/", componentList));
                if (url == null) {
                    return false;
                }
                uri = url.toURI();
            } catch (URISyntaxException e) {
                throw new IllegalStateException(e);
            }
            consumer.accept(Path.of(uri));
            return true;
        }
    }
}
