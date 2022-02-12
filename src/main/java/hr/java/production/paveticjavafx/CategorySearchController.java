package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchController {
    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> nameTableColumn;

    @FXML
    private TableColumn<Category, String> descriptionTableColumn;

    @FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getName()));

        descriptionTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getDescription()));

        categoryTableView.setItems(FXCollections.observableList(FirstScreenController.categoryList));
    }

    public void search() {
        String categoryName = categoryNameTextField.getText();

        List<Category> results =
                FirstScreenController.categoryList
                        .stream()
                        .filter(category -> category.getName().toLowerCase().contains(categoryName.toLowerCase()))
                        .collect(Collectors.toList());

        categoryTableView.setItems(FXCollections.observableList(results));
    }
}