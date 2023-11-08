import Humans.Adult;
import Humans.*;
import Items.List;
import Items.Readable;
import Places.*;

import java.util.Objects;


public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("Больница");
        Freedom freedom = new Freedom();

        Meduniza Meduniza = new Meduniza();
        Sineglazka Sineglazka = new Sineglazka();
        Neboska Neboska = new Neboska();
        Rasteryayka Rasteryayka = new Rasteryayka();
        Molchyn Molchyn = new Molchyn();
        Ponchik Ponchik = new Ponchik();
        Siropchik Siropchik = new Siropchik();
        Pulka Pulka = new Pulka();
        Vorchyn Vorchyn = new Vorchyn();
        Pilulkin Pilulkin = new Pilulkin();
        Human[] humanArr = {Meduniza, Sineglazka, Neboska, Rasteryayka, Molchyn, Ponchik, Siropchik, Pulka, Vorchyn, Pilulkin};
        System.out.println();

        Readable List = new List("Список", "Какой-то текст");
        Meduniza.read(List);
        System.out.println();

        Sineglazka.impact(hospital, Neboska);
        Sineglazka.impact(hospital, Rasteryayka);
        Sineglazka.impact(hospital, Molchyn);
        Sineglazka.impact(hospital, Ponchik);
        Sineglazka.impact(hospital, Siropchik);
        System.out.println();

        System.out.print("В " + hospital.getName() + " остались ");
        for (Human human: humanArr){
            if (Objects.equals(human.getLocation().getName(), hospital.getName())) {
                System.out.print(human.getName() + " ");
            }
        }
        System.out.println();
        System.out.println();

        Pulka.endure();
        System.out.println("так как " + Pulka.getDisease().getName());
        System.out.println();

        for (Human human: humanArr){
            if (human instanceof Angry) {
                ((Angry) human).tearingHair();
                ((Adult) human).say();
            }
        }
    }
}