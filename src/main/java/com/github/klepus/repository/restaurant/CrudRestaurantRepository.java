package com.github.klepus.repository.restaurant;

import com.github.klepus.model.Restaurant;
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
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date>=:startDate AND m.date<=:endDate WHERE r.id=:id")
    Restaurant getOneWithMenusBetweenDates(@Param("id") int id,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);

    @EntityGraph(Restaurant.MENU_MEALS_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date>=:startDate AND m.date<=:endDate")
    List<Restaurant> getAllWithMenusBetweenDates(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate,
                                                 Sort sort);

    @EntityGraph(Restaurant.MENU_VOTES_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date>=:startDate AND m.date<=:endDate WHERE r.id=:id")
    Restaurant getOneWithVotesBetweenDates(@Param("id") int id,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);

    @EntityGraph(Restaurant.MENU_VOTES_GRAPH)
    @Query("SELECT r FROM Restaurant r LEFT JOIN r.menus m ON m.date>=:startDate AND m.date<=:endDate")
    List<Restaurant> getAllWithVotesBetweenDates(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate,
                                                 Sort sort);
}
