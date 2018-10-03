package com.webtopic.dto;

import java.util.Date;
import java.util.List;

import com.webtopic.entity.Note;
import com.webtopic.entity.Topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicDto {

	private int id;
	private Date timestamp;
	private List<Note> notes;
	private String description;

	public TopicDto(Topic source) {
		id = source.getId();
		timestamp = source.getTimestamp();
		notes = source.getNotes();
		description = source.getDescription();
	}

	public Topic toTopic() {
		return new Topic(id, timestamp, notes, description);
	}
}
