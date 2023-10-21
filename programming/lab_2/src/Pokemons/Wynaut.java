package Pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Wynaut extends Pokemon{
    public Wynaut(String name, int level){
        super(name, level);
        super.setType(Type.PSYCHIC);
        super.setStats(95, 23, 48, 23, 48, 23);
        super.addMove(new Moves.Special.IceBeam());
        super.addMove(new Moves.Special.ThunderShock());
        super.addMove(new Moves.Special.MudSlap());
        super.addMove(new Moves.Status.Growl());
    }
}
