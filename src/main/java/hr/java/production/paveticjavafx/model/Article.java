package hr.java.production.paveticjavafx.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Article extends NamedEntity {
    private BigInteger orderNum;
    private String market;
    private BigInteger quantity;
    private LocalDateTime modifiedDate;

    public Article(String name, BigInteger orderNum, String market, BigInteger quantity, LocalDateTime modifiedDate) {
        super(name);
        this.orderNum = orderNum;
        this.market = market;
        this.quantity = quantity;
        this.modifiedDate = modifiedDate;
    }

    public BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
