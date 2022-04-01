package com.sjq.study.dp.observer;

/**
 * @author java coder
 * @date 2022/3/29 17:51
 * @desc: 具体的被观察者
 */
public class ConcreteSubject extends Subject {

    public void play(){
        System.out.println("我开始玩耍了，有人观察我吗？？？？");
        super.notifyObservers("play");
    }

}
