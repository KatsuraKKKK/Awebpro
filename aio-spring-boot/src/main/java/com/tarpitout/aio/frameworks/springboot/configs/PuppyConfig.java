package com.tarpitout.aio.frameworks.springboot.configs;

import com.tarpitout.aio.schema.authoring.beans.Puppy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PuppyConfig {


    @Bean
    public Puppy puppy1() {
//        Puppy puppy = new Puppy();
//        puppy.setName(name);
//        puppy.setColor(color);
//        return puppy;
        return new Puppy();
    }
}
