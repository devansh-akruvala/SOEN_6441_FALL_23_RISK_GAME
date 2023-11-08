package com.soen6441.risk_game_u14.log_observer_pattern;

import java.util.ArrayList;

/**
 * An Observable class with methods to connect/disconnect with observers and notify them of any updates.
 */
public class Observable {
    ArrayList<Observer> d_Observers = new ArrayList<Observer>();

    /**
     * Attaches an Observer object to the observable.
     *
     * @param p_Observer The Observer object to be attached to the Observable.
     */
    public void attach(Observer p_Observer) {
        this.d_Observers.add(p_Observer);
    }

    /**
     * Detaches an Observer object from the observable.
     *
     * @param p_Observer The Observer object to be detached from the Observable.
     */
    public void detach(Observer p_Observer) {
        if (this.d_Observers.size() != 0) {
            this.d_Observers.remove(p_Observer);
        }
    }

    /**
     * The notify method of the Observable, which calls the update method on every change/addition of a log.
     *
     * @param p_Observable The Observable object.
     */
    public void log(Observable p_Observable) {
        for (Observer l_Observer : d_Observers) {
            l_Observer.update(p_Observable);
        }
    }
}

