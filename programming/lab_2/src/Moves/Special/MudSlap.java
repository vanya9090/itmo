package Moves.Special;

import ru.ifmo.se.pokemon.*;

public class MudSlap extends SpecialMove{
    public MudSlap(){
        super(Type.GROUND, 20, 100);
    }
    @Override
    protected String describe(){
        return "is using MudSlap";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(1).turns(1).stat(Stat.ACCURACY, -1);
        pokemon.addEffect(e);
    }
}
