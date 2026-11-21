package Model;

public class Company {

    private int id;
    private String name;
    private int parkingspaceNum;
    private int disabilityPspaceNum;
    private double pricePHour;
    private int free;

    public Company(
            int id,
            String name,
            int parkingspaceNum,
            int disabilityPspaceNum,
            double pricePHour,
            int free
    ) {
        this.id = id;
        this.name = name;
        this.parkingspaceNum = parkingspaceNum;
        this.disabilityPspaceNum = disabilityPspaceNum;
        this.pricePHour = pricePHour;
        this.free = free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParkingspaceNum() {
        return parkingspaceNum;
    }

    public void setParkingspaceNum(int parkingspaceNum) {
        this.parkingspaceNum = parkingspaceNum;
    }

    public int getDisabilityPspaceNum() {
        return disabilityPspaceNum;
    }

    public void setDisabilityPspaceNum(int disabilityPspaceNum) {
        this.disabilityPspaceNum = disabilityPspaceNum;
    }

    public double getPricePHour() {
        return pricePHour;
    }

    public void setPricePHour(double pricePHour) {
        this.pricePHour = pricePHour;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return name;
    }
}
