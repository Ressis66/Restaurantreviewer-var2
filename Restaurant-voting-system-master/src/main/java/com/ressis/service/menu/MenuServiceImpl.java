package com.ressis.service.menu;

import com.ressis.model.Menu;
import com.ressis.model.MenuMeal;
import com.ressis.repository.CrudMealRepository;
import com.ressis.repository.CrudMenuRepository;
import com.ressis.repository.CrudRestaurantRepository;
import com.ressis.to.menu.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ressis.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    private final CrudMenuRepository menuRepository;

    private final CrudRestaurantRepository restaurantRepository;

    private final CrudMealRepository mealRepository;

    @Autowired
    public MenuServiceImpl(CrudMenuRepository menuRepository, CrudRestaurantRepository restaurantRepository, CrudMealRepository mealRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
    public Menu create(MenuTo menuTo) {
        Assert.notNull(menuTo, "menuTo must not be null");
        return menuRepository.save(getForCreation(menuTo));
    }

    @Override
    @CacheEvict(value = "restaurantsWithMenu", allEntries = true)
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
