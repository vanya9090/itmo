package Moves.Status;
import ru.ifmo.se.pokemon.*;

public class MeanLook extends StatusMove {
    public MeanLook(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected String describe(){
        return "is using MeanLook";
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.EVASION, 2);
    }
}
