package ru.job4j.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Shell {

    private int pathCount = 0;
    private List<String> pathArr = new LinkedList<>();

    public void cd(String path) {
        if (path.startsWith("/")) {
            for (var p : path.split("/")) {
                pathArr.add(p);
            }
        }
    }

    public String pwd() {
        return "/";
    }
}
