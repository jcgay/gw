package fr.jcgay.gw;

public class InputTasks {

    private String[] tasks = new String[]{"help"};

    public InputTasks(String[] args) {
        if (args.length > 0) {
            this.tasks = args;
        }
    }

    public String[] list() {
        return tasks;
    }
}
