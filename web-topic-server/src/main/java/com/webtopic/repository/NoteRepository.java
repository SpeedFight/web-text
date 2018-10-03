package com.webtopic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtopic.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	// TODO test
//	@Query("select * FROM db_web_note.Note where fk_notes= :topicId")
//	List<Note> findByTopic(@Param("topicId") int topicId);
}
