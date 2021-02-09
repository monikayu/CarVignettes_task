package util;

public class Validate {

    public static boolean name(String name){
        String s = name.toLowerCase().trim();
        for (int i = 0; i < name.length(); i++) {
            if((s.charAt(i) > 'z' || s.charAt(i) < 'a') && s.charAt(i) != ' '){
                return false;
            }
        }
        return true;
    }
}
