package Model;
// Konstruktor: ezzel hozzuk létre az új járművet
public class NumberPlate {
    private int id;
    private String plate;
    private String type;             // pl. "Személyautó", "Motor", "Teherautó"
    private String nationalInsignia;  // pl. "H", "DE", "RO"
    private char plateColor;
    private boolean guest;

    public NumberPlate(int id, String plate, String type, String nationalInsignia,  char plateColor,boolean guest) {
        this.id = id;
        this.plate = plate;
        this.type = type;
        this.nationalInsignia = nationalInsignia;
        this.guest = guest;
        this.plateColor = plateColor;
    }





    // Getterek és Setterek (az adatok lekéréséhez és módosításához)


    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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