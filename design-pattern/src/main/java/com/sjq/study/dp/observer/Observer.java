package com.sjq.study.dp.observer;

/**
 * 观察者
 * @author SJQ
 */
public interface Observer {

    /**
     * 观察到消息后 做出响应
     */
    void update(Object obj);

}
