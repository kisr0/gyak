package model;

import java.time.LocalDateTime;

public class NumberPlate {
    int id;
    String licensePlate;
    LocalDateTime arriveTime;
    LocalDateTime exitTime;
    String nationality;
    char plateColor;
    boolean lease;

    public NumberPlate(int id, String licensePlate, LocalDateTime arriveTime, LocalDateTime exitTime, String nationality, char plateColor) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arriveTime = arriveTime;
        this.exitTime = exitTime;
        this.nationality = nationality;
        this.plateColor = plateColor;
        this.lease = false;
    }

    @Override
    public String toString() {
        return "Rendszám:{" +
                "id=" + id +
                ", Rendszám='" + licensePlate + '\'' +
                ", Beérkezési idő=" + arriveTime +
                ", Távozási idő=" + exitTime +
                ", Felségjelzés'" + nationality + '\'' +
                ", Rendszám színe=" + plateColor +
                ", Bérlet=" + lease +
                '}';
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public char getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(char plateColor) {
        this.plateColor = plateColor;
    }

    public boolean isLease() {
        return lease;
    }

    public void setLease(boolean lease) {
        this.lease = lease;
    }
}
