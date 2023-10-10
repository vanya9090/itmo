package Moves.Status;
import ru.ifmo.se.pokemon.*;

public class Growl extends StatusMove {
    public Growl(){
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected String describe(){
        return "is using Growl";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect e = new Effect().chance(1).turns(1).stat(Stat.ATTACK, -1);
        pokemon.addEffect(e);
    }
}
