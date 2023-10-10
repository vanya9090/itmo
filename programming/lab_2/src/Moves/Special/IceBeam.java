package Moves.Special;

import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove{
    public IceBeam(){
        super(Type.ICE, 90, 100);
    }
    @Override
    protected String describe(){
        return "is using IceBeam";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(0.1).turns(1).condition(Status.FREEZE);
        pokemon.addEffect(e);
    }
}
