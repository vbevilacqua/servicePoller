package com.vbevilacqua.poller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "poller")
public class PollerModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String url;
    private String name;
    private boolean alive;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
