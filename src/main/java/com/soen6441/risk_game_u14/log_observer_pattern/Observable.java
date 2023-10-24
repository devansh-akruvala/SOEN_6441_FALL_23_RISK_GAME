package com.soen6441.risk_game_u14.log_observer_pattern;

import java.util.ArrayList;

/**
 * Observable class which has methods to connect/disconnect with observers and notifies if there is any update. 
 */
public class Observable {
	ArrayList<Observer> d_Observers= new ArrayList<Observer>();

	/**
	 * This method attaches the Observer object with the observable.
	 * @param p_Observer Object of Observer which is going to be attached with Observable.
	 */
	public void attach(Observer p_Observer){
		this.d_Observers.add(p_Observer);
	}
	/**
	 * This method detaches the Observer object with the observable.
	 * @param p_Observer Object of Observer which is going to be detached with Observable. 
	 */
	public void detach(Observer p_Observer){
		if(this.d_Observers.size()!=0){
			this.d_Observers.remove(p_Observer);
		}
	}
	/**
	 * This is notify method of the Observable which calls the update method on every change/addition of log.
	 * @param p_Observable Object of observable
	 */
	public void log(Observable p_Observable){
		for(Observer l_Observer: d_Observers){
			l_Observer.update(p_Observable);
		}
	}
}

