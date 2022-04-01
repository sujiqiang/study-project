package com.sjq.study.dp.observer;

/**
 * @author java coder
 * @date
 * @desc: 具体的观察者
 */
public class ConcreteObserver implements Observer {

    /**
     * @desc: 观察到消息后 做出响应
     */
    @Override
    public void update(Object obj) {
        System.out.println("观察到对方的行为:" + obj.toString() + ",我开始行动了！！！！");
    }

}
