package vanya9090.common.commands;

import vanya9090.common.models.HumanBeing;

import java.io.Serializable;

public class CommandArgument implements Serializable {
    private String stringArg;
    private HumanBeing humanBeing;
    public CommandArgument withStringArg(String stringArg) {
        this.stringArg = stringArg;
        return this;
    }
    public CommandArgument withModelArg(HumanBeing humanBeing) { // todo: add interface or abstract Model
        this.humanBeing = humanBeing;
        return this;
    }

    public String getStringArg() {
        return stringArg;
    }

    public HumanBeing getHumanBeing() {
        return humanBeing;
    }
}
