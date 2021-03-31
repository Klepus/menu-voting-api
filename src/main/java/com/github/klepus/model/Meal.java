package com.github.klepus.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {
    public Meal() {
    }

    public Meal(String name) {
        this(null, name);
    }

    public Meal(Integer id, String name) {
        super(id, name);
    }
}
