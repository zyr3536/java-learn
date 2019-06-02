package org.marco.designpattern.gof.structural.proxy.dynamicproxy.jdk;

import org.marco.designpattern.gof.structural.proxy.staticproxy.RealSubject;
import org.marco.designpattern.gof.structural.proxy.staticproxy.Subject;

import java.lang.reflect.Proxy;

public class JdkProxy {
    private Subject subject;

    public JdkProxy(Subject subject) {
        this.subject = subject;
    }

    public Object getProxy() {
        Class<?> aClass = subject.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),
                // InvocationHandler
                (proxy, method, args) -> {
                    System.out.println("before");
                    method.invoke(subject,args);
                    System.out.println("after");
                    return null;
                });
    }
}
