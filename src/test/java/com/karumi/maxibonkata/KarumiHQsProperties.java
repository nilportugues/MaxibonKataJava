package com.karumi.maxibonkata;


import com.karumi.maxibonkata.generator.DevelopersGenerator;
import com.karumi.maxibonkata.generator.HungryDevelopersGenerator;
import com.karumi.maxibonkata.generator.NotSoHungryDevelopersGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;


@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    private final static int MIN_MAXIBONS = 2;

    private KarumiHQs karumiHQs;
    private Chat chat;

    @Before
    public void setUp() {
        chat = Mockito.mock(Chat.class);
        karumiHQs = new KarumiHQs(chat);
    }

    @Property
    public void thereAreAlwaysMoreThanMinimumAmountOfMaxibonShouldRemain(
            @From(DevelopersGenerator.class) final Developer developer) {

        karumiHQs.openFridge(developer);
        Assert.assertTrue(karumiHQs.getMaxibonsLeft() > MIN_MAXIBONS);
    }

    @Property
    public void ifSomeDevelopersGoToTheKitchenTheMinimumAmountOfMaxibonShouldRemain(
            final List<@From(DevelopersGenerator.class) Developer> developerList) {

        karumiHQs.openFridge(developerList);
        Assert.assertTrue(karumiHQs.getMaxibonsLeft() > MIN_MAXIBONS);
    }

    @Property
    public void testChatMessageIsSendWhenWeRunOutOfMaxibon(
            @From(HungryDevelopersGenerator.class) final Developer developer) {

        karumiHQs.openFridge(developer);

        Mockito.verify(chat).sendMessage(buildChatMessage(developer));
    }

    private String buildChatMessage(@From(HungryDevelopersGenerator.class) Developer developer) {
        return "Hi guys, I'm " + developer.getName() + ". We need more maxibons!";
    }

    @Property
    public void testNoChatMessageIsSendIfMaxibonAreAvailable(
            @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);

        Mockito.verify(chat, Mockito.never()).sendMessage(null);
    }
}