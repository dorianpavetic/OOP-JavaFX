package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.main.Main;
import hr.java.production.paveticjavafx.model.Category;
import hr.java.production.paveticjavafx.model.Factory;
import hr.java.production.paveticjavafx.model.Item;
import hr.java.production.paveticjavafx.model.Store;
import javafx.fxml.FXML;

import java.util.*;

public class FirstScreenController {
    public static List<Category> categoryList = new ArrayList<>();
    public static List<Item> itemList = new ArrayList<>();
    public static List<Factory> factoryList = new ArrayList<>();
    public static List<Store> storeList = new ArrayList<>();

    @FXML
    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = Main.getCategoryInputs(scanner);
        final List<Category> categoriesList = new ArrayList<>(Arrays.asList(categories));
        categoryList.clear();
        categoryList.addAll(categoriesList);

        Set<Item> items = Main.getItemInputs(scanner, categoriesList);
        itemList.clear();
        itemList.addAll(items);

        Factory[] factories = Main.getFactoryInputs(scanner, items);
        factoryList.clear();
        factoryList.addAll(Arrays.asList(factories));

        Store[] stores = Main.getStoreInputs(scanner, items);
        storeList.clear();
        storeList.addAll(Arrays.asList(stores));
    }
}