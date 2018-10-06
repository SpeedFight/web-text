package com.webtopic.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtopic.dto.TopicDto;
import com.webtopic.entity.Topic;
import com.webtopic.error.TopicNotFoundException;
import com.webtopic.repository.TopicRepository;
import com.webtopic.response.CustomResponseEntity;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestTopicController {

	@Autowired
	private TopicRepository topicRepository;

	@GetMapping("/topic/{topicId}")
	public TopicDto getTopic(@PathVariable int topicId) {

		Optional<Topic> topic = topicRepository.findById(topicId);

		if (!topic.isPresent()) {
			throw new TopicNotFoundException("Topic: " + topicId + " not found");
		}

		return new TopicDto(topic.get());
	}

	@GetMapping("/topic")
	public List<Topic> getAllTopic() {
		return topicRepository.findAll();
	}

	@PostMapping("/topic")
	public ResponseEntity<?> addTopic(@RequestBody Topic newTopic) {
		newTopic.setId(0);
		newTopic.setTimestamp(new Date());

		if (topicRepository.save(newTopic) != null) {
			return new CustomResponseEntity("Topic created", HttpStatus.CREATED);
		} else {
			return new CustomResponseEntity("Topic not created", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/topic")
	public void updateTopic(@RequestBody Topic updateTopic) {

		if (topicRepository.save(updateTopic) == null) {
			throw new TopicNotFoundException("Topic to update not exist");
		}

	}

	@DeleteMapping("/topic/{topicId}")
	public void deleteTopic(@PathVariable int topicId) {
		Optional<Topic> topic = topicRepository.findById(topicId);

		if (!topic.isPresent()) {
			throw new TopicNotFoundException("Topic: " + topicId + " not found");
		}

		topicRepository.deleteById(topicId);
	}
}
