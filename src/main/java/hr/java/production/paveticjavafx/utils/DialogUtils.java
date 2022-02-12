package hr.java.production.paveticjavafx.utils;

import javafx.scene.control.Alert;

public class DialogUtils {
    public static Alert errorDialog(Class<?> clazz, StringBuilder errors) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error creating " + clazz.getSimpleName());
        alert.setHeaderText("Some input fields are empty or invalid!");
        alert.setContentText(errors.toString());
        return alert;
    }

    public static <T> Alert successDialog(Class<T> clazz, T resource) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(clazz.getSimpleName() + " created successfully");
        alert.setHeaderText("You have successfully created a resource type " + clazz.getSimpleName());
        alert.setContentText("Info about created resource:\n\t" + resource.toString());
        return alert;
    }
}
