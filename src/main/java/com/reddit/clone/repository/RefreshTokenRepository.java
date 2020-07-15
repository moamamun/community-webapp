package com.reddit.clone.repository;

import com.reddit.clone.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

}
