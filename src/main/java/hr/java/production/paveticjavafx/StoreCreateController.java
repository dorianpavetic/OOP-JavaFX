package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.Item;
import hr.java.production.paveticjavafx.model.Store;
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

public class StoreCreateController {
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> itemChoiceBox;

    @FXML
    private TextField webAddressTextField;

    private static Set<Item> items = new HashSet<>();

    @FXML
    public void initialize() {
        initItems(FirstScreenController.itemList.stream(), true);
    }

    private void initItems(Stream<Item> stream, boolean clearSelectedItems) {
        if(clearSelectedItems)
            items.clear();

        final List<String> itemNames = stream.map(Item::getName).collect(Collectors.toList());
        itemChoiceBox.setItems(FXCollections.observableList(itemNames));
        itemChoiceBox.setValue("Select item");
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
        String webAddress = webAddressTextField.getText();

        StringBuilder errors = new StringBuilder();
        if(name.isBlank())
            errors.append("Name can not be empty!\n");

        if(webAddress.isBlank())
            errors.append("Web address can not be empty!\n");

        if(items.isEmpty())
            errors.append("Store must be selling at least one item!\n");

        if(!errors.isEmpty())
            DialogUtils.errorDialog(Store.class, errors).showAndWait();
        else {
            Store store = new Store(name, webAddress, new HashSet<>(items));
            FirstScreenController.storeList.add(store);

            initItems(FirstScreenController.itemList.stream(), true);

            DialogUtils.successDialog(Store.class, store).showAndWait();
        }
    }
}