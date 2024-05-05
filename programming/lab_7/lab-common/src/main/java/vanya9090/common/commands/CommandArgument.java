package vanya9090.common.commands;

import vanya9090.common.models.HumanBeing;

import java.io.Serializable;

public class CommandArgument implements Serializable {
    private String[] stringArg;
    private HumanBeing humanBeing;
    private final String name;
    public final Class<?> type;
    public CommandArgument(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }
    public CommandArgument withStringArg(String[] stringArg) {
        this.stringArg = stringArg;
        return this;
    }
    public CommandArgument withModelArg(HumanBeing humanBeing) { // todo: add interface or abstract Model
        this.humanBeing = humanBeing;
        return this;
    }

    public String getName() {
        return this.name;
    }
    public Class<?> getType(){
        return this.type;
    }
    public String[] getStringArg() {
        return this.stringArg;
    }
    public HumanBeing getModelArg() {
        return this.humanBeing;
    }
}
