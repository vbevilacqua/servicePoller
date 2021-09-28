package com.vbevilacqua.poller.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sites")
public class PollerModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String url;
    private String name;
    private boolean alive;
}
