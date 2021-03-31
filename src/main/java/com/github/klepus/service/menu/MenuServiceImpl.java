package com.github.klepus.service.menu;

import com.github.klepus.model.Menu;
import com.github.klepus.model.MenuMeal;
import com.github.klepus.repository.CrudMealRepository;
import com.github.klepus.repository.CrudMenuRepository;
import com.github.klepus.repository.CrudRestaurantRepository;
import com.github.klepus.to.menu.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.klepus.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private CrudMenuRepository menuRepository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    private CrudMealRepository mealRepository;

    @Override
    public Menu create(MenuTo menuTo) {
        Assert.notNull(menuTo, "menuTo must not be null");
        return menuRepository.save(getForCreation(menuTo));
    }

    @Override
    public void update(MenuTo menuTo) {
        checkNotFoundWithId(menuRepository.save(getForUpdate(menuTo)), menuTo.getId());
    }

    @Override
    public void makeVote(int menuId, int userId, LocalDate date) {
        menuRepository.makeVote(menuId, userId, date);
    }

    private Menu getForCreation(MenuTo menuTo) {
        Menu menu = new Menu();
        menu.setDate(menuTo.getDate());
        menu.setRestaurant(restaurantRepository.getOne(menuTo.getRestaurantId()));

        Set<MenuMeal> menuMeals = menuTo.getMeals().stream()
                .map(m -> new MenuMeal(mealRepository.getOne(m.getId()), m.getPrice()))
                .collect(Collectors.toSet());
        menu.setMenuMeals(menuMeals);

        return menu;
    }

    private Menu getForUpdate(MenuTo menuTo) {
        Menu menu = getForCreation(menuTo);
        menu.setId(menuTo.getId());
        menu.setVotes(Objects.requireNonNull(menuRepository.findById(menu.getId()).orElse(null)).getVotes());
        return menu;
    }
}
