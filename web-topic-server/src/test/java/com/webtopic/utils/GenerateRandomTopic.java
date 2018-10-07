package com.webtopic.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.webtopic.entity.Note;
import com.webtopic.entity.Topic;

import net.bytebuddy.utility.RandomString;

public class GenerateRandomTopic {
	
	private static int noteId;
	
	private GenerateRandomTopic() {};
	
	public static List<Topic> generate(int topicsAmount) {
		
		List<Topic> topics = new ArrayList<>();

		//generate random topics
		Date randDate = new Date();
		for (int i = 0; i < topicsAmount; i++) {
			randDate = new Date(randDate.getTime() + new Random().nextLong());
			topics.add(new Topic(i, new Date(), new ArrayList<>(), generateRandomString(5, 15)));
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

		return new Note(noteId++, new Date(), authors.get(new Random().nextInt(authors.size())),
				generateRandomString(5,15));
	}
	
	private static String generateRandomString(int minLength, int MaxLenght) {
		return RandomString.make(new Random().nextInt(MaxLenght) + minLength);
	}

}
