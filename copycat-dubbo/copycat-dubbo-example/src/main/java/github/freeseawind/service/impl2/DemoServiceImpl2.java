package github.freeseawind.service.impl2;

import com.alibaba.dubbo.config.annotation.Service;

import github.freeseawind.service.DemoService;

/** 
 * @author freeseawind   
 */
@Service(timeout = 5000, loadbalance="random")
public class DemoServiceImpl2 implements DemoService
{
    @Override
    public String sayHello(String name)
    {
        return "Service2 -> Hello " + name;
    }
}
