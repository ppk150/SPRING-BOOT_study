package com.codestates.chapter2.di;

import java.util.List;

public class CafeClient {
    public static void main(String[] args) {
        MenuSerice menuSerice = new MenuServiceStub();
        MenuController controller = new MenuController(menuSerice);
        List<Menu> menuList = controller.getMenus();
    }
}
