package github.freeseawind.spring.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider
{

    public static void main(String[] args) throws Exception
    {
        System.setProperty("java.net.preferIPv4Stack", "true");
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "helloworld/provider.xml" });
        
        context.start();
        
        System.out.println("Provider started.");
        
        System.in.read(); // press any key to exit
    }
}