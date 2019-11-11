package fr.jcgay.gw;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.jline.reader.Candidate;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new GradleProjectStructure(new File(".")).root());

        try (ProjectConnection connection = connector.connect()) {
            GradleProject project = connection.getModel(GradleProject.class);

            BuildLauncher launcher = connection.newBuild();
            launcher.forTasks(args.length > 0 ? new InputTasks(args).list() : readInteractiveInputTasks(project));
            launcher.setStandardOutput(System.out);
            launcher.setStandardError(System.err);
            launcher.setColorOutput(true);
            launcher.run();
        }
    }

    private static String[] readInteractiveInputTasks(GradleProject project) {
        Terminal terminal = newTerminal();

        LineReader build = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter(fromTasks(project)))
                .build();

        return new InteractiveInputTasks(build.readLine()).list();
    }

    private static Collection<Candidate> fromTasks(GradleProject project) {
        return project.getTasks().getAll().stream()
                .map(task -> new Candidate(task.getName(), task.getDisplayName(), task.getGroup(), task.getDescription(), null, null, true))
                .collect(toList());
    }

    private static Terminal newTerminal() {
        try {
            return TerminalBuilder.terminal();
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot create terminal...", e);
        }
    }
}
