package com.tarpitout.aio.schema.authoring.spring.schema.parser;

import com.tarpitout.aio.schema.authoring.beans.Puppy;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PuppyDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Puppy.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        if (StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }

        String color = element.getAttribute("color");
        builder.addPropertyValue("color", color);
    }
}
