package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.main.Main;
import hr.java.production.paveticjavafx.model.Category;
import hr.java.production.paveticjavafx.model.Item;
import hr.java.production.paveticjavafx.model.NamedEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.stream.Collectors;

public class ItemSearchController {
    @FXML
    private TextField itemNameTextField;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TableView<Item> itemTableView;

    @FXML
    private TableColumn<Item, String> nameTableColumn;

    @FXML
    private TableColumn<Item, String> categoryTableColumn;

    @FXML
    private TableColumn<Item, String> widthTableColumn;

    @FXML
    private TableColumn<Item, String> heightTableColumn;

    @FXML
    private TableColumn<Item, String> lengthTableColumn;

    @FXML
    private TableColumn<Item, String> productionCostsTableColumn;

    @FXML
    private TableColumn<Item, String> sellingPriceTableColumn;

    public static List<Item> itemList = new ArrayList<>();

    @FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getName()));

        categoryTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getCategory().getName()));

        widthTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getWidth().toString()));

        heightTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getHeight().toString()));

        lengthTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getLength().toString()));

        productionCostsTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getProductionCost().toString()));

        sellingPriceTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getSellingPrice().toString()));

        Scanner scanner = new Scanner(System.in);
        Category[] categories = Main.getCategoryInputs(scanner);
        final List<Category> categoriesList = new ArrayList<>(Arrays.asList(categories));
        Set<Item> items = Main.getItemInputs(scanner, categoriesList);

        categoriesList.add(0, new Category("Any", ""));
        categoryChoiceBox.setItems(FXCollections.observableList(categoriesList.stream().map(NamedEntity::getName).collect(Collectors.toList())));
        categoryChoiceBox.setValue("Any");

        itemList.clear();
        itemList.addAll(items);

        itemTableView.setItems(FXCollections.observableList(itemList));
    }

    public void search() {
        String itemName = itemNameTextField.getText();
        String categoryName = categoryChoiceBox.getValue();

        List<Item> results =
                itemList
                        .stream()
                        .filter(item -> item.getName().toLowerCase().contains(itemName.toLowerCase()))
                        .collect(Collectors.toList());

        if(categoryName != null && !categoryName.equals("Any")) {
            results = results
                    .stream()
                    .filter(item -> item.getCategory().getName().toLowerCase().contains(categoryName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        itemTableView.setItems(FXCollections.observableList(results));
    }
}