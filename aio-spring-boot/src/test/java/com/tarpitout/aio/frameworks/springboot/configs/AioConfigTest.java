package com.tarpitout.aio.frameworks.springboot.configs;

import com.tarpitout.aio.schema.authoring.beans.Puppy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

//@ContextConfiguration
@SpringBootTest
public class AioConfigTest {
    @Autowired
    private Puppy puppy1;

    @Autowired
    private Puppy puppy2;

    @Test
    public void testPuppy1() {
        System.out.println(puppy1.getName());
        System.out.println(puppy1.getColor());
    }

    @Test
    public void testPuppy2() {
        System.out.println(puppy2.getName());
        System.out.println(puppy2.getColor());
    }

}
