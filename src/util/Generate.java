package util;

import java.util.Random;

public class Generate {

    public static String genFemaleName(){
        String[] names = {"Penka", "Katrina", "Jana", "Sisi", "Ghana", "Amazonia", "Monika", "Reneta", "Krisi"};
        return names[new Random().nextInt(names.length)];
    }
    public static String genMaleName(){
        String[] names = {"Penko", "Kosta", "Juan", "Svetlin", "George", "Atanas", "Krasi", "Konsta", "Pierre"};
        return names[new Random().nextInt(names.length)];
    }

    public static String name(){
        if(new Random().nextBoolean()){
            return genFemaleName();
        }
        else
            return genMaleName();
    }

    public static String registrationPlate(){
        String plate = "";
        for (int i = 0; i < 2; i++) {
            plate += (char) Generate.number(65, 90);
        }
        for (int i = 0; i < 4; i++) {
            plate += new Random().nextInt(10);
        }
        for (int i = 0; i < 2; i++) {
            plate += (char) Generate.number(65, 90);
        }
        return plate;
    }

    public static int number(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }

}
