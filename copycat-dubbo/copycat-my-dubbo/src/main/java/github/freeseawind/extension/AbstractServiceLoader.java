package github.freeseawind.extension;

/** 
 * @author freeseawind   
 */
public abstract class AbstractServiceLoader<S> implements ServiceLoader<S>
{
    protected ClassLoader loader;
    
    protected Class<S> cls;
    
    public AbstractServiceLoader(Class<S> cls)
    {
        this.cls = cls;
        
        init(cls);
    }
    
    /**
     * 
     */
    protected ClassLoader findClassLoader()
    {
        loader = this.getClass().getClassLoader();
        
        return (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
    }
    
    protected abstract void init(Class<S> cls);
}
