package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {
	List<AccountInfo> findByEmail(String email);

	List<AccountInfo> findByPassword(String password);

	List<AccountInfo> findByEmailAndPassword(String email, String password);
}
