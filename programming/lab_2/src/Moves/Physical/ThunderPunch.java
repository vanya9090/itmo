package Moves.Physical;
import ru.ifmo.se.pokemon.*;

public class ThunderPunch extends PhysicalMove {
    public ThunderPunch(){
        super(Type.ELECTRIC, 75, 100);
    }

    @Override
    protected String describe(){
        return "is using ThunderPunch";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(0.1).turns(1).condition(Status.PARALYZE);
        pokemon.addEffect(e);
    }
}
