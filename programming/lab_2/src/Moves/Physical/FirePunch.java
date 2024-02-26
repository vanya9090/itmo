package Moves.Physical;
import ru.ifmo.se.pokemon.*;

public class FirePunch extends PhysicalMove {
    public FirePunch(){
        super(Type.FIRE, 75, 100);
    }

    @Override
    protected String describe(){
        return "is using FirePunch";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(0.1).turns(1).condition(Status.BURN);
        pokemon.addEffect(e);
    }
}
