package Pokemons;
import ru.ifmo.se.pokemon.*;

public class Bulbasaur extends Pokemon{
    public Bulbasaur(String name, int level){
        super(name, level);
        super.setType(Type.GRASS, Type.POISON);
        super.setStats(45, 49, 49, 65, 65, 45);
        super.addMove(new Moves.Physical.FirePunch());
        super.addMove(new Moves.Physical.ThunderPunch());
    }
}
