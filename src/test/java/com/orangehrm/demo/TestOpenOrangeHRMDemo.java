package com.orangehrm.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.chrome.ChromeDriver;

public class TestOpenOrangeHRMDemo {
    private static Logger logger;
    private static ChromeDriver chromeDriver;

    @BeforeAll
    static void setUp() {
        logger = LogManager.getLogger(TestOpenOrangeHRMDemo.class);

        logger.info("Init ChromeDriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @AfterAll
    static void quit() {
        logger.info("Prepare close session");
        chromeDriver.quit();
        logger.info("Session is closed");
    }

    @Test
    void testOpenPage() {
        logger.info("Prepare access to Web");
        chromeDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Assertions.assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", chromeDriver.getCurrentUrl());
        Assertions.assertEquals("OrangeHRM", chromeDriver.getTitle());
        logger.info("Accessed to Web");
    }
}
