package com.ressis.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
@BatchSize(size = 20)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "meal")
public class Meal extends AbstractNamedEntity {
    public Meal() {
    }

    public Meal(String name) {
        this(null, name);
    }

    public Meal(Integer id, String name) {
        super(id, name);
    }

    public Meal(Meal meal) {
        this(meal.id, meal.name);
    }
}
