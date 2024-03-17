package com.example.chatroomskafkabackenddatabasewordcount.dao;

import com.example.chatroomskafkabackenddatabasewordcount.pojo.WordCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordCountRepository extends JpaRepository<WordCount, String> {
}
