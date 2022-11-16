package com.codestates.chapter2.di;

import java.util.List;

public class MenuServiceStub extends MenuSerice {

    @Override
    public List<Menu> getMenuList(){
        return List.of(
                new Menu(1, "아이스 아메리카노", 2500),
                new Menu(2, "아이스 카페라떼", 2500),
                new Menu(3, "아이스 바닐라 라떼", 2500)

        );
    }

}
