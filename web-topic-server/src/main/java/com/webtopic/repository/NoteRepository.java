package com.webtopic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtopic.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}
