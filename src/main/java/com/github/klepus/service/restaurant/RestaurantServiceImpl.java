package com.github.klepus.service.restaurant;

import com.github.klepus.model.Restaurant;
import com.github.klepus.repository.CrudRestaurantRepository;
import com.github.klepus.to.RestaurantTo;
import com.github.klepus.to.RestaurantWithMenuMealsTo;
import com.github.klepus.to.RestaurantWithVoteTo;
import com.github.klepus.util.RestaurantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.github.klepus.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Sort SORT_NAME_ASC = Sort.by("name");

    @Autowired
    private CrudRestaurantRepository repository;

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public RestaurantTo get(int id) {
        return RestaurantsUtil.create(checkNotFoundWithId(repository.findById(id).orElse(null), id));
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantWithMenuMealsTo getOneWithMenuOnDate(int id, LocalDate date) {
        return RestaurantsUtil.createWithMeals(checkNotFoundWithId(repository.getOneWithMenuOnDate(id, date), id));
    }

    @Override
    public RestaurantWithVoteTo getOneWithVoteOnDate(int id, LocalDate date) {
        return RestaurantsUtil.createWithVote(checkNotFoundWithId(repository.getOneWithVoteOnDate(id, date), id));
    }

    @Override
    public List<RestaurantTo> getAll() {
        return RestaurantsUtil.create(repository.findAll(SORT_NAME_ASC));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantWithMenuMealsTo> getAllWithMenusOnDate(LocalDate date) {
        return RestaurantsUtil.createWithMeals(repository.getAllWithMenusOnDate(date, SORT_NAME_ASC));
    }

    @Override
    public List<RestaurantWithVoteTo> getAllWithVotesOnDate(LocalDate date) {
        return RestaurantsUtil.createWithVote(repository.getAllWithVotesOnDate(date, SORT_NAME_ASC));
    }
}