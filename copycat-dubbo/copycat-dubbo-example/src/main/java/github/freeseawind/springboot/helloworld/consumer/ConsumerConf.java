package github.freeseawind.springboot.helloworld.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/** 
 * @author freeseawind   
 */
@Configuration
public class ConsumerConf
{
    @Bean
    public ApplicationConfig applicationConfig()
    {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("consumer-test");
        return applicationConfig;
    }

    /**
     * 服务消费者配置
     * 
     * @author freeseawind
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig()
    {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        return consumerConfig;
    }

    /**
     * 注册中心配置
     * 
     * @author freeseawind
     * @return
     */
    @Bean
    public RegistryConfig registryConfig()
    {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }
}
