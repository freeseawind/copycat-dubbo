package github.freeseawind.springboot.helloworld.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/** 
 * @author freeseawind   
 */
@SpringBootApplication
@EnableAutoConfiguration
@DubboComponentScan(basePackages = "github.freeseawind.service.impl2")
public class ProviderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
