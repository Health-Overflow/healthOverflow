package com.healthoverflow.healthOverflow.infrastructure;

import com.healthoverflow.healthOverflow.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
