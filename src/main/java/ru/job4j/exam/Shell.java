package ru.job4j.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Shell {

    private int indexOfPath = 0;
    private List<String> pathArr = new LinkedList<>();

    private void cdAdder(String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals("..")) {
                if (indexOfPath > 0) {
                    pathArr.remove(indexOfPath - 1);
                    indexOfPath--;
                }
            }
            if (i == 0) {
                continue;
            }
            pathArr.add(items[i]);
            indexOfPath++;
        }
    }

    public void cd(String path) {
        String[] paths = path.split("/");
        if (path.startsWith("/") || path.startsWith("..")) {
            pathArr.removeAll(pathArr);
            indexOfPath = 0;
            cdAdder(paths);
           } else {
                pathArr.add(path);
                indexOfPath++;
            }
        }

    public String pwd() {
        StringJoiner stringJoiner = new StringJoiner("/");
        if (pathArr.isEmpty()) {
            stringJoiner.add("");
        }
        stringJoiner.add("");
        for (var i : pathArr) {
            stringJoiner.add(i);
        }
        return stringJoiner.toString();
    }
}
