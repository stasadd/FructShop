package TD;

import GV.Client;
import GV.Clients;
import GV.Fruit;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FruitShop {

    public class Delivery {
        Fruit[] fruits;
    }

    public class ShopForJson {
        int moneyBalance;
        List<Fruit> fruits;
        String mainFile;
    }

    public List<Fruit> fruits = new ArrayList<Fruit>();
    private String mainFile = "Files\\SHOP.txt";
    private Gson gson = new Gson();
    private int moneyBalance = 0;

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public void addMoneyBalance(int moneyBalance) {
        this.moneyBalance += moneyBalance;
    }

    public String getMainFile() { return mainFile; }

    public void setMainFile(String shopName) {
        this.mainFile = "Files\\" + shopName + ".txt";
    }

    public FruitShop() {
        load(mainFile);
        addFruits("Files\\Delivery.txt");
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
        ShopForJson shopForJson = new ShopForJson();
        shopForJson.fruits = this.fruits;
        shopForJson.moneyBalance = this.moneyBalance;
        shopForJson.mainFile = this.mainFile;
        String json = gson.toJson(shopForJson);
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
            ShopForJson shopForJson = gson.fromJson(json, ShopForJson.class);
            this.fruits = shopForJson.fruits;
            this.moneyBalance = shopForJson.moneyBalance;
            this.mainFile = shopForJson.mainFile;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            moneyBalance = 0;
            fruits = new ArrayList<>();
            mainFile = "Files\\SHOP.txt";
        }
    }

    public List<Fruit> getSpoiledFruits(Date date) throws Exception {
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            Date date1 = f.getDate();
            c.setTime(date1);
            c.add(Calendar.DATE,f.getShelfLife());
            date1 = c.getTime();
            if ((date.compareTo(date1)) > 0)
                Spoiled.add(f);
        }
        return Spoiled;
    }

    public List<Fruit> getAvailableFruits(Date date) throws Exception {
        List<Fruit> Available = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            Date date1  = f.getDate();
            c.setTime(date1);
            c.add(Calendar.DATE,f.getShelfLife());
            date1 = c.getTime();
            if ((date.compareTo(date1)) <= 0)
                Available.add(f);
        }
        return Available;
    }

    public List<Fruit> getAddedFruits(Date date) throws Exception {
        List<Fruit> Added = new ArrayList<Fruit>();
        for (Fruit f : this.fruits) {
            if ((date.compareTo(f.getDate())) == 0)
                Added.add(f);
        }
        return Added;
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) throws Exception{
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            if (f.getType().equals(type)) {
                Date date1  = f.getDate();
                c.setTime(date1);
                c.add(Calendar.DATE,f.getShelfLife());
                date1 = c.getTime();
                if ((date.compareTo(date1) > 0))
                    Spoiled.add(f);
            }
        }
        return Spoiled;
    }

    public List<Fruit> getAvailableFruits(Date date, FruitType type) throws Exception {
        List<Fruit> Available = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            if(f.getType().equals(type)) {
                Date date1  = f.getDate();
                c.setTime(date1);
                c.add(Calendar.DATE,f.getShelfLife());
                date1 = c.getTime();
                if ((date.compareTo(date1)) <= 0)
                    Available.add(f);
            }
        }
        return Available;
    }

    public List<Fruit> getAddedFruits(Date date, FruitType type) throws Exception {
        List<Fruit> Added = new ArrayList<Fruit>();
        for (Fruit f : this.fruits) {
            if (f.getType().equals(type) && (date.compareTo(f.getDate())) == 0)
                Added.add(f);
        }
        return Added;
    }

    public void sell(String pathToJsonFile) {
        try {
            String json = FileLoader.loadString(pathToJsonFile);
            Clients clientsObj = gson.fromJson(json, Clients.class);
            for(Client client : clientsObj.clients) {
                List<Fruit> clientNeed = getAvailableFruits(new Date(), client.type);
                if(client.count <= clientNeed.size()) {
                    for(int i = 0; i < client.count; i++) {
                        addMoneyBalance(clientNeed.get(i).getPrice());
                        this.fruits.remove(clientNeed.get(i));
                    }
                    System.out.println(client.name + " придбав " + client.count + " одиниць товару " + client.type.name());
                    save(getMainFile());
                }
                else {
                    System.out.println("Товару " + client.type.name() + " не вистачає для покупця " + client.name);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}