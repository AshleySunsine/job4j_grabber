package ru.job4j.cache;

import java.util.Scanner;

public class GetFromCacheMenu implements MenuInterface {
    @Override
    public String nameMenu() {
        return "Получить содержимое файла из кэша";
    }

    @Override
    public void run(Emulator emulator) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите какой файл читаем из кэша: " + System.lineSeparator());
        String file = scanner.nextLine();
        emulator.getFromCache(file);
    }
}
