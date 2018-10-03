package com.webtopic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtopic.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
