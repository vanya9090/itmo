package vanya9090.client.commands;

import vanya9090.common.exceptions.BooleanFormatException;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;

import java.util.Scanner;

public interface Executable {
    public void apply(String[] args, Scanner fileReader) throws BooleanFormatException, ParseException, EmptyFieldException;
}
