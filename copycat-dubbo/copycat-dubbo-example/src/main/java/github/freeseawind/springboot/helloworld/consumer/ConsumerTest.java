package github.freeseawind.springboot.helloworld.consumer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import github.freeseawind.service.DemoService;

/** 
 * @author freeseawind   
 */
@SpringBootApplication
@Controller
@DubboComponentScan(basePackages = "github.freeseawind.springboot.helloworld.consumer")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerTest.class)
public class ConsumerTest
{
    @Reference(loadbalance="random")
    private DemoService demoService;
    
    @Test
    public void testRPC()
    {
        for(int i = 0; i < 100; i++)
        {
            System.out.println(demoService.sayHello("freeseawind"));
        }
        
        String str = demoService.sayHello("freeseawind");
        
        assertEquals("Hello freeseawind", str);
        
    }
}
