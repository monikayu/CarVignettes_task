package main.gasStation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Stack;
import java.util.TreeMap;

public class GasStation {
    private TreeMap<String, TreeMap<Vignette.Type, Stack<Vignette>>> vignettes;
    private double earnings;
    private HashSet<Vehicle> trucksRecorded;

    public GasStation() {
        vignettes = new TreeMap<>();
        for (int i = 0; i < 10000; i++) {
            addRandomVignette();
        }
        printVignettes();
    }

    public Vignette getVignette(String color, Vignette.Type type) {
        if(!vignettes.containsKey(color) || !vignettes.get(color).containsKey(type)){
            return null;
        }
        return vignettes.get(color).get(type).peek();
    }

    private Vignette addRandomVignette(){
        Vignette v = new Vignette();
        String c = v.getColor();
        Vignette.Type t = v.getType();
        if(!vignettes.containsKey(c)){ //sorted by price
            vignettes.put(c, new TreeMap<>((c1, c2) -> Integer.compare(c1.getPriceMultiplier(), c2.getPriceMultiplier())));
        }
        if(!vignettes.get(c).containsKey(t)){
            vignettes.get(c).put(t, new Stack<>());
        }
        return vignettes.get(c).get(t).push(v);
    }

    public void sell(Vignette vign, Vehicle vehicle){
        Vignette v = vignettes.get(vign.getColor()).get(vign.getType()).pop();
        v.getPurchased();
        earnings += v.getPrice();
        if(vehicle.getType() == VehicleTypes.TRUCK){
            if(trucksRecorded == null){
                trucksRecorded = new HashSet<Vehicle>();
            }
            trucksRecorded.add(vehicle);
        }
    }

    public void printVignettes(){
        System.out.println("--Vignettes--");
        vignettes.entrySet().forEach(entry -> { System.out.println(entry.getKey());
        entry.getValue().entrySet().forEach(e -> System.out.println("    for a " + e.getKey() + " " + e.getValue().size() + " items"));
        });
    }

    public void printTrucksWithExpiredVignettes(LocalDate date){
        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanStandardFormat = DateTimeFormatter.ofPattern(europeanDatePattern);
        System.out.println("--Vignettes of these trucks will be expired on " + date.format(europeanStandardFormat) + "--");
        trucksRecorded.forEach(t -> {
            if(t.hasVignette() && t.vignetteHasExpired(date)){
                System.out.println(t);
            }
        });
    }
    public void printEarnings(){
        System.out.println("Profit : " + earnings + " lv");
    }
}
