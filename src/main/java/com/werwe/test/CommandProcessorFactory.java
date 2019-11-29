package com.werwe.test;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {HelloWorldModule.class, LoginCommandModule.class, SystemOutModule.class, UserCommandsRouter.InstallationModule.class})
public interface CommandProcessorFactory {
    CommandProcessor processor();
}
