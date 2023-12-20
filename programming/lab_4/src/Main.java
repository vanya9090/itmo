import Actions.*;
import Exceptions.BrokenLegException;
import Exceptions.BrokenMotorException;
import Health.GoodHealth;
import Health.LegDisease;
import Humans.Human;
import Humans.Confines;
import Humans.Roles;
import Items.Car;
import Items.PList;
import Items.Readable;
import Places.Hospital;
import Places.Place;
import Time.Time;

import java.util.Arrays;
import java.util.TimeZone;

import static Time.Times.MORNING;

public class Main {
    public static void main(String[] args) throws BrokenMotorException {
        Time time = new Time("GMT+3");
        Time.TimeUtils timeUtils = new Time.TimeUtils();

        timeUtils.setTime(MORNING.getTime());

        GoodHealth goodHealth = new GoodHealth("Здоров");
        LegDisease legDisease = new LegDisease("Боль в ноге");

        Hospital hospital = new Hospital("Больница");
        Hospital.Administration administration = hospital.new Administration("администрация больницы");

        Readable list = new PList("список", "больничный список");

        Human Meduniza = new Human("Медуница", goodHealth, Confines.FREEDOM, Roles.DOCTOR);
        Human Sineglazka = new Human("Синеглазка", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Neboska = new Human("Небоська", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Rasteryayka = new Human("Растеряйка", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Molchyn = new Human("Молчун", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Ponchik = new Human("Пончик", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Siropchik = new Human("Сиропчик", goodHealth, Confines.FREEDOM, Roles.DEFAULT);
        Human Pulka = new Human("Пулька", legDisease, Confines.CONFINED, Roles.DEFAULT);
        Human Vorchyn = new Human("Ворчун", goodHealth, Confines.CONFINED, Roles.DEFAULT);
        Human Pilulkin = new Human("Пилюлькин", goodHealth, Confines.CONFINED, Roles.DEFAULT);
        Human[] allHumans = {Meduniza, Sineglazka, Neboska, Rasteryayka, Molchyn, Ponchik, Siropchik, Pulka, Vorchyn, Pilulkin};
        System.out.println();

        Action readList = new Read("читать список", list);
        Meduniza.applyAction(readList);
        System.out.println();

        SayTo sayTo = new SayTo("сказать, чтобы выписали","Так выпишите их обоих, чтоб не надоедали вам", Meduniza);
        Sineglazka.applyAction(sayTo);
        System.out.println();

        Human[] luckyHumans = {Neboska, Rasteryayka, Molchyn, Ponchik, Siropchik};
        Action impact = new Impact("добиваться от больницы выписки", administration, luckyHumans);
        Sineglazka.applyAction(impact);
        System.out.println();

        System.out.print("В больнице остались: ");
        for (Human human: allHumans){
            if (human.getLocation() == Confines.CONFINED){
                System.out.print(human.getName() + " ");
            }
        }
        System.out.println("\n");

        try {
            Pulka.setLocation(Confines.FREEDOM);
        }
        catch (BrokenLegException exception){
            legDisease.setLimits(Pulka);
        }

        Action teatHair = new Think("рвать волосы", "готов рвать на себе волосы от досады");
        Vorchyn.applyAction(teatHair);
        Pilulkin.applyAction(teatHair);

        Action thinkEscape = new Think("сказать о побеге", "сказал, что если к вечеру его не выпишут, то он устроит побег");
        Vorchyn.applyAction(thinkEscape);
        Pilulkin.applyAction(thinkEscape);
        System.out.println();

        timeUtils.setTime(MORNING.getTime());

        Human Vintik = new Human("Винтик", goodHealth, Confines.CONFINED, Roles.DEFAULT);
        Human Shpuntik = new Human("Шпунтик", goodHealth, Confines.CONFINED, Roles.DEFAULT);
        Human Bublik = new Human("Бублик", goodHealth, Confines.CONFINED, Roles.DEFAULT);
        Human[] newHumans = {Vintik, Shpuntik, Bublik};
        Car car = new Car("машина", true);

        Action wakeUp = new WakeUp("проснуться", time);
        Arrays.asList(newHumans).forEach(human -> human.applyAction(wakeUp));
        System.out.println();

        Repair repair = new Repair("приняться чинить автомобиль", car);
        try {
            car.run();
        }catch (BrokenMotorException exception){
            Arrays.asList(newHumans).forEach(human -> human.applyAction(repair));
        }

        car.run();
        System.out.println();

        System.out.println("Трое друзей решили устроить пробную поездку");
        Place home = new Place("Дом");
        Place street = new Place("Улица");
        car.drive(home);
        car.drive(street);
        System.out.println();

        Human[] babies = {new Human("Малыш 1", goodHealth, Confines.FREEDOM, Roles.BABY),
                new Human("Малыш 2", goodHealth, Confines.FREEDOM, Roles.BABY)};
        Action fruitPicking = new Picking("собирать фрукты");
        Arrays.asList(babies).forEach(babie -> babie.applyAction(fruitPicking));

        Arrays.asList(newHumans).forEach(human -> human.applyAction(new Action("увидеть малышек", false) {
            @Override
            public void run(Human human) {
                System.out.println(human.getName() + " увидел малышек, которые занимались уборкой фруктов.");
                this.setDone(true);
            }
        }));
    }
}