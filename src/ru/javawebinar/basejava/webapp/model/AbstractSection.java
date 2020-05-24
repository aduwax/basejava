package ru.javawebinar.basejava.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractSection implements Serializable {
    private final String title;

    AbstractSection(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSection that = (AbstractSection) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
