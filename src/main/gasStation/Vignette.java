package main.gasStation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Vignette {

    public enum Type {
        day(1, Period.ofDays(1)), month(10, Period.ofMonths(1)), year(60, Period.ofYears(1));

        private int priceMultiplier;
        private Period period;

        private Type(int priceMultiplier, Period period) {
            this.priceMultiplier = priceMultiplier;
            this.period = period;
        }

        public int getPriceMultiplier() {
            return priceMultiplier;
        }
    }

    //characteristics
    private final String color;
    private final Type type;
    private LocalDate dateIssued;
    private final double price;
    private final Period period;

    public Vignette() {
        type = Type.values()[new Random().nextInt(Type.values().length)];
        VehicleTypes vehicle = VehicleTypes.values()[new Random().nextInt(VehicleTypes.values().length)];
        color = vehicle.getColorVignette();
        price = vehicle.getPriceWeekVi() * type.priceMultiplier;
        period = type.period;
    }

    public boolean hasExpired(LocalDate date){
        if(dateIssued == null){
            return false;
        }
        return dateIssued.plus(period).isBefore(date);
    }

    public double getPrice() {
        return price;
    }

    public void getPurchased(){
        dateIssued = LocalDate.now();
    }

    public String getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format(color + " %.2f lv", price);
    }
}
