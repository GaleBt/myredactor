package com.mycompany.myredactor.service;

import com.mycompany.myredactor.command.CommandImpl;
import com.mycompany.myredactor.command.CommandFactory;
import com.mycompany.myredactor.exception.UnknownCommandException;
import java.util.regex.Pattern;

/**
 * Parses user input into command
 */
public class ParserService {

    /**
     * Command factory
     */
    private final CommandFactory factory;
    
    /**
     * Regexp patterns
     */
    private final static Pattern LIST_PATTERN = Pattern.compile("\\s*list\\s*");
    private final static Pattern DELETE_PATTERN = Pattern.compile("^\\s*del\\s+\\d+$");
    private final static Pattern INSERT_PATTERN = Pattern.compile("^\\s*ins\\s+\\d+\\s+.*$");
    private final static Pattern SAVE_PATTERN = Pattern.compile("^\\s*save\\s*");
    private final static Pattern QUIT_PATTERN = Pattern.compile("^\\s*quit\\s*");
    private final static Pattern OPEN_PATTERN = Pattern.compile("^open\\s+.*");

    public ParserService() {
        this.factory = CommandFactory.getInstance();
    }

    public ParserService(CommandFactory commandFactory) {
        this.factory = commandFactory;
    }

    /**
     * Parses user input
     */
    public CommandImpl parse(String command) {

        if (OPEN_PATTERN.matcher(command).matches()) {
            return factory.newOpenFile(command.substring(4).trim());
        }

        if (LIST_PATTERN.matcher(command).matches()) {
            return factory.newListCommand();
        }

        if (DELETE_PATTERN.matcher(command).matches()) {
            int num = Integer.parseInt(command.trim().substring(4).trim());
            return factory.newDeleteCommand(num);
        }
        if (INSERT_PATTERN.matcher(command).matches()) {
            //num -string number for  insert
            //insst - string for insert
            //comst - command
            String comst = command.trim().substring(4).trim();
            String numst = comst.substring(0, comst.indexOf(" "));
            int num = Integer.parseInt(numst);
            String insst = comst.replaceFirst(numst, "").trim();
            return factory.newInsertCommand(num, insst);
        }
        if (SAVE_PATTERN.matcher(command).matches()) {
            return factory.newSaveCommand();
        }
        //exit
        if (QUIT_PATTERN.matcher(command).matches()) {
            return factory.newQuitCommand();
        }

        throw new UnknownCommandException(command);

    }

}
