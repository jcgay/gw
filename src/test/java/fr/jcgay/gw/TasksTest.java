package fr.jcgay.gw;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TasksTest {

    @Test
    void list_tasks_from_main_args() {
        assertThat(new Tasks(new String[]{"clean", "assemble"}).list())
                .containsExactly("clean", "assemble");
    }

    @Test
    void return_task_help_when_args_are_empty() {
        assertThat(new Tasks(new String[]{}).list()).containsExactly("help");
    }
}