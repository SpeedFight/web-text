package com.webtopic.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.collection.IsArrayContaining;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtopic.config.WebTopicApplication;
import com.webtopic.entity.Note;
import com.webtopic.entity.Topic;
import com.webtopic.repository.NoteRepository;
import com.webtopic.repository.TopicRepository;
import com.webtopic.utils.GenerateRandomTopic;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes=com.webtopic.config.WebTopicApplication.class)
//@ContextConfiguration(classes = com.webtopic.config.WebTopicApplication.class)
@WebMvcTest(RestTopicController.class)
public class RestTopicControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TopicRepository topicRepository;

	@MockBean
	private NoteRepository noteRepository;

	private static ObjectMapper mapper;

	@Configuration
	@ComponentScan(basePackageClasses = { RestTopicController.class })
	public static class TestConf {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapper = new ObjectMapper();
	}

	@Test
	public void getTopicList() throws Exception {
		assertEquals(0, 0);
		List<Topic> topics = GenerateRandomTopic.generate(5);
		String topicsJson = mapper.writeValueAsString(topics);
		
		when(topicRepository.findAll()).thenReturn(topics);
		
		this.mockMvc.perform(get("/api/topic")).andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(topicsJson));
	}

}
