package Model;

public class ParkingRecord {
    private final String licensePlate;
    private final String nationality;
    private final String color;
    private final String checkIn;
    private final String checkOut;
    private final String duration;
    private final String status;

    public ParkingRecord(String licensePlate, String nationality, String color,
                         String checkIn, String checkOut, String duration, String status) {
        this.licensePlate = licensePlate;
        this.nationality = nationality;
        this.color = color;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.duration = duration;
        this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getNationality() { return nationality; }
    public String getColor() { return color; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }
    public String getDuration() { return duration; }
    public String getStatus() { return status; }
}