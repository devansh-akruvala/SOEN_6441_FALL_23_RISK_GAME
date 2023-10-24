package com.soen6441.risk_game_u14.log_observer_pattern;

/**
 * Class which extends the Observable class. To log any changes in file, we use the object of this class. 
 */
public class LogEntryBuffer extends Observable {
	private String d_Result;
	private Logger d_Logger;

	/**
	 * Constructor which creates the object of Observer logger and attach it with this class. 
	 */
	public LogEntryBuffer(){
		d_Logger=new Logger();
		this.attach(d_Logger);
	}
	
	/**
	 * This method is called when we want to log anything in the file.
	 * @param p_Result String to be logged. 
	 */
	public void setResult(String p_Result){
		this.d_Result=p_Result;
		log(this);
	}
	
	/**
	 * This method returns the string which was logged.
	 * @return Result string which was logged
	 */
	public String getResult(){
		return this.d_Result;
	}
}

