package com.brotech.Auth.repo;

import com.brotech.Auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    boolean existsByEmail(String email);

}
