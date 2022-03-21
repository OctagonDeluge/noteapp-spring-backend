package com.example.application.service;

import com.example.application.entity.Note;
import com.example.application.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public ResponseEntity<Note> saveNote(Note note) throws URISyntaxException {
        Note savedNote = noteRepository.save(note);
        return ResponseEntity.created(new URI("/notes/"+savedNote.getId())).body(savedNote);
    }

    public ResponseEntity<String> deleteNote(Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<Note> update(Long id, Note note) {
        Note updatedNote = noteRepository.findById(id).orElse(null);
        updatedNote.setId(note.getId());
        updatedNote.setCaption(note.getCaption());
        updatedNote.setContent(note.getContent());
        noteRepository.save(updatedNote);
        return ResponseEntity.ok(updatedNote);
    }

}
