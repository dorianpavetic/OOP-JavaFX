package hr.java.production.paveticjavafx;

import hr.java.production.paveticjavafx.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MarketController {
    @FXML
    private TextField storeNameTextField;

    @FXML
    private TableView<Article> marketTableView;

    @FXML
    private TableColumn<Article, String> orderNumTableColumn;

    @FXML
    private TableColumn<Article, String> nameTableColumn;

    @FXML
    private TableColumn<Article, String> articleNameTableColumn;

    @FXML
    private TableColumn<Article, String> quantityTableColumn;

    @FXML
    private TableColumn<Article, String> modifiedDateTableColumn;

    @FXML
    private ChoiceBox<String> marketChoiceBox;

    @FXML
    private ChoiceBox<String> articleChoiceBox;

    @FXML
    private TextField quantityTextField;

    public static List<Article> articleList = new ArrayList<>();

    @FXML
    public void initialize() {
        articleList.clear();

        orderNumTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getOrderNum().toString()));

        nameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getMarket()));

        articleNameTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getName()));

        quantityTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getQuantity().toString()));

        modifiedDateTableColumn.setCellValueFactory(stringCellDataFeatures ->
                new SimpleStringProperty(stringCellDataFeatures.getValue().getModifiedDate().toString()));

        Article jabukaAjdared = new Article("Jabuka Ajdared", BigInteger.ONE, "Dućan Špansko",
                BigInteger.valueOf(20), LocalDateTime.now());
        Article kupus = new Article("Zeleni kupus", BigInteger.valueOf(2), "Dućan Špansko",
                BigInteger.valueOf(20), LocalDateTime.now());
        Article laptopLenovo = new Article("Laptop Lenovo T120", BigInteger.valueOf(3), "Trgovina MMM",
                BigInteger.valueOf(10), LocalDateTime.now().plusHours(12));

        articleList.add(jabukaAjdared);
        articleList.add(kupus);
        articleList.add(laptopLenovo);

        articleList.add(0, new Article("All", BigInteger.ZERO, "All", BigInteger.ZERO, LocalDateTime.now()));
        Set<String> marketNames = articleList.stream().map(Article::getMarket).collect(Collectors.toSet());
        marketChoiceBox.setItems(FXCollections.observableList(marketNames.stream().toList()));
        marketChoiceBox.setValue("All");

        Set<String> articleNames = articleList.stream().map(Article::getName).collect(Collectors.toSet());
        articleChoiceBox.setItems(FXCollections.observableList(articleNames.stream().toList()));
        articleChoiceBox.setValue("All");

        marketTableView.setItems(FXCollections.observableList(articleList.subList(1, articleList.size())));
    }

    public void search() {
        String marketName = marketChoiceBox.getValue();
        String articleName = articleChoiceBox.getValue();
        String quantity = quantityTextField.getText();

        if(quantity != null && !quantity.isEmpty() &&
                !marketName.equals("All") && !articleName.equals("All")) {
            StringBuilder errors = new StringBuilder();
            //Create
            BigInteger quantityBig = BigInteger.ZERO;
            try {
                quantityBig = new BigInteger(quantity);
            } catch (NumberFormatException ex) {
                errors.append("Quantity must be number\n");
            }
            Article article = articleList.stream()
                    .filter(articleItem -> articleItem.getName().equals(articleName) &&
                            articleItem.getMarket().equals(marketName))
                    .findAny()
                    .orElse(null);
            if(article == null) {
                errors.append("Cannot find item!\n");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail to modify");
                alert.setContentText(errors.toString());
                alert.showAndWait();
            }
            else {
                articleList.remove(article);

                article.setQuantity(quantityBig);
                article.setModifiedDate(LocalDateTime.now());

                articleList.add(article);

                List<Article> newList = new ArrayList<>(articleList);

                marketTableView.setItems(FXCollections.observableList(newList.subList(1, newList.size())));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success modify");
                alert.setContentText("Item is modified successfully");
                alert.showAndWait();
            }
        } else {
            List<Article> results = new ArrayList<>(articleList);
            if(marketName != null && !marketName.equals("All")) {
                results = articleList
                        .stream()
                        .filter(article -> article.getMarket().toLowerCase().contains(marketName.toLowerCase()))
                        .collect(Collectors.toList());
            }

            if(articleName != null && !articleName.equals("All")) {
                results = results
                        .stream()
                        .filter(item -> item.getName().toLowerCase().contains(articleName.toLowerCase()))
                        .collect(Collectors.toList());
            }

            marketTableView.setItems(FXCollections.observableList(results));
        }
    }
}