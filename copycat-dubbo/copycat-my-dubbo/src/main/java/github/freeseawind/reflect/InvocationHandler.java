package github.freeseawind.reflect;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;


/** 
 * @author freeseawind   
 */
public interface InvocationHandler
{
    <S> S getInvoker(Class<S> cls, Object[] params, SPI spi, Adaptive adaptive);
}
