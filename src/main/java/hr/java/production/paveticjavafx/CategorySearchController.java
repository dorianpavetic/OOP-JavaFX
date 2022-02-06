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

public class CategorySearchController {
    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> nameTableColumn;

    @FXML
    private TableColumn<Category, String> descriptionTableColumn;

    public static List<Category> categoryList = new ArrayList<>();

    @FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getName()));

        descriptionTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getDescription()));

        Scanner scanner = new Scanner(System.in);
        Category[] categories = Main.getCategoryInputs(scanner);
        final List<Category> categoriesList = new ArrayList<>(Arrays.asList(categories));

        categoryList.clear();
        categoryList.addAll(categoriesList);

        categoryTableView.setItems(FXCollections.observableList(categoriesList));
    }

    public void search() {
        String categoryName = categoryNameTextField.getText();

        List<Category> results =
                categoryList
                        .stream()
                        .filter(category -> category.getName().toLowerCase().contains(categoryName.toLowerCase()))
                        .collect(Collectors.toList());

        categoryTableView.setItems(FXCollections.observableList(results));
    }
}