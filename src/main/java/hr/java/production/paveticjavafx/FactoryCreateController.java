package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.enums.City;
import hr.java.production.paveticjavafx.model.Address;
import hr.java.production.paveticjavafx.model.Factory;
import hr.java.production.paveticjavafx.model.Item;
import hr.java.production.paveticjavafx.utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoryCreateController {
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> itemChoiceBox;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    private static Set<Item> items = new HashSet<>();

    @FXML
    public void initialize() {
        final List<String> itemNames = FirstScreenController.itemList.stream().map(Item::getName).collect(Collectors.toList());
        itemChoiceBox.setItems(FXCollections.observableList(itemNames));
        itemChoiceBox.setValue("Select item");

        initItems(FirstScreenController.itemList.stream(), true);
    }

    public void add() {
        String itemName = itemChoiceBox.getValue();
        if(itemName.equals("Select item")) {
            StringBuilder errors = new StringBuilder();
            errors.append("Must select Item");
            DialogUtils.errorDialog(Item.class, errors).showAndWait();
        }
        else {
            Item selectedItem = FirstScreenController.itemList
                            .stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findAny()
                    .orElse(null);
            if(selectedItem == null) {
                StringBuilder errors = new StringBuilder();
                errors.append("Must select Item");
                DialogUtils.errorDialog(Item.class, errors).showAndWait();
            }
            else {
                items.add(selectedItem);
                Set<Item> itemsSet = new HashSet<>(FirstScreenController.itemList);
                itemsSet.removeAll(items);

                initItems(itemsSet.stream(), false);
            }

        }
    }

    public void create() {
        String name = nameTextField.getText();
        String street = streetTextField.getText();
        String houseNumber = houseNumberTextField.getText();
        String cityName = cityChoiceBox.getValue();

        StringBuilder errors = new StringBuilder();
        if(name.isBlank())
            errors.append("Name can not be empty!\n");

        if(street.isBlank())
            errors.append("Street can not be empty!\n");

        if(houseNumber.isBlank())
            errors.append("House number can not be empty!\n");

        City city = null;
        if(cityName == null)
            errors.append("Must select a City!\n");
        else {
            try {
                city = City.valueOf(cityName.toUpperCase());
            } catch (IllegalArgumentException ex) {
                errors.append("Can not find selected City!\n");
            }
        }

        if(items.isEmpty())
            errors.append("Factory must be producing at least one item!\n");

        if(!errors.isEmpty())
            DialogUtils.errorDialog(Factory.class, errors).showAndWait();
        else {
            Address address = new Address.Builder()
                    .street(street)
                    .houseNumber(houseNumber)
                    .city(city)
                    .build();
            Factory factory = new Factory(name, address, new HashSet<>(items));
            FirstScreenController.factoryList.add(factory);

            initItems(FirstScreenController.itemList.stream(), true);

            DialogUtils.successDialog(Factory.class, factory).showAndWait();
        }
    }

    private void initItems(Stream<Item> stream, boolean clearSelectedItems) {
        if(clearSelectedItems)
            items.clear();

        final List<String> itemNames = stream.map(Item::getName).collect(Collectors.toList());
        itemChoiceBox.setItems(FXCollections.observableList(itemNames));
        itemChoiceBox.setValue("Select item");
    }
}