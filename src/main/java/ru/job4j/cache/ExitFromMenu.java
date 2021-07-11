package ru.job4j.cache;

public class ExitFromMenu implements MenuInterface {
    @Override
    public String nameMenu() {
        return "Выход";
    }

    @Override
    public void run(Emulator emulator) {
        emulator.exitFromMenu();
    }
}
