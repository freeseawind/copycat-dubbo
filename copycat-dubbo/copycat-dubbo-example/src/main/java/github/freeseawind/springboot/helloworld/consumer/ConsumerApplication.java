package github.freeseawind.springboot.helloworld.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import github.freeseawind.service.DemoService;

/** 
 * @author freeseawind   
 */
@SpringBootApplication
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages="github.freeseawind.springboot.helloworld.consumer")
@DubboComponentScan(basePackages = "github.freeseawind.springboot.helloworld.consumer")
public class ConsumerApplication
{
    @Reference
    private DemoService demoService;
    
    public static void main(String[] args)
    {
        SpringApplication.run(ConsumerApplication.class, args);
    }
    
    @RequestMapping("/a")
    @ResponseBody
    public String sayHello()
    {
        return demoService.sayHello("freeseawind");
    }
}
