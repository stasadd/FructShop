package TD;

import GV.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FruitShop {

    public class Delivery {
        Fruit[] fruits;
    }

    public List<Fruit> fruits = new ArrayList<Fruit>();
    private String mainFile = "Files\\SHOP.txt";
    Gson gson = new Gson();

    public FruitShop() {
        load(mainFile);
    }

    public void addFruits(String pathToJsonFile) {
        try {
            String json = FileLoader.loadString(pathToJsonFile);
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

    public void save(String pathToJsonFile) {
        String json = gson.toJson(this.fruits);
        System.out.println(json);
        try (FileWriter writer = new FileWriter(pathToJsonFile) ) {
            writer.write(json);
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void load(String pathToJsonFile) {
        try {
            String json = FileLoader.loadString(pathToJsonFile);
            Type collectionType = new TypeToken<List<Fruit>>(){}.getType();
            fruits = gson.fromJson(json, collectionType);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }





}
