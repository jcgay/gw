package fr.jcgay.gw;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InteractiveInputTasksTest {

    @Test
    void when_user_inputs_one_task() {
        assertThat(new InteractiveInputTasks("assemble ").list()).containsExactly("assemble");
    }

    @Test
    void when_user_inputs_multiple_tasks() {
        assertThat(new InteractiveInputTasks("clean assemble ").list()).containsExactly("clean", "assemble");
    }
}