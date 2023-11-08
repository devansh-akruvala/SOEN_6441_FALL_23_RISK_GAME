package com.soen6441.risk_game_u14.log_observer_pattern;

/**
 * An extension of the Observable class, this class is used to log any changes in a file.
 */
public class LogEntryBuffer extends Observable {
    private String d_Result;
    private Logger d_Logger;

    /**
     * Constructor that creates an instance of the Logger observer and attaches it to this class.
     */
    public LogEntryBuffer() {
        d_Logger = new Logger();
        this.attach(d_Logger);
    }

    /**
     * Logs a specified string into the file.
     *
     * @param p_Result The string to be logged.
     */
    public void setResult(String p_Result) {
        this.d_Result = p_Result;
        log(this);
    }

    /**
     * Returns the string that was logged.
     *
     * @return The result string that was logged.
     */
    public String getResult() {
        return this.d_Result;
    }
}

