package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.User;
import com.theironyard.services.LocationRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.PostingRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IronJobsApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostingRepository postingRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}


	@Test
	public void	createUser() throws Exception {
		User user = new User();
		user.setUsername("Test_Username");
		user.setPassword(PasswordStorage.createHash("Test_Password"));

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/users").content(json).contentType("application/json")
		);
		assertTrue(userRepository.count() == 1);
	}

}
//package com.theironyard;
//
//import com.theironyard.services.LocationRepository;
//import com.theironyard.services.MessageRepository;
//import com.theironyard.services.PostingRepository;
//import com.theironyard.services.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.context.WebApplicationContext;
//
//import static junit.framework.TestCase.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class IronJobsApplicationTests {
//
//	@Autowired
//	WebApplicationContext wap;
//
//	@Autowired
//	LocationRepository locationRepository;
//
//	@Autowired
//	MessageRepository messageRepository;
//
//	@Autowired
//	PostingRepository postingRepository;
//
//	@Autowired
//	UserRepository userRepository;
//
	//MockMvc mockMvc;
//
//	@Test
//	public void testLogin() throws Exception {
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/users")
//						.param("username", "TestUser")
//						.param("password", "password")
//
//		).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//		assertTrue(userRepository.count() == 1);
//	}
//
//
//
//}
