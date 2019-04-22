package GV;

import TD.FruitType;

public class Fruit {
    FruitType type;
    int daysExpiration;
    String dateDelivery;
    double price;

    public Fruit() {
    }

    public Fruit(FruitType type, int daysExpiration, String dateDelivery, double price) {
        this.type = type;
        this.daysExpiration = daysExpiration;
        this.dateDelivery = dateDelivery;
        this.price = price;
    }

    public FruitType getType() {
        return type;
    }

    public void setType(FruitType type) {
        this.type = type;
    }

    public int getDaysExpiration() {
        return daysExpiration;
    }

    public void setDaysExpiration(int daysExpiration) {
        this.daysExpiration = daysExpiration;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
