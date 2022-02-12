package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.NamedEntity;
import hr.java.production.paveticjavafx.model.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
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

        storeTableView.setItems(FXCollections.observableList(FirstScreenController.storeList));
    }

    public void search() {
        String storeName = storeNameTextField.getText();

        List<Store> results =
                FirstScreenController.storeList
                        .stream()
                        .filter(store -> store.getName().toLowerCase().contains(storeName.toLowerCase()))
                        .collect(Collectors.toList());

        storeTableView.setItems(FXCollections.observableList(results));
    }
}