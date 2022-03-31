package com.example.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator-sn"
    )
    @SequenceGenerator(
            name = "sequence-generator-sn",
            sequenceName = "sn_id_seq",
            allocationSize = 1
    )
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    //@JsonBackReference
    @JsonIgnore
    private Schedule schedule;
}
