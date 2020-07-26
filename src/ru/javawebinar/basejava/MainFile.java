package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File projectRoot = new File(".");
        showDirTree(projectRoot, 0);
    }

    private static void showDirTree(File directory, int level) {
        if (level == 0) {
            System.out.println(directory.getPath());
        }

        level++;
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                showDirTree(file, level);
            } else {
                StringBuilder treeItemLine = new StringBuilder("|");
                for (int i = 0; i < level; i++) {
                    treeItemLine.append("—");
                }
                System.out.println(treeItemLine.append(file.getName()).toString());
            }
        }
    }
}