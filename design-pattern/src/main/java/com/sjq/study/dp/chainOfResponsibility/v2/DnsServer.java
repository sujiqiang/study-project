package com.sjq.study.dp.chainOfResponsibility.v2;

import com.sjq.study.dp.chainOfResponsibility.Record;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * @author SJQ
 * Observable: 被观察者、
 * Observer： 观察者
 */
public abstract class DnsServer extends Observable implements Observer {

    /**
     * 接收到事件之后处理事件
     *
     * @param observable
     * @param arg        请求事件
     */
    @Override
    public void update(Observable observable, Object arg) {

        Record record = (Record) arg;
        // 是本地域名，解析地址
        if (isLocal(record.getDomain())) {
            record.setIp(getIpAddress(record.getDomain()));
        } else {
            // 不是本地域名，找上级 DNS 服务
            super.setChanged(); // Observable
            super.notifyObservers(record); // Observable
        }
        // 签名: 每个服务器签自己的名，打标记
        sign(record);
    }

    /**
     * 设置父级 Server
     *
     * @param dnsServer
     */
    public void setUpperServer(DnsServer dnsServer) {
        // 清空 观察者
        super.deleteObservers();
        // 设置 观察者
        super.addObserver(dnsServer);
    }

    private String getIpAddress(String domain) {
        Random random = new Random();
        return random.nextInt(255) + "." + random.nextInt(255) + "." + random.nextInt(255);
    }


    protected abstract boolean isLocal(String domain);

    protected abstract void sign(Record record);


}
