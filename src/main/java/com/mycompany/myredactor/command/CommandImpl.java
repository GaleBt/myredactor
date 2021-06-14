package com.mycompany.myredactor.command;

import java.util.function.Consumer;

/**
 * Contains type of the command to execute and execution callback
 */
public class CommandImpl implements Command {

    private CommandType type;
    private Consumer consumer;

    public void setType(CommandType type) {
        this.type = type;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void execute() {
        consumer.accept(null);
    }

}
