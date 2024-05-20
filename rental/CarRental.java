package rental;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;
import java.util.Collections;
import rental.Car;


public class CarRental {
    private ArrayList<Car> cars;
    
    public CarRental(String filename) {
        cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine()) != null ) {
                //System.out.println(line);
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String brand = parts[0];
                    String[] carInfo = parts[1].split(",");
                    if (carInfo.length == 2){
                        String plate = carInfo[0];
                        try {
                            double price = Double.parseDouble(carInfo[1]);
                            Car car = Car.make(brand, plate, price);
                            if (car != null){
                                cars.add(car);
                            }
                        } catch(NumberFormatException e) {}
                    }
                    
                }
            }
        } catch (IOException | NumberFormatException e) { }
    
    }
    public int numberOfCars() {
        return cars.size();
    }

    public void insertionSort() {
        for(int i = 1; i < cars.size(); i++){
            Car pivot = cars.get(i);
            int j = i - 1;
            while (j >= 0 && cars.get(j).getPrice() > pivot.getPrice()) {
                cars.set(j+1, cars.get(j));
                j--;
            }
            cars.set(j+1, pivot);
        }
    }

    public double weightedAverage(){
        if(cars == null || cars.size() == 0){
            return -1.0;
        } else {
            double sumOfProducts = 0.0;
            int sumOfWeights = 0;
            for (int i = 0; i < cars.size(); i++){
                sumOfProducts += (i+1) * cars.get(i).getPrice();
                sumOfWeights += i+1;
            }
            return sumOfProducts/sumOfWeights;    
        }
    }

    public Car rentCheapest() {
        if(cars.size() == 0) return null;
        insertionSort();

        Car cheapCar = cars.get(0);
        cars.remove(cheapCar);
        return cheapCar;
    }

    public ArrayList<Car> sale() {
        ArrayList<Car> soldCars = new ArrayList<>(cars.size());
        Random random = new Random();
        for(Car car : cars) {
            if(random.nextBoolean()) {
                car.decreasePrice();
            }
            soldCars.add(car);
        }
        return soldCars;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Car car : cars) {
            sb.append(car).append("\n");
        }
        return sb.toString();
    }
}