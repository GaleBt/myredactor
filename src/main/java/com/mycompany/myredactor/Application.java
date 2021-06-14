package com.mycompany.myredactor;

import com.mycompany.myredactor.service.ShellService;

/**
 * Application entry point 
 */
public class Application {

    public static void main(String[] args) {
        new ShellService().service(args);
    }

}
