package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;

public class Menu extends MenuItem {
    public Menu(String name, Action action) {
        super(name, action);
    }

    public Menu(String name, Action action, List<MenuItem> listSubMenu) {
        super(name, action, listSubMenu);
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
    public Optional<MenuItem> findMenuByName(String name) {
        return super.findMenuByName(name);
    }

    @Override
    public void run() {
        super.run();
    }
}
