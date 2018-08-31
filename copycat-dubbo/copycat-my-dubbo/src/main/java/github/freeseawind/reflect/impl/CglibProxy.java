package github.freeseawind.reflect.impl;

import org.springframework.cglib.proxy.Enhancer;

import github.freeseawind.reflect.Proxy;


/** 
 * @author freeseawind   
 */
public class CglibProxy implements Proxy<CglibInterceptory>
{
    @SuppressWarnings("unchecked")
    @Override
    public <T> T createSubClass(Class<T> service)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {service});
        enhancer.setCallback(createInvocationHandler(service));
        return (T) enhancer.create();
    }
    
    @Override
    public CglibInterceptory createInvocationHandler(Class<?> service)
    {
        return new CglibInterceptory(service);
    }
    
    public static void main(String[] args)
    {
    }
}
