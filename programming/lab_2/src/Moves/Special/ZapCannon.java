package Moves.Special;

import ru.ifmo.se.pokemon.*;

public class ZapCannon extends SpecialMove{
    public ZapCannon(){
        super(Type.ELECTRIC, 120, 50);
    }
    @Override
    protected String describe(){
        return "is using ZapCannon";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(1).turns(1).condition(Status.PARALYZE);
        pokemon.addEffect(e);
//        pokemon.base.[Sta]
//        pokemon.restore();
    }
}
