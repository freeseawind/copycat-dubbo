package github.freeseawind.extension.impl;

import github.freeseawind.extension.AbstractServiceLoader;

/** 
 * @author freeseawind   
 */
public class AdaptiveServiceLoader<S> extends AbstractServiceLoader<S>
{

    /**
     * @param cls
     */
    public AdaptiveServiceLoader(Class<S> cls)
    {
        super(cls);
    }

    @Override
    public S getExtension(String name)
    {
        return null;
    }

    @Override
    protected void init(Class<S> cls)
    {
        
    }

    @Override
    public S getAdaptive()
    {
        return null;
    }
}
