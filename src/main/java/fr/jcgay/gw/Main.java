package fr.jcgay.gw;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new GradleProjectStructure(new File(".")).root());

        try (ProjectConnection connection = connector.connect()) {
            BuildLauncher launcher = connection.newBuild();
            launcher.forTasks(new Tasks(args).list());
            launcher.setStandardOutput(System.out);
            launcher.setStandardError(System.err);
            launcher.setColorOutput(true);
            launcher.run();
        }
    }
}
