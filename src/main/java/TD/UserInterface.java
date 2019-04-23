package TD;

import GV.Fruit;
import SB.Company;

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
            System.out.println("Вибірка по товару: " + type.name());
            System.out.println("Кількість товару: " + ref.size());
            for(Fruit f : ref) {
                System.out.println(f.toString());
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

    private void Menu() {
        System.out.println("1 - вивести весь список на екран");
        System.out.println("2 - добавити поставку (1 або 2)");
        System.out.println("3 - зберегти дані");
        System.out.println("4 - завантажити дані (скинути поточний список)");
        System.out.println("5 - відібрати товари, які можуть зіпсуватись (по даті)");
        System.out.println("6 - відібрати товари, які можуть зіпсуватись (по даті і типу)");
        System.out.println("7 - відібрати товари, готові для продажу (по даті)");
        System.out.println("8 - відібрати товари, готові для продажу (по даті і типу)");
        System.out.println("9 - відібрати товари (по даті поставки)");
        System.out.println("10 - відібрати товари(по даті поставки і типу)");
        System.out.println("11 - здійснити реалізацію товару");
        System.out.println("12 - переглянути поточний баланс магазину");
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
                        FruitType tempType = getType();
                        showList(shop.getSpoiledFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                    case 7: {
                        showList(shop.getAvailableFruits(getCustomDate()));
                        break;
                    }
                    case 8: {
                        FruitType tempType = getType();
                        showList(shop.getAvailableFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                    case 9: {
                        showList(shop.getAddedFruits(getCustomDate()));
                        break;
                    }
                    case 10: {
                        FruitType tempType = getType();
                        showList(shop.getAddedFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                    case 11: {
                        shop.sell("Files\\clients_1.txt");
                        break;
                    }
                    case 12: {
                        System.out.println("Поточний баланс магазину - " + shop.getMoneyBalance());
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    private void MenuCompany() {
        System.out.println("1 - зберегти дані");
        System.out.println("2 - завантажити дані (скинути поточний список)");
        System.out.println("3 - обрати магазин");
        System.out.println("4 - загальний баланс всіх магазинів");
        System.out.println("5 - відібрати товари, які можуть зіпсуватись (по даті)");
        System.out.println("6 - відібрати товари, які можуть зіпсуватись (по даті і типу)");
        System.out.println("7 - відібрати товари, готові для продажу (по даті)");
        System.out.println("8 - відібрати товари, готові для продажу (по даті і типу)");
        System.out.println("9 - відібрати товари (по даті поставки)");
        System.out.println("10 - відібрати товари(по даті поставки і типу)");
        System.out.println("0 - вихід");
        System.out.print("Ваш вибір ->");
    }

    public void showCompany(Company company) {
        Scanner in = new Scanner(System.in);
        while(true) {
            MenuCompany();
            try {
                int selected = in.nextInt();
                switch (selected) {
                    case 0: {
                        return;
                    }
                    case 1: {
                        company.save(company.getMainFile());
                        break;
                    }
                    case 2: {
                        company.load(company.getMainFile());
                        break;
                    }
                    case 3: {
                        if(company.fruitShops.isEmpty()) {
                            System.out.println("Список магазинів порожній");
                            break;
                        }
                        System.out.println("оберіть магазин (від 0 до " + (company.fruitShops.size()-1) + ")");
                        System.out.print("->");
                        int shopIndex = in.nextInt();
                        FruitShop temp = company.fruitShops.get(shopIndex);
                        showShop(temp);
                        break;
                    }
                    case 4: {
                        System.out.println("Загальний баланс компанії: " + company.getMoneyBalance());
                        break;
                    }
                    case 5: {
                        showList(company.getSpoiledFruits(getCustomDate()));
                        break;
                    }
                    case 6: {
                        FruitType tempType = getType();
                        showList(company.getSpoiledFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                    case 7: {
                        showList(company.getAvailableFruits(getCustomDate()));
                        break;
                    }
                    case 8: {
                        FruitType tempType = getType();
                        showList(company.getAvailableFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                    case 9: {
                        showList(company.getAddedFruits(getCustomDate()));
                        break;
                    }
                    case 10: {
                        FruitType tempType = getType();
                        showList(company.getAddedFruits(getCustomDate(), tempType), tempType);
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
