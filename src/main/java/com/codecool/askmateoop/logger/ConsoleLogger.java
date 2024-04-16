package com.codecool.askmateoop.logger;

import java.time.LocalDate;

public class ConsoleLogger implements Logger{

    @Override
    public void logInfo(String message) {
        displayMessage( "INFO", message);
    }

    @Override
    public void logError(String message) {
        displayMessage("ERROR", message);
    }

    private void displayMessage(String messageType, String message){
        System.out.println("[" + messageType + "] " + LocalDate.now() + " - " + message);
    }
}
