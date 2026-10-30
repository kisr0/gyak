package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Parking_ticket {
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;


    public Parking_ticket(Vehicle vehicle, LocalDateTime entryTime) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.exitTime = null;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }


    public long getParkingDurationInMinutes() {
        if (exitTime == null) {
            // Ha még bent áll, a jelenlegi időhöz képest számolunk
            return Duration.between(entryTime, LocalDateTime.now()).toMinutes();
        }
        return Duration.between(entryTime, exitTime).toMinutes();
    }


    public double  calculateCost(long eltoltottIdo,int oraDij) {
        if(eltoltottIdo<60)
        {
            return 0.0;
        }
        int kulonben= (int) (eltoltottIdo-60); //Itt kiszámoljuk azt,hogy mennyi a plusz amit az ingyenes 1 orán kívül eltöltöttünk
        int fizetosOra = (int) Math.ceil((double) kulonben / 60.0); //Fogjuk megnézzuk mennyit orát töltöttunk felkerekírjük átalakitjuk inte
        return fizetosOra*oraDij;
    }
}