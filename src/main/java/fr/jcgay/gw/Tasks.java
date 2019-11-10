package fr.jcgay.gw;

public class Tasks {

    private String[] tasks = new String[]{"help"};

    public Tasks(String[] args) {
        if (args.length > 0) {
            this.tasks = args;
        }
    }

    public String[] list() {
        return tasks;
    }
}
