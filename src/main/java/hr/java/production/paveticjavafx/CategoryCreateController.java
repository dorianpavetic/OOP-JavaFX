package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.Category;
import hr.java.production.paveticjavafx.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CategoryCreateController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField descriptionTextField;

    public void create() {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();

        StringBuilder errors = new StringBuilder();
        if(name.isBlank())
            errors.append("Category name can not be empty!\n");
        else {
            boolean categoryExists = FirstScreenController.categoryList
                    .stream()
                    .anyMatch(category -> category.getName().equalsIgnoreCase(name));
            if(categoryExists)
                errors.append("Category already exists!\n");
        }

        if(description.isBlank())
            errors.append("Category description can not be empty!\n");

        if(!errors.isEmpty())
            DialogUtils.errorDialog(Category.class, errors).showAndWait();
        else {
            Category category = new Category(name, description);
            FirstScreenController.categoryList.add(category);
            DialogUtils.successDialog(Category.class, category).showAndWait();
        }
    }
}