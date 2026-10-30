package Model;

public class Guest extends Person {
    private int parkCount;
    private boolean hasPass;

    // A konstruktor bekéri az ős adatait (id, vehicle) ÉS a saját adatait is
    public Guest(int id, Vehicle vehicle, int parkCount, boolean hasPass) {
        super(id, vehicle); // Átadja az id-t és a vehicle-t a Person ősosztálynak
        this.parkCount = parkCount;
        this.hasPass = hasPass;
    }

    // Getterek és Setterek a saját mezőkhöz
    public int getParkCount() {
        return parkCount;
    }

    public void setParkCount(int parkCount) {
        this.parkCount = parkCount;
    }

    public boolean isHasPass() {
        return hasPass;
    }

    public void setHasPass(boolean hasPass) {
        this.hasPass = hasPass;
    }
}