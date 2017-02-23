package com.hueemulator.server.handlers;

import com.hueemulator.emulator.HttpTester;
import com.hueemulator.emulator.TestEmulator;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;


abstract class TestBase {

    protected TestEmulator testEmulator;
    protected HttpTester httpTester;
    protected String fileName = "/config-2bulbs.json";
    protected String baseURL;

    @Before
    public void setUp() throws IOException {
        testEmulator = TestEmulator.getInstanceOnRandomPort();
        // Only start the Emulator/Server once for all tests.
        if (!testEmulator.isServerRunning()) {
            testEmulator.startEmulator(true);
        }

        httpTester = new HttpTester();

        // Tests should be stateless.  Reload initial config before running each test.
        testEmulator.reloadInitialConfig();
        baseURL = testEmulator.getBaseUrl();
    }

    @After
    public void tearDown() {
        testEmulator.stopEmulator();
    }

}

