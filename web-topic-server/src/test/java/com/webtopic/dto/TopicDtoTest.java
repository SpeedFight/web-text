package com.webtopic.dto;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.webtopic.entity.Note;
import com.webtopic.entity.Topic;
import com.webtopic.utils.GenerateRandomTopic;

import net.bytebuddy.utility.RandomString;

public class TopicDtoTest {

	private static List<Topic> topics;

	@BeforeClass
	public static void initTopic() {
		topics = GenerateRandomTopic.generate(5);
	}
	
	@Test
	public void smallDtoConversionTest() {
		long date = new Date().getTime();
		Topic topic1 = new Topic(7, new Date(date), null, "test");
		Topic topic2 = new Topic(7, new Date(date), null, "test");
		TopicDto topicDto = new TopicDto(topic1);
		
		assertTrue(topicDto.getId() == topic2.getId());
		assertTrue(topicDto.getTimestamp().compareTo(topic2.getTimestamp()) == 0);
		assertTrue(topicDto.getDescription().compareTo(topic2.getDescription()) == 0);	
	}
	
	@Test
	public void smallTopicConversionTest() {
		long date = new Date().getTime();
		Topic topic1 = new Topic(7, new Date(date), null, "test");
		TopicDto topicDto = new TopicDto(topic1);
		
		Topic topic2 = topicDto.toTopic();
		
		assertTrue(topic1.getId() == topic2.getId());
		assertTrue(topic1.getTimestamp().compareTo(topic2.getTimestamp()) == 0);
		assertTrue(topic1.getDescription().compareTo(topic2.getDescription()) == 0);
		
	}

	@Test
	public void toDtoConversion() {
		List<TopicDto> topicsDto = new ArrayList<>();
		
		topics.forEach(e -> topicsDto.add(new TopicDto(e)));
		
		TopicDto dtoTmp;
		Topic topicTmp;
		for(int i = 0; i < topicsDto.size(); ++i) {
			dtoTmp = topicsDto.get(i);
			topicTmp = topics.get(i);
			
			assertTrue(dtoTmp.getId() == topicTmp.getId());
			assertTrue(dtoTmp.getTimestamp().compareTo(topicTmp.getTimestamp()) == 0);
			assertTrue(dtoTmp.getDescription().compareTo(topicTmp.getDescription()) == 0);
			
			for (int j = 0; j < dtoTmp.getNotes().size(); j++) {
				Note dtoNoteTmp = dtoTmp.getNotes().get(j);
				Note topicNoteTmp = topicTmp.getNotes().get(j);
				
				assertTrue(dtoNoteTmp.getId() == topicNoteTmp.getId());
				assertTrue(dtoNoteTmp.getTimestamp().compareTo(topicNoteTmp.getTimestamp()) == 0);
				assertTrue(dtoNoteTmp.getAuthor().compareTo(topicNoteTmp.getAuthor()) == 0);
				assertTrue(dtoNoteTmp.getText().compareTo(topicNoteTmp.getText()) == 0);			
			}
		}
		
		
	}


}
