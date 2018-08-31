package github.freeseawind.reflect;

import com.alibaba.dubbo.common.extension.SPI;

/** 
 * @author freeseawind   
 */
@SPI("cglib")
public interface Proxy<I extends InvocationHandler>
{
    <T> T createSubClass(Class<T> service);

    I createInvocationHandler(Class<?> service);
}
