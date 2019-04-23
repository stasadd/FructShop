package GV;

import TD.FruitType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fruit {
    FruitType type;
    int shelfLife;
    String date;
    int price;

    public FruitType getType() {
        return type;
    }

    public void setType(FruitType type) {
        this.type = type;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getDate() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(this.date);
    }

    public String getStrDate() {
        return this.date;
    }

    public void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.date = format.format(date);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Fruit() {
    }

    public Fruit(FruitType type, int shelfLife, Date date, int price) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = format.format(date);
        this.price = price;
    }

    @Override
    public String toString() {
        return getType() + ": "  + getPrice() + " - " + getStrDate() + " - " + getShelfLife();
    }
}
