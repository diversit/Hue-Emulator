package com.hueemulator.server.handlers;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.hueemulator.emulator.HttpTester;
import com.hueemulator.emulator.TestEmulator;
import com.hueemulator.lighting.utils.TestUtils;
import com.hueemulator.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestConfigurationAPI extends TestBase {

    @Test
    public void testBadJSON() throws Exception {
        String jsonToPut = "{badJson here . . \"lights\": [\"1\",\"2\"],\"name\": \"Test Group\"}";
        String response="";
        String expected="[{\"error\":{\"address\":\"/api/newdeveloper/groups\",\"description\":\"body contains invalid json\",\"type\":\"2\"}}]";
        String url = baseURL + "newdeveloper/groups";
        response = httpTester.doPutOrPost(url, jsonToPut, "POST");
        
        assertEquals(response, expected);
    }
    
    @Test
    public void testCreateUserAPI_4_1() throws Exception {
        // 2.4  Set group attributes
        System.out.println("Testing Create User 4.1.   (http://www.developers.meethue.com/documentation/configuration-api#71_create_user)" );
        String url = baseURL;
        String response="";
        String jsonToPut = "{\"devicetype\":\"test user\"}";
        String expected  = "[{\"error\":{\"address\":\"\",\"description\":\"link button not pressed\",\"type\":\"101\"}}]";
        
        response= httpTester.doPutOrPost(url, jsonToPut, "POST");
        assertTrue(TestUtils.jsonsArrayEqual(response, expected));       
    }
    
    @Test
    public void testRandomUsernames() {
        String userName = Utils.generateRandomUsername();        
        assertTrue(userName.length() == 31);
        System.out.println(userName);
    }
    
}
