package com.example.demo.common.log;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.log.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

    Page<Log> findByClassNameAndState(String className, State state, Pageable pageable);
}
