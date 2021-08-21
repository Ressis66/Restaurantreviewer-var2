package com.ressis.to;

import java.util.Objects;

public class BaseTo {
    private int id;

    public BaseTo() {

    }

    public BaseTo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTo baseTo = (BaseTo) o;
        return id == baseTo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseTo{" +
                "id=" + id +
                '}';
    }
}
