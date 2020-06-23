package ru.javawebinar.basejava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        File[] directoriesAndFilesList = directory.listFiles();
        List<File> onlyFilesList = new ArrayList<>();

        level++;
        for (File file : Objects.requireNonNull(directoriesAndFilesList)) {
            if (file.isDirectory()) {
                printTreeItem(file.getName(), level);
                showDirTree(file, level);
            } else {
                // Можно и без else, просто хотел вывести покрасивее - сначала выводим директории, а потом файлы
                onlyFilesList.add(file);
            }
        }

        // Вывожу остатки файлов
        for (File file : onlyFilesList) {
            printTreeItem(file.getName(), level);
        }
    }

    private static void printTreeItem(String filename, int level) {
        StringBuilder treeItemLine = new StringBuilder("|");
        for (int i = 0; i < level; i++) {
            treeItemLine.append("—");
        }
        treeItemLine
                .append(" ")
                .append(filename);
        System.out.println(treeItemLine.toString());
    }
}