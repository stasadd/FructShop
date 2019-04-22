package SB;

import GV.Fruit;
import TD.FruitShop;
import TD.FruitType;

import java.lang.reflect.Type;
import java.util.*;


public class FruitDate {

    FruitShop fruitShop  = new FruitShop();

   public List<Fruit> getSpoiledFruits(Date date)
   {
       List<Fruit> Spoiled = new ArrayList<Fruit>();


       for (Fruit f:fruitShop.fruits) {
           try {

               if ((date.compareTo(f.getDate())) == -1)
                   Spoiled.add(f);
           }
           catch (Exception e)
           {
               System.out.println(e.toString());
           }
       }


        return Spoiled;
   }
    public List<Fruit> getAvailableFruits(Date date)
    {
        List<Fruit> Available = new ArrayList<Fruit>();


        for (Fruit f:fruitShop.fruits) {
            try {

                if ((date.compareTo(f.getDate())) != -1)
                    Available.add(f);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }


        return Available;
    }
    public List<Fruit> getSpoiledFruits(Date date, FruitType type)
    {
        List<Fruit> Spoiled = new ArrayList<Fruit>();




        for (Fruit f:fruitShop.fruits) {
            if (f.getType().equals(type)) {
                try {
                    if ((date.compareTo(f.getDate())) == -1)
                        Spoiled.add(f);

                }catch (Exception e)
                {
                    System.out.println(e.toString());
                }
                }
        }


        return Spoiled;
    }
    public List<Fruit> getAvailableFruits(Date date, FruitType type)
    {
        List<Fruit> Available = new ArrayList<Fruit>();



        for (Fruit f:fruitShop.fruits) {

            if(f.getType().equals(type)) {
                try {

                    if ((date.compareTo(f.getDate())) != -1)
                        Available.add(f);
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                }
            }
        }


        return Available;
    }
    public  List<Fruit> getAddedFruits(Date date)
    {
        List<Fruit> Added = new ArrayList<Fruit>();



        for (Fruit f:fruitShop.fruits) {
            try {

                if ((date.compareTo(f.getDate())) == 0)
                    Added.add(f);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }

        }
        return  Added;
    }

    public  List<Fruit> getAddedFruits(Date date,FruitType type)
    {
        List<Fruit> Added = new ArrayList<Fruit>();



        for (Fruit f:fruitShop.fruits) {
            if(f.getType().equals(type)) {
                try {

                    if ((date.compareTo(f.getDate())) == 0)
                        Added.add(f);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

        }
        return  Added;
    }
}
