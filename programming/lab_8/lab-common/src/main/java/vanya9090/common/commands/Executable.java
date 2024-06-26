package vanya9090.common.commands;

import java.util.Scanner;

/**
 * интерфейс для команд с формами
 *
 * @author vanya9090
 */
public interface Executable {
    String apply(String[] args, Scanner fileReader) throws Exception;
}
