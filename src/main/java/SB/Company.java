package SB;

import GV.Fruit;
import TD.FileLoader;
import TD.FruitShop;
import TD.FruitType;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Company {

    public class CompanyForJson {
        int moneyBalance;
        List<FruitShop> fruitShops;
        String mainFile;
    }

    public List<FruitShop> fruitShops = new ArrayList<>();
    private String mainFile = "Files\\COMPANY.txt";
    private Gson gson = new Gson();
    private int moneyBalance = 0;

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public int getCompanyBalance() {
        int balance = 0;
        for(FruitShop shop : fruitShops)
            balance += shop.getMoneyBalance();
        this.moneyBalance = balance;
        return balance;
    }

    public String getMainFile() {
        return mainFile;
    }

    public void setMainFile(String companyName) {
        this.mainFile = "Files\\" + companyName + ".txt";
    }

    public Company() {
    }

    public void save(String pathToJsonFile) {
        CompanyForJson companyForJson = new CompanyForJson();
        companyForJson.fruitShops = this.fruitShops;
        companyForJson.moneyBalance = this.moneyBalance;
        companyForJson.mainFile = this.mainFile;
        String json = gson.toJson(companyForJson);
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
            CompanyForJson companyForJson = gson.fromJson(json, CompanyForJson.class);
            this.fruitShops = companyForJson.fruitShops;
            this.moneyBalance = companyForJson.moneyBalance;
            this.mainFile = companyForJson.mainFile;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            moneyBalance = 0;
            fruitShops = new ArrayList<>();
        }
    }

    public List<Fruit> getSpoiledFruits(Date date) throws Exception {
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Spoiled.addAll(shop.getSpoiledFruits(date));
        }
        return Spoiled;
    }

    public List<Fruit> getAvailableFruits(Date date) throws Exception {
        List<Fruit> Available = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Available.addAll(shop.getAvailableFruits(date));
        }
        return Available;
    }

    public List<Fruit> getAddedFruits(Date date) throws Exception {
        List<Fruit> Added = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Added.addAll(shop.getAddedFruits(date));
        }
        return Added;
    }

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) throws Exception{
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Spoiled.addAll(shop.getSpoiledFruits(date, type));
        }
        return Spoiled;
    }

    public List<Fruit> getAvailableFruits(Date date, FruitType type) throws Exception {
        List<Fruit> Available = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Available.addAll(shop.getSpoiledFruits(date, type));
        }
        return Available;
    }

    public List<Fruit> getAddedFruits(Date date, FruitType type) throws Exception {
        List<Fruit> Added = new ArrayList<Fruit>();
        for(FruitShop shop : fruitShops) {
            Added.addAll(shop.getAddedFruits(date, type));
        }
        return Added;
    }

}
