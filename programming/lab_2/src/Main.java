import Pokemons.Bulbasaur;
import Pokemons.Ivysaur;
import Pokemons.Venusaur;
import Pokemons.Chimchar;
import Pokemons.Monferno;
import Pokemons.Wynaut;

import ru.ifmo.se.pokemon.*;

public class Main{
    public static void main(String[] args) {
        Battle b = new Battle();
        Bulbasaur p1 = new Bulbasaur("Vanya", 1);
        Ivysaur p2 = new Ivysaur("Sergey", 1);
        Venusaur p3 = new Venusaur("Maksim", 1);
        Chimchar p4 = new Chimchar("Angela", 1);
        Monferno p5 = new Monferno("Dmitriy", 1);
        Wynaut p6 = new Wynaut("Katya", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);

        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}