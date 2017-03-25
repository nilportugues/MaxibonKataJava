package com.karumi.maxibonkata;

import com.karumi.maxibonkata.generator.KarumiesGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private static final String ANY_NAME = "Nil";

    private static final int ANY_NUMBER_OF_MAXIBONS = 1;

    private static final int MAXIBON_PEDRO = 3;
    private static final int MAXIBON_DAVIDE = 0;
    private static final int MAXIBON_ALBERTO = 1;
    private static final int MAXIBON_JORGE = 2;
    private static final int MAXIBON_SERGIO = 1;

    @Property
    public void theNumberOfMaxibonsAssignedIsPositiveOrZero(final int number) {
        final Developer developer = new Developer(ANY_NAME, number);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    //Creamos una property... es como @Test, pero para property-testing
    @Property(trials = 50)
    public void theNumberOfMaxibonsAssignedIsPositiveOrZero(
            @From(KarumiesGenerator.class) final Developer developer) {

        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    @Test
    public void testNumberOfMaxibonsMatchTheDocumentation() {
        assertSame(Karumies.PEDRO.getNumberOfMaxibonsToGrab(), MAXIBON_PEDRO);
        assertSame(Karumies.DAVIDE.getNumberOfMaxibonsToGrab(), MAXIBON_DAVIDE);
        assertSame(Karumies.ALBERTO.getNumberOfMaxibonsToGrab(), MAXIBON_ALBERTO);
        assertSame(Karumies.JORGE.getNumberOfMaxibonsToGrab(), MAXIBON_JORGE);
        assertSame(Karumies.SERGIO.getNumberOfMaxibonsToGrab(), MAXIBON_SERGIO);
    }
}