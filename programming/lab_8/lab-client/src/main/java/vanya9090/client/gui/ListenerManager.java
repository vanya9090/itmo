//package vanya9090.client.gui;
//
//import vanya9090.client.gui.listeners.MyListener;
//import vanya9090.common.commands.Command;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ListenerManager {
//    private static final Map<String, MyListener<?>> handlers = new HashMap<>();
//    public static void register(String handleName, Handler<?> handler){
//        handlers.put(handleName, handler);
//    }
//    public Map<String, Handler<?>> getHandlers() {
//        return handlers;
//    }
//    static {
//        register("boolean", new BooleanHandler());
//        register("Boolean", new BooleanHandler());
//        register("Float", new FloatHandler());
//        register("float", new FloatHandler());
//        register("int", new IntHandler());
//        register("Integer", new IntHandler());
//        register("LocalDate", new LocalDateHandler());
//        register("Mood", new MoodHandler());
//        register("String", new StringHandler());
//        register("WeaponType", new WeaponTypeHandler());
//    }
//}
