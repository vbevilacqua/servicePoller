package com.vbevilacqua.poller.repository;

import com.vbevilacqua.poller.model.PollerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollerRepo extends JpaRepository<PollerModel, Long> {
    PollerModel findByUrl(String url);
}
