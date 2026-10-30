package Model;

public class Vehicle {
    private String plate;
    private String type;             // pl. "Személyautó", "Motor", "Teherautó"
    private String nationalInsignia;  // pl. "H", "DE", "RO"
    private char plateColor;

    // Konstruktor: ezzel hozzuk létre az új járművet
    public Vehicle(String plate, String type, String nationalInsignia, char plateColor) {
        this.plate = plate;
        this.type = type;
        this.nationalInsignia = nationalInsignia;
        this.plateColor = plateColor;
    }

    // Getterek és Setterek (az adatok lekéréséhez és módosításához)
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNationalInsignia() {
        return nationalInsignia;
    }

    public void setNationalInsignia(String nationalInsignia) {
        this.nationalInsignia = nationalInsignia;
    }

    public char getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(char plateColor) {
        this.plateColor = plateColor;
    }
}