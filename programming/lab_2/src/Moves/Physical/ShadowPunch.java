package Moves.Physical;

import ru.ifmo.se.pokemon.*;

public class ShadowPunch extends PhysicalMove {
    public ShadowPunch(){
        super(Type.GHOST, 60, 100);
    }
    @Override
    protected String describe(){
        return "is using ShadowPunch";
    }
//    @Override
//    protected void applySelfEffects(Pokemon p){
//        Effect eff = new Effect().chance(1);
//        p.addEffect(eff);
//        super.applySelfEffects(p);
//    }
//    @Override
//    protected void applyOppEffects(Pokemon pokemon){
//        pokemon.setMod(Stat.EVASION, 0);
//    }
}
