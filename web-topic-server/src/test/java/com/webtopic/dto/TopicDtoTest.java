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

import net.bytebuddy.utility.RandomString;

public class TopicDtoTest {

	private static List<Topic> topics;

	@BeforeClass
	public static void initTopic() {
		topics = generateRandomTopics(5);
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

	private static List<Topic> generateRandomTopics(int topicsAmount) {
		
		List<Topic> topics = new ArrayList<>();

		//generate random topics
		Date randDate = new Date();
		for (int i = 0; i < topicsAmount; i++) {
			randDate = new Date(randDate.getTime() + new Random().nextLong());
			topics.add(new Topic(0, new Date(), new ArrayList<>(), generateRandomString(5, 15)));
		}

		//fill each topic with random notes
		topics.forEach(e -> {
			for (int i = 0; i < 10; i++) {
				Note randomNote = getRandomNote();
				e.getNotes().add(randomNote);
			}
		});

		return topics;
	}

	private static Note getRandomNote() {

		List<String> authors = new ArrayList<>(
				Arrays.asList("Konoha", "Yuki", "Yukari", "Zurucha", "Inori", "Niko", "Maki"));

		return new Note(0, new Date(), authors.get(new Random().nextInt(authors.size())),
				generateRandomString(5,15));
	}
	
	private static String generateRandomString(int minLength, int MaxLenght) {
		return RandomString.make(new Random().nextInt(MaxLenght) + minLength);
	}
}
