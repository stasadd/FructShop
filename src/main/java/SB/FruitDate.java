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
       Calendar c = Calendar.getInstance();

       for (Fruit f:fruitShop.fruits) {
           try {
               Date date1  = f.getDate();
               c.setTime(date1);
               c.add(Calendar.DATE,f.getShelfLife());
               date1 = c.getTime();

               if ((date.compareTo(date1)) == -1)
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
        Calendar c = Calendar.getInstance();

        for (Fruit f:fruitShop.fruits) {
            try {
                Date date1  = f.getDate();
                c.setTime(date1);
                c.add(Calendar.DATE,f.getShelfLife());
                date1 = c.getTime();

                if ((date.compareTo(date1)) != -1)
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

        Calendar c = Calendar.getInstance();

        for (Fruit f:fruitShop.fruits) {
            if (f.getType().equals(type)) {
                try {
                    Date date1  = f.getDate();
                    c.setTime(date1);
                    c.add(Calendar.DATE,f.getShelfLife());
                    date1 = c.getTime();
                    if ((date.compareTo(date1) == -1))
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

        Calendar c = Calendar.getInstance();

        for (Fruit f:fruitShop.fruits) {

            if(f.getType().equals(type)) {
                try {
                    Date date1  = f.getDate();
                    c.setTime(date1);
                    c.add(Calendar.DATE,f.getShelfLife());
                    date1 = c.getTime();

                    if ((date.compareTo(date1)) != -1)
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
        Calendar c = Calendar.getInstance();


        for (Fruit f:fruitShop.fruits) {
            try {
                Date date1  = f.getDate();
                c.setTime(date1);
                c.add(Calendar.DATE,f.getShelfLife());
                date1 = c.getTime();

                if ((date.compareTo(date1)) == 0)
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
        Calendar c = Calendar.getInstance();


        for (Fruit f:fruitShop.fruits) {
            if(f.getType().equals(type)) {
                try {
                    Date date1  = f.getDate();
                    c.setTime(date1);
                    c.add(Calendar.DATE,f.getShelfLife());
                    date1 = c.getTime();


                    if ((date.compareTo(date1)) == 0)
                        Added.add(f);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

        }
        return  Added;
    }
}
