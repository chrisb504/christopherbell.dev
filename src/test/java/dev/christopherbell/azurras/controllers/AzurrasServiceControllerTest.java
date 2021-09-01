package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.configs.GeneralProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes={GeneralProperties.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class AzurrasServiceControllerTest {
    
    @Test
    public void testtest() {

    }
}