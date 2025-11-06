package com.angelocvti.consumer.repository;

import com.angelocvti.consumer.model.LogEvent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogEventRepository extends ElasticsearchRepository<LogEvent, String> {}
