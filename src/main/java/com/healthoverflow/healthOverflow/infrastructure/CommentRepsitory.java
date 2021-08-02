package com.healthoverflow.healthOverflow.infrastructure;

import com.healthoverflow.healthOverflow.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepsitory extends JpaRepository<Comment,Long> {
}
