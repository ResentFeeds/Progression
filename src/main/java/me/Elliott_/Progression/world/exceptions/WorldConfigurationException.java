package me.Elliott_.Progression.world.exceptions;

/**
 * Created by Elliott on 18/03/2015.
 */
public class WorldConfigurationException extends Exception {

    private final String message;

    public WorldConfigurationException(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

}
