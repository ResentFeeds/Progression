package me.Elliott_.Progression.world.exceptions;

public class WorldGenerationException extends Exception {

    private final String message;

    public WorldGenerationException(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

}
