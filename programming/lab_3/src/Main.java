import Actions.*;
import Health.GoodHealth;
import Health.LegDisease;
import Humans.Human;
import Confines.Confines;
import Items.PList;
import Items.Readable;
import Places.Hospital;

public class Main {
    public static void main(String[] args) {

        GoodHealth goodHealth = new GoodHealth("Здоров");
        LegDisease legDisease = new LegDisease("Боль в ноге");

        Hospital hospital = new Hospital("Больница");

        Readable list = new PList("список", "какой-то список");

        Human Meduniza = new Human("Медуница", goodHealth, Confines.FREEDOM);
        Human Sineglazka = new Human("Синеглазка", goodHealth, Confines.FREEDOM);
        Human Neboska = new Human("Небоська", goodHealth, Confines.FREEDOM);
        Human Rasteryayka = new Human("Растеряйка", goodHealth, Confines.FREEDOM);
        Human Molchyn = new Human("Молчун", goodHealth, Confines.FREEDOM);
        Human Ponchik = new Human("Пончик", goodHealth, Confines.FREEDOM);
        Human Siropchik = new Human("Сиропчик", goodHealth, Confines.FREEDOM);
        Human Pulka = new Human("Пулька", legDisease, Confines.CONFINED);
        Human Vorchyn = new Human("Ворчун", goodHealth, Confines.CONFINED);
        Human Pilulkin = new Human("Пилюлькин", goodHealth, Confines.CONFINED);
        Human[] allHumans = {Meduniza, Sineglazka, Neboska, Rasteryayka, Molchyn, Ponchik, Siropchik, Pulka, Vorchyn, Pilulkin};
        System.out.println();

        System.out.print(Meduniza.getName() + " снова стала ");
        Action readList = new Read("просматривать список", list);
        Meduniza.applyAction(readList);
        System.out.println();

        Human[] luckyHumans = {Neboska, Rasteryayka, Molchyn, Ponchik, Siropchik};
        System.out.println(Sineglazka.getName() + " добилась от больницы чтобы: ");
        Action impact = new Impact("добиваться от больницы", hospital, luckyHumans);
        Sineglazka.applyAction(impact);
        System.out.println();

        System.out.print("В больнице остались: ");
        for (Human human: allHumans){
            if (human.getLocation() == Confines.CONFINED){
                System.out.print(human.getName() + " ");
            }
        }
        System.out.println("\n");

        for (Human human: allHumans){
            if (human.getHealth() == legDisease){
                legDisease.setLimits(human);
            }
        }
        Action teatHair = new thinkAnnoyance("готов рвать волосы");
        System.out.print(Vorchyn.getName());
        Vorchyn.applyAction(teatHair);
        System.out.print(Pilulkin.getName());
        Pilulkin.applyAction(teatHair);

        Action thinkEscape = new thinkEscape("сказать о побеге");
        System.out.print(Vorchyn.getName());
        Vorchyn.applyAction(thinkEscape);
        System.out.print(Pilulkin.getName());
        Pilulkin.applyAction(thinkEscape);
    }
}