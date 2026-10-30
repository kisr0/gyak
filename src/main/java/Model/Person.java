package Model;
/* ŐSOSZTÁLY
* Azért választottam ezt a megoldást hisz többször is felmerül az kinek mikor hogy van bértlete.
*Ütközben előjött az a feltevés 10 parkolés után legyen egy popup ablak ami megkérdezi akarsz e bérletet venni.
* Viszont ez a counter a dolgozók számára teljesen fölösleges hisz ők ingyeb parkolnak
* Jobbnak láttam kihasználni a java OOP tulajdonságokat és öröklést haszálni(Az osztéy strukturája természetesen
* válozhat kezdeti feltevés) Mark 10.30*/

public class Person {
  private   int id;
  private Vehicle vehicle;

    public Person(int id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;
    }

}
