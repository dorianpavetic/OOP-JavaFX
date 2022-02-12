package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.Category;
import hr.java.production.paveticjavafx.model.Discount;
import hr.java.production.paveticjavafx.model.Item;
import hr.java.production.paveticjavafx.utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.math.BigDecimal;

public class ItemCreateController {
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<Category> categoryChoiceBox;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField productionCostTextField;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    public void initialize() {
        categoryChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {
                return category.getName();
            }

            @Override
            public Category fromString(String s) {
                return new Category(s, "");
            }
        });
        categoryChoiceBox.setItems(FXCollections.observableList(FirstScreenController.categoryList));
        categoryChoiceBox.setValue(new Category("Select category", ""));
    }

    public void create() {
        String name = nameTextField.getText();
        String widthText = widthTextField.getText();
        String heightText = heightTextField.getText();
        String lengthText = lengthTextField.getText();
        String productionCostText = productionCostTextField.getText();
        String sellingPriceText = sellingPriceTextField.getText();
        Category selectedCategory = categoryChoiceBox.getValue();

        StringBuilder errors = new StringBuilder();
        if(name.isBlank())
            errors.append("Name can not be empty!\n");

        if(selectedCategory.getName().equals("Select category"))
            errors.append("Must select a Category!\n");

        BigDecimal width = getNumber(widthText, errors, "Width");
        BigDecimal height = getNumber(heightText, errors, "Height");
        BigDecimal length = getNumber(lengthText, errors, "Length");
        BigDecimal productionCost = getNumber(productionCostText, errors, "Production cost");
        BigDecimal sellingPrice = getNumber(sellingPriceText, errors, "Selling price");

        if(!errors.isEmpty())
            DialogUtils.errorDialog(Item.class, errors).showAndWait();
        else {
            Item item = new Item(name, selectedCategory, width, height, length,
                    productionCost, sellingPrice, new Discount(BigDecimal.ZERO));
            FirstScreenController.itemList.add(item);
            DialogUtils.successDialog(Item.class, item).showAndWait();
        }
    }

    private BigDecimal getNumber(String numberString, StringBuilder errors, String field) {
        BigDecimal number = BigDecimal.ZERO;
        if(numberString.isBlank()) {
            errors.append(field).append(" can not be empty!\n");
        }
        else {
            try {
                number = new BigDecimal(numberString);
            } catch (NumberFormatException ex) {
                errors.append(field).append(" is not a valid number format!\n");
            }
        }

        return number;
    }
}