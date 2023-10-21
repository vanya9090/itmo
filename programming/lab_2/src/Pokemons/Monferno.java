package Pokemons;
import ru.ifmo.se.pokemon.*;


public class Monferno extends Chimchar{
    public Monferno(String name, int level){
        super(name, level);
        super.addType(Type.FIGHTING);
        super.setStats(64, 78, 52, 78, 52, 81);
        super.addMove(new Moves.Status.MeanLook());
    }
}
