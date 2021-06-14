package com.mycompany.myredactor.command;

import com.mycompany.myredactor.service.RedactorService;

/**
 * Factory for creating commands
 */
public class CommandFactory {
    
    /**
     * Redactor service
     */
    private final RedactorService redactorService = new RedactorService();
    
    /**
     * Factory instance
     */
    private static CommandFactory INSTANCE;

    /**
     * Constructor is private due to singletone pattern
     */
    private CommandFactory()
    {
        
    }
    
    /**
     * Singletone getInstance() method
     * 
     * @return Initialized CommandFactory instance
     */
    public static synchronized CommandFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CommandFactory();
        }
        return INSTANCE;
    }

    /**
     * Creates list command
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newListCommand() {
        CommandImpl command = new CommandImpl();
        command.setType(CommandType.LIST);
        command.setConsumer(s -> {
            redactorService.readAllLine();
        });
        return command;

    }

    /**
     * Creates delete command
     * 
     * @param num Line number to delete
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newDeleteCommand(Integer num) {
        CommandImpl command = new CommandImpl();
        command.setType(CommandType.DELETE);
        command.setConsumer(s -> {
            redactorService.deleteLine(num);
        });
        return command;

    }

    /**
     * Creates insert command
     * 
     * @param num Line number to insert
     * @param str String to insert
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newInsertCommand(Integer num, String str) {
        CommandImpl command = new CommandImpl();
        command.setType(CommandType.INSERT);
        command.setConsumer(s -> {
            redactorService.insertLine(num, str);
        });
        return command;

    }

    /**
     * Creates save command 
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newSaveCommand() {
        CommandImpl command = new CommandImpl();
        command.setType(CommandType.SAVE);
        command.setConsumer(s -> {
            redactorService.saveList();
        });
        return command;
    }

    /**
     * Creates quit command
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newQuitCommand() {
        CommandImpl command = new CommandImpl();
        command.setType(CommandType.QUIT);
        command.setConsumer(s -> {
            System.out.println("Goodbye");
            System.exit(0);
        });
        return command;
    }
    
    /**
     * Creates open file command
     * 
     * @param arg Path to the file
     * 
     * @return CommandImpl instance
     */
    public CommandImpl newOpenFile(String arg) {
        CommandImpl command = new CommandImpl();
        command.setConsumer(s -> {
            redactorService.openFile(arg);
        });
        return command;
    }

}
