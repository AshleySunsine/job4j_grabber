package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class MenuItem {
    private int level = 1;
    private String name;
    private List<MenuItem> listSubMenu = new ArrayList<>();
    private Action action;

    public MenuItem(String name, Action action) {
        this.action = action;
        this.name = name;
    }

    public MenuItem(String name, Action action, List<MenuItem> listSubMenu) {
        this.name = name;
        this.listSubMenu = listSubMenu;
        this.action = action;
    }


    protected MenuItem setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public void showMenu() {
        System.out.print("---".repeat(this.level - 1) + ">");
        System.out.println(this.name + " (level " + this.level + ")");
    }

    public List<MenuItem> getMenu() {
        List<MenuItem> listing = new ArrayList<>();
        listing.add(this);
        listing.addAll(getSubMenu(level + 1));
        return listing;
    }

    private List<MenuItem> getSubMenu(int level) {

        List<MenuItem> listing = new ArrayList<>();
        if (!listSubMenu.isEmpty()) {
            for (var menuItem : listSubMenu) {
                menuItem.setLevel(level);
                List<MenuItem> subMenus = menuItem.getMenu();
                listing.addAll(subMenus);
            }
        }
        return listing;
    }

    public Optional<MenuItem> findMenuByName(String name) {
        Optional<MenuItem> findedMenu = Optional.empty();
        if (this.name.equals(name)) {
            findedMenu = Optional.of(this);
            return findedMenu;
        } else {
            if (!this.listSubMenu.isEmpty()) {
                for (var subMenu : listSubMenu) {
                    findedMenu = subMenu.findMenuByName(name);
                    if (findedMenu.isPresent()) {
                        return findedMenu;
                    }
                }
            }
        }
        return findedMenu;
    }

    public void run() {
        this.action.act();
    }
}
