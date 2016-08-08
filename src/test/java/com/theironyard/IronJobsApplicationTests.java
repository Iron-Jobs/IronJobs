//package com.theironyard;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.theironyard.entities.Location;
//import com.theironyard.entities.Posting;
//import com.theironyard.services.LocationRepository;
//import com.theironyard.services.MessageRepository;
//import com.theironyard.services.PostingRepository;
//import com.theironyard.services.UserRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static junit.framework.TestCase.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
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
//	MockMvc mockMvc;
//
//	@Before
//	public void before(){
//		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
//	}
//
//	@Test
//	public void addPosting() throws Exception {
//		Posting posting = new Posting();
//		posting.setTitle("TestPosting");
//		posting.setDescription("TestDescription");
//		posting.setSalaryStart(20000);
//		posting.setSalaryEnd(40000);
//		postingRepository.save(posting);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(posting);
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/postings")
//				.content(json)
//				.contentType("application/json")
//		);
//		assertTrue(postingRepository.count() == 1);
//	}
//
//	@Test
//	public void deletePosting() throws Exception {
//
//		Posting posting = new Posting();
//		posting.setTitle("TestPosting");
//		posting.setDescription("TestDescription");
//		posting.setSalaryStart(20000);
//		posting.setSalaryEnd(40000);
//		postingRepository.save(posting);
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.delete("/postings/" + posting.getId())
//		);
//		assertFalse(postingRepository.exists(posting.getId()));
//	}
//
//	@Test
//	public void getSinglePosting() throws Exception {
//		Posting posting = new Posting();
//		posting.setTitle("TestPosting");
//		posting.setDescription("TestDescription");
//		posting.setSalaryStart(20000);
//		posting.setSalaryEnd(40000);
//		postingRepository.save(posting);
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.get("/postings/" + posting.getId())
//		);
//		assertEquals(posting, posting);
//	}
//
//}
