package vanya9090.client.commands;

import vanya9090.common.exceptions.*;

import java.util.Scanner;

public interface Executable {
    public String apply(String[] args, Scanner fileReader) throws Exception;
}
