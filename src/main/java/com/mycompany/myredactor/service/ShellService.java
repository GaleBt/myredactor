package com.mycompany.myredactor.service;

import java.util.Scanner;
import static com.mycompany.myredactor.utils.Helper.Help;
import com.mycompany.myredactor.command.CommandFactory;
import com.mycompany.myredactor.utils.CommandLineParamsUtils;
import com.mycompany.myredactor.utils.FileUtils;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Handles user interaction
 */
public class ShellService {

    private final ParserService parserService = new ParserService();

    private final CommandFactory commandFactory = CommandFactory.getInstance();

    private final Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public void service(String[] args) {

        // open file
        try {
            CommandLineParamsUtils.validate(args);
            FileUtils.validate(args[0]);
            commandFactory.newOpenFile(args[0]).execute();
        } catch (Exception e) {
            System.out.println("An error happened when opening application: " + e.getMessage());
            System.exit(0);
        }

        /**
         * list of commands
         */
        Help();

        // loop until "quit" command is entered
        while (true) {
            System.out.print("> ");
            System.out.flush();
            String str = scanner.nextLine();
            try {
                parserService.parse(str).execute();
            } catch (Exception e) {
                System.out.println("\nINVALID INPUT: " + e.getMessage());
            }
        }

    }
}
