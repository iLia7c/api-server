package olx;

public class Car extends CarModel {
    // bigger than 1850
    private Year manufacturingYear;

    // TODO add digits and letters and - symbol verification
    private String serialNumber;

    protected Car(Brand b, ModelName modelName) {
        super(b, modelName);
    }

    public static Car getInstance(Brand b, ModelName modelName) {
        Car car = new Car(b, modelName);
        car.manufacturingYear = Year.getDefaultYear();
        car.serialNumber = "11A-B";

        return car;
    }



}
