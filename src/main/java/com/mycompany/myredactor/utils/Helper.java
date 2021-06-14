package com.mycompany.myredactor.utils;

import com.mycompany.myredactor.command.CommandType;
import java.util.Arrays;

/**
 * Outputs list of commands
 */
public class Helper {

    public static void Help() {
        Arrays.asList(CommandType.values()).stream()
                .forEach(commandType -> System.out.println(
                commandType.getName()
                + " - "
                + commandType.getDescription()
        ));
    }

}
