package fr.jcgay.gw;

public class InteractiveInputTasks {

    private final String readLine;

    public InteractiveInputTasks(String readLine) {
        this.readLine = readLine;
    }

    public String[] list() {
        return readLine.split(" ");
    }
}
