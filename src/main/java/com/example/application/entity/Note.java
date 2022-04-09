package com.example.application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "notes_id_seq",
            allocationSize = 1
    )
    private long id;
    private String caption;
    private String content;
}
