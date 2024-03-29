package com.soen6441.risk_game_u14.log_observer_pattern;

import java.io.Serializable;

/**
 * An Observer interface containing an update method, called when notified by an Observable.
 */
public interface Observer extends Serializable {
    /**
     * This method updates the file with each log entry in the LogEntryBuffer.
     *
     * @param p_Observable The Observable object notifying the Observer.
     */
    public void update(Observable p_Observable);
}
