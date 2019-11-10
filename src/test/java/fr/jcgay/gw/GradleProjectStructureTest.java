package fr.jcgay.gw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GradleProjectStructureTest {

    @Test
    void root_directory_is_where_gradle_wrapper_is_located(@TempDir Path rootDir) throws IOException {
        writeFakeWrapper(rootDir);

        File result = new GradleProjectStructure(rootDir.toFile()).root();

        assertThat(result).isEqualTo(rootDir.toFile().getCanonicalFile());
    }

    @Test
    void root_directory_is_where_gradle_wrapper_is_located_when_launched_from_a_submodule(@TempDir Path rootDir) throws IOException {
        writeFakeWrapper(rootDir);

        File result = new GradleProjectStructure(rootDir.resolve("module-a/").toFile()).root();

        assertThat(result).isEqualTo(rootDir.toFile().getCanonicalFile());
    }

    @Test
    void root_directory_is_where_gradle_wrapper_is_located_when_launched_from_a_submodule_with_plop(@TempDir Path rootDir) throws IOException {
        writeFakeWrapper(rootDir);

        File result = new GradleProjectStructure(rootDir.resolve("./module-a/").toFile()).root();

        assertThat(result).isEqualTo(rootDir.toFile().getCanonicalFile());
    }

    private static void writeFakeWrapper(@TempDir Path rootDir) throws IOException {
        Path wrapper = rootDir.resolve("gradlew.sh");
        Files.write(wrapper, List.of(""));
    }
}