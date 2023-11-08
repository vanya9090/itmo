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


        Readable List = new List("Список", "Какой-то текст");
        System.out.println(Meduniza.read(List));

        System.out.print("Таким образом, " + Sineglazka.getName() + " добилась, чтобы, кроме ");
        Sineglazka.impact(hospital, Neboska);
        Sineglazka.impact(hospital, Rasteryayka);
        System.out.print(Neboska.getName() + " и " + Rasteryayka.getName() + " ");
        System.out.print(Sineglazka.impact(hospital, Molchyn) + " ");
        System.out.print(Sineglazka.impact(hospital, Ponchik) + " ");
        System.out.println(Sineglazka.impact(hospital, Siropchik) + " ");

        System.out.print("В больнице остались ");
        for (Human human: humanArr){
            if (Objects.equals(human.getLocation().getName(), "Больница")) {
                System.out.print(human.getName() + " ");
            }
        }

        System.out.println();
        System.out.print(Pulka.endure() + ", так как ");
        System.out.print(Pulka.getDisease().getName() + " у него все еще болела, но ");
        for (Human human: humanArr){
            if (human instanceof Angry) {
                System.out.print(human.getName() + " ");
            }
        }
        System.out.print("готовы были " + Vorchyn.tearingHair());
        System.out.print(" и сказали, что " + Vorchyn.say());
    }
}