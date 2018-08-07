package github.freeseawind.service;

/** 
 * @author freeseawind   
 */
public class DemoServiceImpl implements DemoService
{
    @Override
    public String sayHello(String name)
    {
        return "Hello " + name;
    }
}
