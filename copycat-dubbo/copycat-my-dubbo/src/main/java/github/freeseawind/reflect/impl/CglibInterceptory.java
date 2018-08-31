package github.freeseawind.reflect.impl;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

import github.freeseawind.reflect.BaseInvocationHandler;
import github.freeseawind.reflect.InvocationHandler;

/** 
 * @author freeseawind   
 */
public class CglibInterceptory extends BaseInvocationHandler implements InvocationHandler, MethodInterceptor
{
    private Class<?> service;

    public CglibInterceptory(Class<?> service)
    {
        super();
        this.service = service;
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        SPI spi = service.getAnnotation(SPI.class);

        Adaptive adaptive = service.getAnnotation(Adaptive.class);

        Object invoker = this.getInvoker(service, arg2, spi, adaptive);

        return arg3.invoke(invoker, arg2);
    }

}
