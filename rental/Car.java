package rental;

public class Car {
    private String brand;
    private String licensePlate;
    private double price;
    private final static double MAX_PRICE = 500.0 ;
    private final static Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE); 
    
    private Car(String brand, String licensePlate, double price){
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }
    
    public boolean isCheaperThan(Car other) throws IllegalArgumentException {
        if(other == null) throw new IllegalArgumentException("Invaid car");
        return other.price > this.price;
    }
    
    public void decreasePrice() {
        if(price >= 10 && price < MAX_PRICE) { price -= 10; }
    }
    
    public static Car make(String brand, String licensePlate, double price) {
        if(brand.length() < 2){
            return null;
        }
        for(char c : brand.toCharArray()){
            if(c != ' ' && !Character.isLetter(c)){
                return null;
            }
        }
        if (!isValidLicensePlate(licensePlate)) { return null; }

        if (price <= 0 || price > MAX_PRICE) { return null; }

        return new Car(brand, licensePlate, price);   
    }
        
    private static boolean isValidLicensePlate(String licensePlate) {
        if(licensePlate == null || licensePlate.length() != 7) {
            return false;
        }

        String[] plate = licensePlate.trim().replaceAll(" +", " ").split(" ");
        
        String letters = plate[0];
        String digits = plate[1];

        if(letters.length() != 3 || digits.length() != 3) {
            return false;
        }

        boolean res = true;
        
        for(char c : letters.toCharArray()){
            if(!Character.isUpperCase(c)) {
                res = false;
            }
        }
        for(char c : digits.toCharArray()){
            if(!Character.isDigit(c)) {
                res = false;
            }
        }
        return res;
    }
    @Override      
    public String toString(){
        return String.format("%s (%s) %5.1f EUR",this.brand, this.licensePlate, this.price);
    }
    
    public double getPrice(){
        return price;
    }

}