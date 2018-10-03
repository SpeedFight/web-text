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

import com.webtopic.entity.Note;
import com.webtopic.entity.Topic;
import com.webtopic.error.TopicNotFoundException;
import com.webtopic.repository.NoteRepository;
import com.webtopic.repository.TopicRepository;
import com.webtopic.response.CustomResponseEntity;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestNoteController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private RestTopicController restTopicController;

	// TODO investigate if this method really necessary
	@GetMapping("/topic/{topicId}/note/{noteId}")
	public Note getNoteFromTopic(@PathVariable int topicId, @PathVariable int noteId) {

		Note note;
		try {
			note = restTopicController.getTopic(topicId).getNotes().get(noteId);
		} catch (IndexOutOfBoundsException e) {
			throw new TopicNotFoundException("Note: " + noteId + " not found");
		}

		return note;
	}

	@GetMapping("/topic/{topicId}/note")
	public List<Note> getAllNotesFromTopic(@PathVariable int topicId) {

		// TODO fix
//		return restTopicController.getTopic(topicId).getNotes();
//		return noteRepository.findByTopic(topicId);
		return null;
	}

	@PostMapping("/topic/{topicId}/note")
	public ResponseEntity<?> addNote(@PathVariable int topicId, @RequestBody Note newNote) {

		newNote.setTimestamp(new Date());
		newNote.setId(0);

		Optional<Topic> topicToUpdate = topicRepository.findById(topicId);
		if (!topicToUpdate.isPresent()) {
			throw new TopicNotFoundException("Topic: " + topicId + " not found");
		}

		topicToUpdate.get().getNotes().add(newNote);

		topicRepository.save(topicToUpdate.get());

		if (noteRepository.save(newNote) != null) {
			return new CustomResponseEntity("Note created", HttpStatus.CREATED);
		} else {
			return new CustomResponseEntity("Note not created", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/note/{noteId}")
	public void updateNote(@PathVariable int noteId, @RequestBody Note updatedNote) {

		if (noteRepository.save(updatedNote) == null) {
			throw new TopicNotFoundException("Note: " + noteId + " not exist");
		}

	}

	@DeleteMapping("/note/{noteId}")
	public void deleteNote(@PathVariable int noteId) {
		Optional<Note> note = noteRepository.findById(noteId);

		if (note.isPresent()) {
			throw new TopicNotFoundException("Note: " + noteId + " not exist");
		}

		noteRepository.delete(note.get());
	}
}
