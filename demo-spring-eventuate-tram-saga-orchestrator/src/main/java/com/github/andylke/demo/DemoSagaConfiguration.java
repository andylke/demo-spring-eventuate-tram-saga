package com.github.andylke.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@Configuration
@Import({OptimisticLockingDecoratorConfiguration.class})
public class DemoSagaConfiguration {}
