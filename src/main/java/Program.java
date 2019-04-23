import TD.FruitShop;
import TD.UserInterface;

public class Program {
    public static void main(String[] args) {
        FruitShop fruitShop = new FruitShop();
        UserInterface UI = new UserInterface();
        UI.showShop(fruitShop);
    }
}
