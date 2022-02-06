package hr.java.production.paveticjavafx.sort;

import hr.java.production.paveticjavafx.model.Item;

import java.util.Comparator;

public record ProductionSorter(boolean isAscending) implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        if (isAscending) //Lowest to highest
            return o1.getSellingPrice().compareTo(o2.getSellingPrice());
        else //Highest to lowest
            return o2.getSellingPrice().compareTo(o1.getSellingPrice());
    }
}
