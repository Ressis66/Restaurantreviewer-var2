package com.ressis.service.menu;

import com.ressis.model.Menu;
import com.ressis.to.menu.MenuTo;

import java.time.LocalDate;

public interface MenuService {
    Menu create(MenuTo menu);

    void update(MenuTo menu);

    void makeVote(int menuId, int userId, LocalDate date);
}
