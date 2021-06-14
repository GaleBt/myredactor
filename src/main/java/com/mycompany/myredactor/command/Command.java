package com.mycompany.myredactor.command;

/**
 * Contains type of the command to execute and execution callback
 */
public interface Command {

    CommandType getType();
    void execute();

}
