package com.sjq.study.dp.observer;

import java.util.Vector;

/**
 * 被观察者
 *
 * @author SJQ
 */
public abstract class Subject {

    /**
     * 观察者
     */
    private final Vector<Observer> observers = new Vector<>();

    /**
     * 增加 观察者
     *
     * @param obs
     */
    protected void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * 删除 观察者
     *
     * @param obs
     */
    protected void deleteObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * 通知所有 观察者
     *
     * @param obs
     */
    protected void notifyObservers(Object obj) {
        for (Observer obs : observers) {
            obs.update(obj);
        }
    }


}
