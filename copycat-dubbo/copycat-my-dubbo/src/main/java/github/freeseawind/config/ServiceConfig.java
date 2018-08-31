package github.freeseawind.config;

import java.io.Serializable;

public class ServiceConfig<T> implements Serializable
{
    private static final long serialVersionUID = -7728317885177767541L;
    
    /**
     * 
     * 服务接口名，必填参数
     */
    private String interfaceName;
    
    /**
     * 服务对象实现引用，必填参数
     */
    private T ref;

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName)
    {
        this.interfaceName = interfaceName;
    }

    public T getRef()
    {
        return ref;
    }

    public void setRef(T ref)
    {
        this.ref = ref;
    }
}
