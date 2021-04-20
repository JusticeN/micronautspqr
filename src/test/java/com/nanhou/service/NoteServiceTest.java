package com.nanhou.service;

import com.nanhou.service.dto.NoteDto;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class NoteServiceTest {

    @Inject
    NoteService noteService;

    @AfterEach
    void tearDown() {
        noteService.reset();
    }

    @Test
    void createNewNote() {
        Assertions.assertEquals(0, noteService.allNotes().size());
        var title1 = "title1";
        var description1 = "description1";
        var noteDto = new NoteDto(title1, description1);
        var createdNote = noteService.createNewNote(noteDto);
        Assertions.assertNotNull(createdNote.getId());
        Assertions.assertEquals(title1, createdNote.getTitle());
        var expectedDescription1 = description1 + " added js code";
        Assertions.assertEquals(expectedDescription1, createdNote.getDescription());
        Assertions.assertEquals(1, noteService.allNotes().size());
    }
}