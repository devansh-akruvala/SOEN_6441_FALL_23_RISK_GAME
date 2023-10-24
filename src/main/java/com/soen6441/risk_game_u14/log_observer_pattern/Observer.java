package com.soen6441.risk_game_u14.log_observer_pattern;

/**
 * Interface observer which has one update method which is called when observable notifies it. 
 */
public interface Observer {
	/**
	 * This method updates the file on each log entry in LogEntryBuffer.
	 * @param p_Observable Oject of observable who notifies the Observer.
	 */
	public void update(Observable p_Observable);
}
