package com.example.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface PersonRepository extends ElasticsearchRepository<Person, String> {}