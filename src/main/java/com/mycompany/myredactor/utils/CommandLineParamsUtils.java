package com.mycompany.myredactor.utils;

/**
 * Helper class for command line parameters validation
 */
public class CommandLineParamsUtils {
    
    public static void validate(String[] args) {
        if(args.length == 0) {
            throw new IllegalArgumentException("File path not supplied, "
                    + "should be given as a first parameter");
        }
        if(args.length > 1) {
            throw new IllegalArgumentException("Too many command line parameters");
        }
    }
    
}
