package main.gasStation;

import util.Generate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Vehicle {
    private static String[] makes = {"nissan","suzuki", "honda"};
    private VehicleTypes type;
    private String make;
    private Vignette vignette;
    private int yearProduction;
    private String registrationPlate;

    public Vehicle() {
        type = VehicleTypes.values()[new Random().nextInt(VehicleTypes.values().length)];
        make = makes[new Random().nextInt(makes.length)];
        yearProduction = Generate.number(1990, LocalDate.now().getYear());
        registrationPlate = Generate.registrationPlate();
    }

    public VehicleTypes getType() {
        return type;
    }

    public void setVignette(Vignette vignette) {
        this.vignette = vignette;
    }

    public boolean vignetteHasExpired(LocalDate date) {
        return vignette.hasExpired(date);
    }

    @Override
    public String toString() {
        return make + " " + type.name().toLowerCase() + " " + yearProduction + " " + registrationPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationPlate, vehicle.registrationPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationPlate);
    }

    public boolean hasVignette(){
        return vignette != null;
    }
}
