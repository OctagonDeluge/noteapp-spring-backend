package com.example.application.controller;

import com.example.application.entity.Note;
import com.example.application.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @PostMapping("/save")
    public String save(@RequestBody Note note) {
        noteService.saveNote(note);
        return "Zaebis";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Note note) {
        noteService.update(id, note);
        return "updated";
    }

    @GetMapping("/last")
    public Note getOne() {
        noteService.getLastAddedNote();
        return noteService.getLastAddedNote();
    }
}
