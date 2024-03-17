package com.example.chatroomskafkabackenddatabasewordcount.config;

import com.example.chatroomskafkabackenddatabasewordcount.dao.WordCountRepository;
import com.example.chatroomskafkabackenddatabasewordcount.pojo.WordCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ChatKafkaConsumerWordCountConfig {

    private final WordCountRepository wordCountRepository;

    @Bean
    public Consumer<KStream<String, Long>> wordCount() {
        return kStream -> kStream
                .foreach((key, value) -> {
                    WordCount currentWordCount = wordCountRepository.findById(key).orElse(new WordCount(key, 0L));
                    currentWordCount.setCount(currentWordCount.getCount() + value);
                    wordCountRepository.save(currentWordCount);
                });
    }
}


