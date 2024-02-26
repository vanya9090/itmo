package Pokemons;
import ru.ifmo.se.pokemon.*;

public class Venusaur extends Ivysaur{
    public Venusaur(String name, int level){
        super(name, level);
        super.setStats(80, 82, 83, 100, 100, 80);
        super.addMove(new Moves.Special.HydroPump());
    }
}
