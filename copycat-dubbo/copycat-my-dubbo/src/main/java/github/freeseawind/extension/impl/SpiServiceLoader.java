package github.freeseawind.extension.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.extension.SPI;

import github.freeseawind.extension.AbstractServiceLoader;
import github.freeseawind.extension.ServiceLoader;
import github.freeseawind.extension.ServiceLoaderFactory;
import github.freeseawind.reflect.InvocationHandler;
import github.freeseawind.reflect.Proxy;

/** 
 * @author freeseawind   
 */
public class SpiServiceLoader<S> extends AbstractServiceLoader<S>
{
    private static final Logger LOG = LoggerFactory.getLogger(SpiServiceLoader.class);

    // Java标准SPI路径
    public static final String SERVICES_DIRECTORY = "META-INF/services/";

    // dubbo 默认SPI路径
    public static final String DUBBO_DIRECTORY = "META-INF/dubbo/";

    // dubbo 默认SPI路径
    public static final String DUBBO_INTERNAL_DIRECTORY = DUBBO_DIRECTORY + "internal/";
    
    public static final String MY_DIRECTORY = "META-INF/freeseawind/";

    private ConcurrentMap<String, Class<S>> CACHED_NAME;

    private ConcurrentMap<String, S> EXTENSION_INSTANCES;

    // The access control context taken when the ServiceLoader is created
    private AccessControlContext acc;
    
    private S serviceProxy;

    /**
     * @param extensionLoader
     * @throws Throwable 
     */
    public SpiServiceLoader(Class<S> cls)
    {
        super(cls);
    }

    @Override
    public S getExtension(String name)
    {
        return lookUp(cls, name);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public synchronized S getAdaptive()
    {
        if(serviceProxy == null)
        {
            ServiceLoader<Proxy> extension = ServiceLoaderFactory.getExtension(Proxy.class);

            @SuppressWarnings("unchecked")
            Proxy<InvocationHandler> proxy = extension.getExtension(Proxy.class.getAnnotation(SPI.class).value());

            serviceProxy =  proxy.createSubClass(cls);
        }
        
        return serviceProxy;
    }
    
    @Override
    protected void init(Class<S> cls)
    {
        this.acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
        
        CACHED_NAME = new ConcurrentHashMap<>();
        
        EXTENSION_INSTANCES = new ConcurrentHashMap<>();

        loadExtension();
    }

    private S lookUp(Class<S> service, String name)
    {
        S ret = EXTENSION_INSTANCES.get(name);

        if (ret != null && ret.getClass() == service)
        {
            return ret;
        }
        
        service = CACHED_NAME.get(name);

        if (!cls.isAssignableFrom(service))
        {
            LOG.error("Provider {} not a subtype", name);
        }

        try
        {
            S p = cls.cast(service.newInstance());

            EXTENSION_INSTANCES.putIfAbsent(name, p);

            return p;
        }
        catch (Throwable x)
        {
            LOG.error("Provider {} could not be instantiated", name);
        }

        return null;
    }

    /**
     * @Description
     * @author freeseawind
     * @Date 2018年8月26日      
     */
    private void loadExtension()
    {
        String newFileName = cls.getName();
        String oldFileName = cls.getName().replace("org.apache", "com.alibaba");

        loadExtension(DUBBO_INTERNAL_DIRECTORY + newFileName);
        loadExtension(DUBBO_INTERNAL_DIRECTORY + oldFileName);
        loadExtension(DUBBO_DIRECTORY + newFileName);
        loadExtension(DUBBO_DIRECTORY + oldFileName);
        loadExtension(SERVICES_DIRECTORY + newFileName);
        loadExtension(SERVICES_DIRECTORY + oldFileName);
        loadExtension(MY_DIRECTORY + newFileName);
    }

    private void loadExtension(String path)
    {

        try
        {
            ClassLoader classLoader = findClassLoader();

            Enumeration<java.net.URL> urls;

            if (classLoader != null)
            {
                urls = classLoader.getResources(path);
            }
            else
            {
                urls = ClassLoader.getSystemResources(path);
            }

            if (urls == null)
            {
                throw new IllegalStateException(String.format("SPI %s not found.", cls.getName()));
            }

            while (urls.hasMoreElements())
            {
                java.net.URL resourceURL = urls.nextElement();
                loadResource(classLoader, resourceURL);
            }
        }
        catch (Throwable t)
        {
            LOG.error("", t);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadResource(ClassLoader classLoader, java.net.URL resourceURL)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream(), "utf-8")))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                final int ci = line.indexOf('#');

                if (ci >= 0)
                {
                    line = line.substring(0, ci);
                }

                line = line.trim();

                if (line.length() > 0)
                {
                    try
                    {
                        String name = null;

                        int i = line.indexOf('=');

                        if (i > 0)
                        {
                            name = line.substring(0, i).trim();
                            line = line.substring(i + 1).trim();
                        }

                        if (line.length() > 0)
                        {
                            CACHED_NAME.putIfAbsent(name, (Class<S>) Class.forName(line, true, classLoader));
                        }

                        LOG.debug("load spi extension for name {}", line);
                    }
                    catch (Throwable t)
                    {
                        LOG.error("", t);
                    }
                }
            }
        }
        catch (Throwable t)
        {
            LOG.error("", t);
        }
    }
}
