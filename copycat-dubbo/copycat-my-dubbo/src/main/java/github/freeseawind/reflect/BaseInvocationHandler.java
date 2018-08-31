package github.freeseawind.reflect;

import java.util.Arrays;

import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

import github.freeseawind.extension.ServiceLoader;
import github.freeseawind.extension.ServiceLoaderFactory;

/** 
 * @author freeseawind   
 */
public class BaseInvocationHandler implements InvocationHandler
{
    public <S> S getInvoker(Class<S> cls, Object[] params, SPI spi, Adaptive adaptive)
    {
        ServiceLoader<S> serviceLoader = ServiceLoaderFactory.getExtension(cls);
        
        URL url = null;
        
        for (Object obj : params)
        {
            if(obj instanceof URL)
            {
                url = (URL)obj;
            }
        }
        
        String key = spi.value();
        
        if(adaptive != null && url != null)
        {
            URL tempURL = url;
            
            key = Arrays.asList(adaptive.value()).stream().filter((t) ->
            {
                String param = tempURL.getParameter(t, spi.value());
                
                return !StringUtils.isEmpty(param);
                
            }).findFirst().get();
        }
        
        S service = serviceLoader.getExtension(key);
        
        return service;
    }
}
