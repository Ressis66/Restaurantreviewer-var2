package com.ressis.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {
    public Menu() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date")
    private LocalDate date;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "menu_meals", joinColumns = @JoinColumn(name = "menu_id"))
    private Set<MenuMeal> menuMeals;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "votes", joinColumns = @JoinColumn(name = "menu_id"))
    private Set<Vote> votes;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<MenuMeal> getMenuMeals() {
        return menuMeals;
    }

    public void setMenuMeals(Set<MenuMeal> menuMeals) {
        this.menuMeals = menuMeals;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
