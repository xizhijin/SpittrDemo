package spittr.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.web.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomeMethod() throws Exception {
		HomeController homeController = new HomeController();
		assertEquals("home", homeController.home());
	}
	
	@Test
	public void testHomePage() throws Exception {
		HomeController homeController = new HomeController();
		MockMvc mockMvc = standaloneSetup(homeController).build();
		mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
	}
	
	
}
