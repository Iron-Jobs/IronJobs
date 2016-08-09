package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.Posting;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setUsername("Test_Username");
		user.setPassword(PasswordStorage.createHash("Test_Password"));

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/users").content(json).contentType("application/json").sessionAttr("user", user)
		);
		assertTrue(userRepository.count() == 1);
	}

	@Test
	public void getUser() throws Exception {
		User user = new User("Test", PasswordStorage.createHash("password"));
		userRepository.save(user);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users").requestAttr("token", user.getToken())
		);

		assertEquals(user, user);
	}

	@Test
	public void getAllUsers() throws Exception {
		User user1 = new User("Test1", PasswordStorage.createHash("password"));
		User user2 = new User("Test2", PasswordStorage.createHash("password"));
		userRepository.save(user1);
		userRepository.save(user2);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/all")
		);

		assertEquals(user1, user1);
		assertEquals(user2, user2);
	}


	@Test
	public void getUserPosting() throws Exception {

	}


	@Test
	public void addPosting() throws Exception {
		Posting posting = new Posting();
		posting.setTitle("TestPosting");
		posting.setDescription("TestDescription");
		posting.setSalaryStart(20000);
		posting.setSalaryEnd(40000);
		postingRepository.save(posting);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(posting);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/postings")
				.content(json)
				.contentType("application/json")
		);
		assertTrue(postingRepository.count() == 1);
	}

	@Test
	public void deletePosting() throws Exception {

		Posting posting = new Posting();
		posting.setTitle("TestPosting");
		posting.setDescription("TestDescription");
		posting.setSalaryStart(20000);
		posting.setSalaryEnd(40000);
		postingRepository.save(posting);

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/postings/" + posting.getId())
		);
		assertFalse(postingRepository.exists(posting.getId()));
	}

	@Test
	public void getSinglePosting() throws Exception {
		Posting posting = new Posting();
		posting.setTitle("TestPosting");
		posting.setDescription("TestDescription");
		posting.setSalaryStart(20000);
		posting.setSalaryEnd(40000);
		postingRepository.save(posting);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/postings/" + posting.getId())
		);
		assertEquals(posting, posting);
	}

}
