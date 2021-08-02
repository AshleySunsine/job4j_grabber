package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;

abstract class MenuItem {
    private int subMenuNumber;
    private int level = 1;
    private String name;
    private List<MenuItem> listSubMenu = new ArrayList<>();

    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem(String name, List<MenuItem> listSubMenu) {
        this.name = name;
        this.listSubMenu = listSubMenu;
    }

    public int getLevel() {
        return level;
    }

    public MenuItem setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public void showMenu() {
        System.out.print("---".repeat(this.level - 1));
        System.out.println(" " + this.name + " (" + this.level + ")");
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
}
