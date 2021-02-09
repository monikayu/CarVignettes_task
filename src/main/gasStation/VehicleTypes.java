package main.gasStation;

public enum VehicleTypes { //and the required vignettes
    CAR("red", 5),
    TRUCK("green", 7 ),
    AUTOBUS("blue", 9);

    private String colorVignette;
    private double priceWeekVi;

    VehicleTypes(String colorVignette, double priceWeekVi) {
        this.colorVignette = colorVignette;
        this.priceWeekVi = priceWeekVi;
    }

    public String getColorVignette() {
        return colorVignette;
    }

    public double getPriceWeekVi() {
        return priceWeekVi;
    }
}
