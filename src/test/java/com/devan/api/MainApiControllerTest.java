package com.devan.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class MainApiControllerTest {

//    @Autowired WebApplicationContext wac;
//    @Autowired MockHttpSession session;
//    @Autowired MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateServer() throws Exception {

    }

    @Test
    public void testDestroyServer() throws Exception {

    }

    @Test
    public void testExecuteServer() throws Exception {

    }

    @Test
    public void testGetAllServers() throws Exception {

    }

    @Test
    public void testGetServer() throws Exception {

    }

    @Test
    public void testGetSupportedGames() throws Exception {

    }
}