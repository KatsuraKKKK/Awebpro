package com.tarpitout.aio.schema.authoring.spring.schema;

import com.tarpitout.aio.schema.authoring.spring.schema.parser.PuppyDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class AioNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("puppy", new PuppyDefinitionParser());
    }
}
