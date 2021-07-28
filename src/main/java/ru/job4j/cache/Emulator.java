package ru.job4j.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Emulator {

    private DirFileCache dirFileCache;
    private boolean atMenu = true;

    public Emulator(DirFileCache dirFileCache) {
        this.dirFileCache = dirFileCache;
    }

    public void changeFileDirectory(String dir) {
            if (fileValidate(dir).equals("dir")) {
            dirFileCache = new DirFileCache(dir);
            System.out.println("Новая директория: " + dir);
        } else {
                System.out.println("Директории " + dir + " не существует");
            }
    }

    public void loadToCache(String key) {
        if (fileValidate(key).equals("file")) {
            dirFileCache.load(key);
            System.out.println("Файл " + key + " загружен в кэш");
        } else {
            System.out.println("Файла " + key + " не существует");
        }
    }

    public void getFromCache(String key) {
        System.out.println(dirFileCache.get(key));
    }

    private String fileValidate(String dir) {
        File direct = new File(dir);
        File file = new File(this.dirFileCache.getCachingDir() + File.separator + dir);
        //для дебага
        boolean isFile = file.isFile();
        boolean isDirectory = direct.isDirectory();
        boolean existDir = direct.exists();
        boolean existFile = file.exists();
        if (existDir && isDirectory) {
            return "dir";
        }
        if (existFile && isFile) {
            return "file";
        }
        System.out.println("Ошибка валидации");
        return dir;
    }

    public void exitFromMenu() {
        atMenu = false;
    }

    private void showMenu(List<MenuInterface> menus) {
        for (int i = 1; i < menus.size() + 1; i++) {
            System.out.print(i + ".");
            System.out.println(menus.get(i - 1).nameMenu());
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator(new DirFileCache("./"));
        List<MenuInterface> menus = new ArrayList<>(
                List.of(new ChangeDirMenu(), new LoadToCacheMenu(),
                        new GetFromCacheMenu(), new ExitFromMenu()));
        Scanner scanner = new Scanner(System.in);
        int item;
        while (emulator.atMenu) {
            emulator.showMenu(menus);
            System.out.println("Выберите задачу: ");
            item = scanner.nextInt();
            menus.get(item - 1).run(emulator);
        }
    }
}
