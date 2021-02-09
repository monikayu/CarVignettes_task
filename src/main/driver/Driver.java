package main.driver;

import main.gasStation.GasStation;
import main.gasStation.Vehicle;
import main.gasStation.Vignette;
import util.Generate;
import util.Validate;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;

public class Driver {
    private static final int MIN_WALLET_AMOUNT = 20;
    private static final int MAX_WALLET_AMOUNT = 2000;
    private String name;
    private HashSet<Vehicle> vehicles;
    private double wallet;
    private GasStation station;

    public Driver(String name, HashSet<Vehicle> vehicles, double wallet, GasStation station) throws Exception {
        if(Validate.name(name)) {
            this.name = name;
        }
        else{
            this.name = Generate.name();
        }
        if(vehicles == null){
            this.vehicles = new HashSet<>();
        }
        else {
            this.vehicles = vehicles;
        }
        if(wallet > 0) {
            this.wallet = wallet;
        }
        else{
            this.wallet = Generate.number(MIN_WALLET_AMOUNT, MAX_WALLET_AMOUNT);
        }
        if(station == null){
            throw new Exception("Invalid input");
        }
        this.station = station;
    }

    public Driver(GasStation gasStation) throws Exception {
        this("0", null, -3, gasStation);
    }

    public void buyOneVignette(Vehicle v, Vignette.Type type){
        String color = v.getType().getColorVignette();
        if(wallet >= v.getType().getPriceWeekVi() * type.getPriceMultiplier()){
            Vignette vign = station.getVignette(color, type);
            if(vign != null){
                station.sell(vign, v);
                wallet -= vign.getPrice();
                v.setVignette(vign);
            }
        }
       // System.out.println("Driver does not have enough money.");
    }

    public void buyManyVignettes(int count){
        if(count <= 0){
            System.out.println("Invalid input");
            return;
        }
        if(vehicles.size() < count){
            System.out.println("Driver does not have that many vehicles.");
            return;
        }
        int counter = count;
        for(Vehicle v : vehicles){
            if(counter == 0){
                break;
            }
            buyOneVignette(v, Vignette.Type.values()[new Random().nextInt(Vignette.Type.values().length)]);
            counter--;
        }
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }
    public int getAmountOfVehicles(){
        return vehicles.size();
    }

    @Override
    public String toString() {
        return name;
    }

    public void getInfo(LocalDate date){
        int counter = 0;
        for(Vehicle v : vehicles){
            if(v.hasVignette() && v.vignetteHasExpired(date)){
                counter++;
            }
        }
        System.out.println(this + " :" + vehicles.size() + " vehicles; " + wallet + " lv ; " + counter + " vehicles' vignettes will be expired.");//FIXME
    }
}
