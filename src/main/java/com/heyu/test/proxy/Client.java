package com.heyu.test.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Client {

    @Autowired
    private Subject subject;

    public void test(){
        System.out.println(subject.getClass());
        subject.test();
    }

    @Before
    public void init(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\classes");
    }

    @org.junit.Test
    public void tet(){


        CgPorxy porxy = new CgPorxy();

        Subject subject = (Subject) porxy.getProxy(Subject.class);
        System.out.println(subject.test());
        /*MyInvocationHandler handler = new MyInvocationHandler(subject);

        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class},handler);

        byte[] files = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Interface.class});
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("d:\\$Proxy0.class");
            output.write(files);
            output.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(proxy.getClass());
        proxy.test();*/
    }
}
