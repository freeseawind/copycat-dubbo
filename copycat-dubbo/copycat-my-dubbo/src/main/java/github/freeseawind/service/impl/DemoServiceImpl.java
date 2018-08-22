package github.freeseawind.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import github.freeseawind.service.DemoService;

/** 
 * @author freeseawind   
 */
@Service(timeout = 5000)
public class DemoServiceImpl implements DemoService
{
    @Override
    public String sayHello(String name)
    {
        return "Hello " + name;
    }
}
