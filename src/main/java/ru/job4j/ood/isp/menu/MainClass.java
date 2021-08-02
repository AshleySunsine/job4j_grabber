package ru.job4j.ood.isp.menu;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        MenuItem menu111 = new Menu("Point 1.1.1");
        MenuItem menu112 = new Menu("Point 1.1.2");
        MenuItem menu11 = new Menu("Point 1.1", List.of(menu111, menu112));
        MenuItem menu12 = new Menu("Point 1.2");
        MenuItem menu1 = new Menu("Point 1", List.of(menu11, menu12));
        List<MenuItem> menus = menu1.getMenu();
        for (var menu : menus) {
            menu.showMenu();
        }

        String runMenu = "Point 1.1.2";
         for (var menu : menus) {
             if (menu.getName().equals(runMenu)) {
                 menu.run();
             }
         }
    }
}
