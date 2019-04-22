package TD;

import GV.Fruit;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FruitShop {

    public List<Fruit> fruits = new ArrayList<Fruit>();
    private Double moneyBalance;
    Gson gson = new Gson();

    public FruitShop() {
        addFruits("Files\\Delivery");
        
    }

    public void addFruits(String pathToJsonFile) {
        class Delivery {
            Fruit[] fruits;
        }

        try {
            String json = FileLoader.loadString(pathToJsonFile);
            System.out.println(json);
            Delivery delivery = gson.fromJson(json, Delivery.class);
            if(delivery != null && delivery.fruits != null) {
                for (Fruit f : delivery.fruits) {
                    this.fruits.add(f);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }





}
