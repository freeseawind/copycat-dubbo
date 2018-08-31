package github.freeseawind.extension;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import github.freeseawind.extension.impl.SpiServiceLoader;
import github.freeseawind.util.BaseUtil;

/** 
 * @author freeseawind   
 */
public class ServiceLoaderFactory
{
    static final ConcurrentMap<Class<?>, ServiceLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();

    static <S> void addExtension(Class<S> key, ServiceLoader<S> val)
    {
        EXTENSION_LOADERS.put(key, val);
    }

    @SuppressWarnings("unchecked")
    public static <S> ServiceLoader<S> getExtension(Class<S> service)
    {
        if (service == null)
        {
            throw new IllegalArgumentException("Extension type == null");
        }

        if (!service.isInterface())
        {
            throw new IllegalArgumentException("Extension type(" + service + ") is not interface!");
        }

        if (!BaseUtil.withSPI(service))
        {
            throw new IllegalArgumentException(
                    "Extension type(" + service + ") is not extension, because WITHOUT @SPI Annotation!");
        }

        ServiceLoader<S> loader = (ServiceLoader<S>) EXTENSION_LOADERS.get(service);

        if (loader == null)
        {
            loader = (ServiceLoader<S>) EXTENSION_LOADERS.putIfAbsent(service, new SpiServiceLoader<S>(service));
        }

        return loader;
    }
}
