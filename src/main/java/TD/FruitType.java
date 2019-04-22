package TD;

public enum FruitType {
    Apple,
    Avocado,
    Banana,
    Coconut,
    Grapes,
    Lemon,
    Orange,
    Peaches,
    Pear,
    Pineapple;

    public static FruitType getByIndex(int index) throws Exception {
        return FruitType.values()[index];
    }

    public static void showTypes() {
        for(int i = 0; i < FruitType.values().length; i++) {
            System.out.println(i + " - " + FruitType.values()[i]);
        }
    }
}
