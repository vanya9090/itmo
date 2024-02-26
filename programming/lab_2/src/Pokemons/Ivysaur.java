package Pokemons;
import ru.ifmo.se.pokemon.*;

public class Ivysaur extends Bulbasaur {
    public Ivysaur(String name, int level){
        super(name, level);
        super.setStats(60, 62, 63, 80, 80   , 60);
        super.addMove(new Moves.Special.ZapCannon());
    }
}
