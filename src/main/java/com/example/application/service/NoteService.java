package com.example.application.service;

import com.example.application.entity.Note;
import com.example.application.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Long id, Note note) {
        Note noteEntity = noteRepository.findById(id).orElse(null);
        noteEntity.setId(note.getId());
        noteEntity.setCaption(note.getCaption());
        noteEntity.setContent(note.getContent());
        noteRepository.save(noteEntity);
    }

    public Note getLastAddedNote() {
        return noteRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);
    }
}
