package vanya9090.common.validators;

import vanya9090.common.handlers.*;

import java.util.HashMap;
import java.util.Map;

public class ValidatorManager {
    private static final Map<String, Validator<?>> validators = new HashMap<>();
    public static void register(String validName, Validator<?> validator){
        validators.put(validName, validator);
    }
    public Map<String, Validator<?>> getValidators() {
        return validators;
    }
    static {
        register("cool", new CoolCarValidator());
        register("creationDate", new CreationDateValidator());
        register("hasToothpick", new HasToothpickValidator());
        register("id", new IdValidator());
        register("impactSpeed", new ImpactSpeedValidator());
        register("minutesOfWaiting", new MinutesOfWaitingValidator());
        register("NameCarValidator", new NameCarValidator());
        register("name", new NameValidator());
        register("realHero", new RealHeroValidator());
        register("x", new XValidator());
        register("y", new YValidator());
        register("weaponType", new WeaponTypeValidator());
        register("mood", new MoodValidator());
        register("filename", new FilenameValidator());
        register("login", new LoginValidator());
        register("password", new PasswordValidator());
    }
}
