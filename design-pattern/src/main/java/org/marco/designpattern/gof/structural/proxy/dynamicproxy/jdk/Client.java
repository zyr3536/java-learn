package org.marco.designpattern.gof.structural.proxy.dynamicproxy.jdk;

import org.marco.designpattern.gof.structural.proxy.staticproxy.RealSubject;
import org.marco.designpattern.gof.structural.proxy.staticproxy.Subject;

public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        JdkProxy jdkProxy = new JdkProxy(subject);
        Subject proxy = (Subject) jdkProxy.getProxy();
        proxy.run();
    }
}
