package hr.java.production.paveticjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class IzbornikController {
    public static final int DIM = 700;

    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "category-search.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showCategoryCreateScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "category-create.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "item-search.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showItemCreateScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "item-create.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "factory-search.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showFactoryCreateScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "factory-create.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "store-search.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showStoreCreateScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource( "store-create.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DIM, DIM); } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }
}
