package fr.jcgay.gw;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class GradleProjectStructure {

    private final File currentDir;

    public GradleProjectStructure(File currentDir) {
        try {
            this.currentDir = currentDir.getCanonicalFile();
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot resolve directory: " + currentDir, e);
        }
    }

    public File root() {
        return findRoot(currentDir);
    }

    private static File findRoot(File currentDir) {
        File[] hasWrapper = currentDir.listFiles(file -> file.getName().contains("gradlew"));
        if (hasWrapper != null && hasWrapper.length > 0) {
            return currentDir;
        }
        return findRoot(currentDir.getParentFile());
    }
}
