package hr.java.production.paveticjavafx.model;

import java.util.Objects;
import java.util.Set;

/**
 * Sells items of type {@link Item}.
 */
public class Store extends NamedEntity {
    private String webAddress;
    private Set<Item> items;

    /**
     * Creates new store with name, web address and selling items.
     *
     * @param name name of the store.
     * @param webAddress web address of the store.
     * @param items items that this store sells.
     */
    public Store(String name, String webAddress, Set<Item> items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return Store.class.getSimpleName()
                .concat(" ")
                .concat(getName())
                .concat(" sells ")
                .concat(String.valueOf(items.size()))
                .concat(" items")
                .concat(". Web address: ")
                .concat(webAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(webAddress, store.webAddress) && Objects.equals(items, store.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), webAddress, items);
    }
}
