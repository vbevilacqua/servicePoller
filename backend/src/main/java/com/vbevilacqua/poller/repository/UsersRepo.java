package com.vbevilacqua.poller.repository;

import com.vbevilacqua.poller.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<UsersModel, Long> {
}
