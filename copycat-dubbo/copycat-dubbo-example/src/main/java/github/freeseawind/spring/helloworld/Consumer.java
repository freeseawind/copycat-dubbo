package github.freeseawind.spring.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import github.freeseawind.service.DemoService;

public class Consumer
{
    public static void main(String[] args) throws Exception
    {
        System.setProperty("java.net.preferIPv4Stack", "true");
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "helloworld/consumer.xml" });
        
        context.start();
        
        // Obtaining a remote service proxy
        DemoService demoService = (DemoService) context.getBean("demoService");
        
        // Executing remote methods
        String hello = demoService.sayHello("world");
        
        // Display the call result
        System.out.println(hello);
    }
}