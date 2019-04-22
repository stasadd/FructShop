package SB;

import GV.Fruit;
import TD.FruitShop;

import java.util.*;

public class FruitDate {

    FruitShop fruitShop ;

   public List<Fruit> getSpoiledFruits(Date date)
   {
       List<Fruit> Spoiled = new ArrayList<Fruit>();
       fruitShop = new FruitShop();

       for (Fruit f:fruitShop.fruits) {
           if ((date.compareTo(f.getDate())) == -1)
               Spoiled.add(f);
       }


        return Spoiled;
   }
    public List<Fruit> geetAvailableFruits(Date date)
    {
        List<Fruit> Available = new ArrayList<Fruit>();
        fruitShop = new FruitShop();

        for (Fruit f:fruitShop.fruits) {
            if ((date.compareTo(f.getDate())) != -1)
                Available.add(f);
        }


        return Available;
    }
}
