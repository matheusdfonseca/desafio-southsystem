package com.southsystem.voting.repository;

import com.southsystem.voting.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByIsClosedIsFalseAndAndTimeEndIsAfter(LocalDateTime now);
}
