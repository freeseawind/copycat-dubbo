package github.freeseawind.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import github.freeseawind.service.DemoService;

/** 
 * @author freeseawind   
 */
@Service(timeout = 5000, loadbalance="random")
public class DemoServiceImpl implements DemoService
{
    @Override
    public String sayHello(String name)
    {
        return "Service1 -> Hello " + name;
    }
}
