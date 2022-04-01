package com.sjq.study.dp.observer;

/**
 * @author java coder
 * @date 2022/3/29 17:54
 * @desc: 观察者模式 场景模拟
 */
public class Client {

    public static void main(String[] args) {
        // 被观察者
        ConcreteSubject subject = new ConcreteSubject();
        // 观察者
        Observer observer = new ConcreteObserver();
        // 观察者和被观察者 绑定
        subject.addObserver(observer);
        // 被观察者行动了
        subject.play();
    }

}
