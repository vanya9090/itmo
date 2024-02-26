package Pokemons;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Chimchar extends Pokemon{
    public Chimchar(String name, int level){
        super(name, level);
        super.setType(Type.FIRE);
        super.setStats(44, 58, 44, 58, 44, 61);
        super.addMove(new Moves.Special.MudSlap());
        super.addMove(new Moves.Physical.ShadowPunch());
        super.addMove(new Moves.Special.HydroPump());
    }
}
