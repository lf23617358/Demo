package com.example.demo.repository;

import com.example.demo.domain.DemoPo;
import org.springframework.data.repository.CrudRepository;

public interface DemoRepository extends CrudRepository<DemoPo, String> {
}
