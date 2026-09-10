public class Car extends Vehicle {

    // Field only cars have
    private int numberOfDoors;

    // Constructor
    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }

    // Override displayInfo()
    @Override
    public void displayInfo() {
        System.out.println("Car: " + brand + " (" + year + ") - "
                + numberOfDoors + " doors");
    }
}