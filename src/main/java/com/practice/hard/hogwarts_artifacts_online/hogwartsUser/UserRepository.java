package com.practice.hard.hogwarts_artifacts_online.hogwartsUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<HogwartsUser, Integer> {
}
