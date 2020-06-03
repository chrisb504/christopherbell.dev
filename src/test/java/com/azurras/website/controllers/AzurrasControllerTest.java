package com.azurras.website.controllers;

import com.azurras.website.configs.GeneralConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes={GeneralConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class AzurrasControllerTest {
    
    @Test
    public void testtest() {

    }
}