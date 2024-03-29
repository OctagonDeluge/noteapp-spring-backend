package com.example.application.repository;

import com.example.application.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Override
    @Query("SELECT n FROM Note n WHERE n.user.email = ?#{principal?.username}")
    List<Note> findAll();

}
