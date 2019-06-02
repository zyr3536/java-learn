package org.marco.designpattern.gof.structural.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.marco.designpattern.gof.structural.proxy.staticproxy.RealSubject;

public class Client {

    public static void main(String[] args) {
         RealSubject subject = (RealSubject) Enhancer.create(RealSubject.class, (MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("proxy start : " + proxy.getClass() + ": " + obj.getClass());
            Object res2 = proxy.invokeSuper(obj, args1);
            System.out.println("proxy over");
            return res2;
        });
         subject.run();
    }
}
