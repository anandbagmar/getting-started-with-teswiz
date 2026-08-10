package com.znsio.sample.e2e.screen.theapp;

public abstract class EchoScreen {

    public static EchoScreen get() {
        return com.znsio.teswiz.screen.ScreenRegistry.getScreen(EchoScreen.class);
    }

    public abstract EchoScreen echoMessage(String message);
}
