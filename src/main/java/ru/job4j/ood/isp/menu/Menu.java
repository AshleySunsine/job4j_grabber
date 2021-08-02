package ru.job4j.ood.isp.menu;

import java.util.List;

public class Menu extends MenuItem {
    public Menu(String name) {
        super(name);
    }

    public Menu(String name, List<MenuItem> listSubMenu) {
        super(name, listSubMenu);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void showMenu() {
        super.showMenu();
    }

    @Override
    public List<MenuItem> getMenu() {
        return super.getMenu();
    }

    @Override
    public void run() {
        super.run();
    }
}
