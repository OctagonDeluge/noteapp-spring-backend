package com.example.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator-event"
    )
    @SequenceGenerator(
            name = "sequence-generator-event",
            sequenceName = "events_id_seq",
            allocationSize = 1
    )
    private Long id;
    private Date event_date;
    //private Long user_id;

    @OneToMany(mappedBy = "schedule")
    //@JsonManagedReference
    @JsonIgnore
    private List<Event> events;

    //@ManyToOne
    //private User user;
}
