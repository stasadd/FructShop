import SB.Company;
import TD.FruitShop;
import TD.UserInterface;

public class Program {
    public static void main(String[] args) {

        //1
//        FruitShop fruitShop1 = new FruitShop();
//        fruitShop1.load(fruitShop1.getMainFile());
//        FruitShop fruitShop2 = new FruitShop();
//        fruitShop2.setMainFile("SHOP_2");
//        fruitShop2.load(fruitShop2.getMainFile());
//        Company company = new Company();
//        company.fruitShops.add(fruitShop1);
//        company.fruitShops.add(fruitShop2);
//        UserInterface UI = new UserInterface();
//        UI.showCompany(company);

        //2
        Company company = new Company();
        company.load(company.getMainFile());
        UserInterface UI = new UserInterface();
        UI.showCompany(company);





    }
}
