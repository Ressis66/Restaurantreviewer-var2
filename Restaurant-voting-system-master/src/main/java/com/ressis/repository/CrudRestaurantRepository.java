package com.ressis.repository;

import com.vahundos.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Optional<Restaurant> findById(Integer integer);

    @Override
    List<Restaurant> findAll(Sort sort);

    @EntityGraph(Restaurant.MENU_MEALS_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date=:date WHERE r.id=:id")
    Restaurant getOneWithMenuOnDate(@Param("id") int id, @Param("date") LocalDate date);

    @EntityGraph(Restaurant.MENU_MEALS_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date=:date")
    List<Restaurant> getAllWithMenusOnDate(@Param("date") LocalDate date, Sort sort);

    @EntityGraph(Restaurant.MENU_VOTES_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date=:date WHERE r.id=:id")
    Restaurant getOneWithVoteOnDate(@Param("id") int id, @Param("date") LocalDate date);

    @EntityGraph(Restaurant.MENU_VOTES_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date=:date")
    List<Restaurant> getAllWithVotesOnDate(@Param("date") LocalDate date, Sort sort);
}
