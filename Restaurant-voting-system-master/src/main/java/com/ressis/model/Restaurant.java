package com.ressis.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@NamedEntityGraphs({
        @NamedEntityGraph(name = Restaurant.MENU_MEALS_GRAPH,
                attributeNodes = @NamedAttributeNode(value = "menus", subgraph = "menus_meals"),
                subgraphs = @NamedSubgraph(name = "menus_meals", attributeNodes = @NamedAttributeNode("menuMeals"))),
        @NamedEntityGraph(name = Restaurant.MENU_VOTES_GRAPH,
                attributeNodes = @NamedAttributeNode(value = "menus", subgraph = "menus_votes"),
                subgraphs = @NamedSubgraph(name = "menus_votes", attributeNodes = @NamedAttributeNode("votes")))
})
public class Restaurant extends AbstractNamedEntity {
    public static final String MENU_MEALS_GRAPH = "RestaurantEntityGraph[menu_meals]";
    public static final String MENU_VOTES_GRAPH = "RestaurantEntityGraph[menu_votes]";

    public Restaurant() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Menu> menus;

    public Restaurant(String name) {
        this(null, name);
    }
    
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
