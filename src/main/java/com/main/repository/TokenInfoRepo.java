package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.TokenInfo;


@Repository
public interface TokenInfoRepo extends JpaRepository<TokenInfo,Long>{

	TokenInfo findByRefreshToken(String name);

}
