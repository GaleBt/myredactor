package com.mycompany.myredactor.command;

/**
 * Contains possible command types along with their descriptions
 */
public enum CommandType {

    LIST {
        @Override
        public String getName() {
            return "list";
        }

        @Override
        public String getDescription() {
            return "list each line in n:xxx format";
        }

    },
    DELETE {
        @Override
        public String getName() {
            return "del";
        }

        @Override
        public String getDescription() {
            return "delete line at n";
        }

    },
    INSERT {
        @Override
        public String getName() {
            return "ins";
        }

        @Override
        public String getDescription() {
            return "insert a line at n";
        }

    },
    SAVE {
        @Override
        public String getName() {
            return "save";
        }

        @Override
        public String getDescription() {
            return "saves to disk";
        }

    },
    QUIT {
        @Override
        public String getName() {
            return "quit";
        }

        @Override
        public String getDescription() {
            return "quits the editor and returns to the command line";
        }

    };

    public abstract String getName();

    public abstract String getDescription();

}
