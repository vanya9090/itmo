package com.vanya9090.client.utils;

public class ExecuteLogger implements ILogger{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    @Override
    public void info(Object obj) {

    }

    @Override
    public void success(Object obj) {

    }

    @Override
    public void warning(Object obj) {

    }

    @Override
    public void error(Object obj) {
        System.out.println(ANSI_RED + obj + ANSI_RESET);
    }

    @Override
    public void table(Object obj1, Object obj2) {

    }

    @Override
    public void field(Object obj) {

    }
}
