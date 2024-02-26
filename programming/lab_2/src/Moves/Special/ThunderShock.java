package Moves.Special;

import ru.ifmo.se.pokemon.*;

public class ThunderShock extends SpecialMove{
    public ThunderShock(){
        super(Type.ELECTRIC, 40, 100);
    }
    @Override
    protected String describe(){
        return "is using ThunderShock";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(0.1).turns(1).condition(Status.PARALYZE);
        pokemon.addEffect(e);
    }
}
