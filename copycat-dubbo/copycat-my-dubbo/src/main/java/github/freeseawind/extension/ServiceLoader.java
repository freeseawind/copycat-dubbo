package github.freeseawind.extension;

import com.alibaba.dubbo.common.extension.SPI;

/** 
 * @author freeseawind   
 */
@SPI("spi")
public interface ServiceLoader<S>
{
    /**
     * 获取服务扩展
     *
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    S getExtension(String name);
    
    /**
     * 获取服务扩展代理
     * 
     * @param service
     * @return
     */
    S getAdaptive();
}
