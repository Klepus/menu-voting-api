package com.github.klepus.service.menu;


import com.github.klepus.model.Menu;
import com.github.klepus.to.menu.MenuTo;

import java.time.LocalDate;

public interface MenuService {
    Menu create(MenuTo menu);

    void update(MenuTo menu);

    void makeVote(int menuId, int userId, LocalDate date);
}
