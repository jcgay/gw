package fr.jcgay.gw;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File("."));

        try (ProjectConnection connection = connector.connect()) {
            BuildLauncher launcher = connection.newBuild();
            launcher.forTasks("help");
            launcher.setStandardOutput(System.out);
            launcher.setStandardError(System.err);
            launcher.run();
        }
    }
}
