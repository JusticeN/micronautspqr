package com.nanhou;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.stream.Stream;

@MicronautTest
class MicronautspqrTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testIdgeneration() {
        String shortUUID = IdGenerator.generate();
        System.out.println(shortUUID);
        Assertions.assertEquals(11, shortUUID.length());
        Stream.generate(IdGenerator::generate).limit(100)
                .forEach(generated -> Assertions.assertNotEquals(shortUUID, generated));
    }

}
