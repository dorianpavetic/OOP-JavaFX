package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.main.Main;
import hr.java.production.paveticjavafx.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.stream.Collectors;

public class StoreSearchController {
    @FXML
    private TextField storeNameTextField;

    @FXML
    private TableView<Store> storeTableView;

    @FXML
    private TableColumn<Store, String> nameTableColumn;

    @FXML
    private TableColumn<Store, String> webAddressTableColumn;

    @FXML
    private TableColumn<Store, String> itemsTableColumn;

    public static List<Store> storeList = new ArrayList<>();

    @FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getName()));

        webAddressTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getWebAddress()));

        itemsTableColumn.setCellValueFactory(stringCellDataFeatures -> {
            List<String> names = stringCellDataFeatures.getValue().getItems().stream().map(NamedEntity::getName).collect(Collectors.toList());
            final String namesString = names.toString();
            return new SimpleStringProperty(namesString.substring(1, namesString.length()-1));
        });

        Scanner scanner = new Scanner(System.in);
        Category[] categories = Main.getCategoryInputs(scanner);
        final List<Category> categoriesList = new ArrayList<>(Arrays.asList(categories));
        Set<Item> items = Main.getItemInputs(scanner, categoriesList);
        Store[] stores = Main.getStoreInputs(scanner, items);

        storeList.clear();
        storeList.addAll(Arrays.asList(stores));

        storeTableView.setItems(FXCollections.observableList(storeList));
    }

    public void search() {
        String storeName = storeNameTextField.getText();

        List<Store> results =
                storeList
                        .stream()
                        .filter(store -> store.getName().toLowerCase().contains(storeName.toLowerCase()))
                        .collect(Collectors.toList());

        storeTableView.setItems(FXCollections.observableList(results));
    }
}