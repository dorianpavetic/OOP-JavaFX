package hr.java.production.paveticjavafx.model;

import java.util.Objects;

/**
 * Used for entities that need to have name field.
 */
public abstract class NamedEntity {
    private String name;

    /**
     * Create new NamedEntity with provided name.
     *
     * @param name entity name.
     */
    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedEntity that = (NamedEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
