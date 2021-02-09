import main.driver.Driver;
import main.gasStation.Vehicle;
import main.gasStation.GasStation;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        GasStation station = new GasStation();
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        for (int i = 0; i < 200; i++) {
            vehicles.add(new Vehicle());
        }
        for (int i = 0; i < 20; i++) {
            try {
                drivers.add(new Driver(station));
            } catch (Exception e) {
                System.out.println("Invalid argument.");;
            }
        }
        for (int i = 0, d = 0; i < vehicles.size(); i++){
            if(i != 0 && i % 10 == 0){
                d++;
            }
            if(d == drivers.size()){
                break;
            }
            drivers.get(d).addVehicle(vehicles.get(i));
        }
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            if(i % 3 == 0){
                driver.buyManyVignettes(5);
            }
            else{
               driver.buyManyVignettes(driver.getAmountOfVehicles());
            }
        }
        station.printTrucksWithExpiredVignettes(LocalDate.now().plus(Period.ofDays(8)));
        station.printVignettes();
        station.printEarnings();

    }
}
