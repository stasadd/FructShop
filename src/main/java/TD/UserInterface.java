package TD;

import GV.Fruit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private void showList(List<Fruit> ref) {
        if(ref != null) {
            System.out.println("Кількість товару: " + ref.size());
            for(Fruit f : ref) {
                System.out.println(f.toString());
            }
        }
    }

    private void showList(List<Fruit> ref, FruitType type) {
        if(ref != null && type != null) {
            System.out.println("Вибірка по товару: " + type);
            System.out.println("Кількість товару: " + ref.size());
            for(Fruit f : ref) {
                System.out.println(f.toString());
            }
        }
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

    public void showShop(FruitShop shop) {
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
                        showList(shop.fruits);
                        break;
                    }
                    case 2: {
                        System.out.print("(1 або 2) ->");
                        int fileIndex = in.nextInt();
                        shop.addFruits("Files\\delivery_" + fileIndex + ".txt");
                        break;
                    }
                    case 3: {
                        shop.save(shop.getMainFile());
                        break;
                    }
                    case 4: {
                        shop.load(shop.getMainFile());
                        break;
                    }
                    case 5: {
                        showList(shop.getSpoiledFruits(getCustomDate()));
                        break;
                    }
                    case 6: {
                        showList(shop.getSpoiledFruits(getCustomDate(), getType()));
                        break;
                    }
                    case 7: {
                        showList(shop.getAvailableFruits(getCustomDate()));
                        break;
                    }
                    case 8: {
                        showList(shop.getAvailableFruits(getCustomDate(), getType()));
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
