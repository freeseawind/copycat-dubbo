package github.freeseawind.util;

import com.alibaba.dubbo.common.extension.SPI;

/** 
 * @author freeseawind   
 */
public class BaseUtil
{
    public static <S> boolean withSPI(Class<S> service)
    {
        return service.isAnnotationPresent(SPI.class);
    }
    
    public static <S> String getSpiName(Class<S> service)
    {
        return service.getAnnotation(SPI.class).value();
    }
}
