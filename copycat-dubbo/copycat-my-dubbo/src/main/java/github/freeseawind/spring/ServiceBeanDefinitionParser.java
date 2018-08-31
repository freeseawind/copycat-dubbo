package github.freeseawind.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import github.freeseawind.config.ServiceConfig;


/** 
 * @author freeseawind   
 */
public class ServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser
{
    @Override
    protected Class<?> getBeanClass(Element element)
    {
        return ServiceConfig.class;
    }
    
    protected void doParse(Element element, BeanDefinitionBuilder bean)
    {
        String interfaceName = element.getAttribute("interface");

        String refName = element.getAttribute("ref");
        
        BeanDefinitionBuilder.genericBeanDefinition(refName);
        
        bean.addPropertyValue("interfaceName", interfaceName);
        
        bean.addPropertyValue("ref", BeanDefinitionBuilder.genericBeanDefinition(refName).getBeanDefinition());
    }
}
