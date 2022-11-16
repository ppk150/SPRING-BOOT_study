package com.codestates.chapter2.di;

import java.util.List;

public class MenuController {

    private  MenuSerice menuSerice;
    public  MenuController (MenuSerice menuSerice){
        this.menuSerice = menuSerice;
    }

    public List<Menu> getMenus(){
        return menuSerice.getMenuList();
    }

}
