package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;

public class MainClass {
    public static void main(String[] args) {
        Action action = new FindAction();
        MenuItem menu111 = new Menu("Point 1.1.1", action);
        MenuItem menu112 = new Menu("Point 1.1.2", action);
        MenuItem menu11 = new Menu("Point 1.1", action, List.of(menu111, menu112));
        MenuItem menu12 = new Menu("Point 1.2", action);
        MenuItem menu1 = new Menu("Point 1", action, List.of(menu11, menu12));
        List<MenuItem> menus = menu1.getMenu();
        for (var menu : menus) {
            menu.showMenu();
        }

        String runMenu = "Point 1.1.2";
        Optional<MenuItem> menu = menu1.findMenuByName(runMenu);
        menu.ifPresent(MenuItem::run);
    }
}
