package TD;

import GV.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class FruitShop {

    public class Delivery {
        Fruit[] fruits;
    }

    public List<Fruit> fruits = new ArrayList<Fruit>();
    private String mainFile = "Files\\SHOP.txt";
    private Gson gson = new Gson();
    private Double moneyBalance;

    public Double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(Double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public FruitShop() {
        load(mainFile);
        addFruits("Files\\Delivery.txt");
    }

    private void addFruits(String pathToJsonFile) {
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

    private void save(String pathToJsonFile) {
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

    private void load(String pathToJsonFile) {
        try {
            String json = FileLoader.loadString(pathToJsonFile);
            Type collectionType = new TypeToken<List<Fruit>>(){}.getType();
            fruits = gson.fromJson(json, collectionType);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showList(List<Fruit> ref) {
        if(ref != null) {
            System.out.println("Кількість товару: " + ref.size());
            for(Fruit f : ref) {
                System.out.println(f.getType() + ": "  + f.getPrice() + " - " + f.getStrDate());
            }
        }
    }

    private void showList(List<Fruit> ref, FruitType type) {
        if(ref != null && type != null) {
            System.out.println("Вибірка по товару: " + type);
            System.out.println("Кількість товару: " + ref.size());
            for(Fruit f : ref) {
                System.out.println(f.getType() + ": "  + f.getPrice() + " - " + f.getStrDate());
            }
        }
    }

    public List<Fruit> getSpoiledFruits(Date date) throws Exception {
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            Date date1  = f.getDate();
            c.setTime(date1);
            c.add(Calendar.DATE,f.getShelfLife());
            date1 = c.getTime();
            if ((date.compareTo(date1)) != -1)
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

    public List<Fruit> getSpoiledFruits(Date date, FruitType type) throws Exception{
        List<Fruit> Spoiled = new ArrayList<Fruit>();
        Calendar c = Calendar.getInstance();
        for (Fruit f : this.fruits) {
            if (f.getType().equals(type)) {
                Date date1  = f.getDate();
                c.setTime(date1);
                c.add(Calendar.DATE,f.getShelfLife());
                date1 = c.getTime();
                if ((date.compareTo(date1) == -1))
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
                if ((date.compareTo(date1)) != -1)
                    Available.add(f);
            }
        }
        return Available;
    }

    private void Menu() {
        System.out.println("1 - вивести весь список на екран");
        System.out.println("2 - добавити поставку (1 або 2)");
        System.out.println("3 - зберегти дані");
        System.out.println("4 - завантажити дані (скинути поточний список)");
        System.out.println("5 - відібрати товари, які можуть зіпсуватись (по даті)");
        System.out.println("6 - відібрати товари, які можуть зіпсуватись (по даті і типу)");
        System.out.println("7 - відібрати товари, готові для продажу (по даті)");
        System.out.println("8 - відібрати товари, готові для продажу (по даті і типу)");

        System.out.println("0 - вихід");
        System.out.print("Ваш вибір ->");
    }

    public void showShop() {
        Scanner in = new Scanner(System.in);
        while(true) {
            Menu();
            try {
                int selected = in.nextInt();
                switch (selected) {
                    case 0: {
                        return;
                    }
                    case 1: {
                        showList(this.fruits);
                        break;
                    }
                    case 2: {
                        System.out.print("(1 або 2) ->");
                        int fileIndex = in.nextInt();
                        addFruits("Files\\delivery_" + fileIndex + ".txt");
                        break;
                    }
                    case 3: {
                        save(mainFile);
                        break;
                    }
                    case 4: {
                        load(mainFile);
                        break;
                    }
                    case 5: {
                        showList(getSpoiledFruits(getCustomDate()));
                        break;
                    }
                    case 6: {
                        showList(getSpoiledFruits(getCustomDate(), getType()));
                        break;
                    }
                    case 7: {
                        showList(getAvailableFruits(getCustomDate()));
                        break;
                    }
                    case 8: {
                        showList(getAvailableFruits(getCustomDate(), getType()));
                        break;
                    }
                }
            } catch (Exception ex) {}
        }
    }

    private FruitType getType() throws Exception {
        Scanner inType = new Scanner(System.in);
        FruitType.showTypes();
        System.out.print("вибрати тип ->");
        int i = inType.nextInt();
        return FruitType.getByIndex(i);
    }

    private Date getCustomDate() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner inDate = new Scanner(System.in);
        System.out.print("Введіть дату у форматі dd/MM/yyyy ->");
        String str = inDate.nextLine();
        return format.parse(str);
    }
}