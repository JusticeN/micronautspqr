package com.nanhou.service;

import com.nanhou.domain.Note;
import com.nanhou.service.dto.NoteDto;
import org.graalvm.polyglot.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class NoteService {
    private static final String JS = "js";
    private static Logger log = LoggerFactory.getLogger(NoteService.class);
    private final Map<String, Note> noteStore = new ConcurrentHashMap<>();
    private final Context graalContext;

    public NoteService(Context graalContext) {
        this.graalContext = graalContext;
    }

    public Optional<Note> findById(String id) {
        log.debug("read single note with id {}", id);
        return Optional.ofNullable(noteStore.get(id));
    }

    public Note createNewNote(NoteDto note) {
        Note domain = new Note(note.getTitle(), note.getDescription());
        log.debug("create new note {}", domain);
        // call python code
        graalContext.getBindings(JS).putMember("data", note.getDescription());
        var result = graalContext.eval(JS, "data + ' added js code'");
        domain.setDescription(result.asString());
        noteStore.put(domain.getId(), domain);
        return noteStore.get(domain.getId());
    }

    public Collection<Note> allNotes() {
        log.debug("read all notes");
        return noteStore.values();
    }

    public Note updateNote(String id, NoteDto dto) {
        if (noteStore.containsKey(id)) {
            log.debug("update note id {}", id);
            var note = noteStore.get(id);
            note.setDescription(dto.getDescription());
            note.setTitle(dto.getTitle());
            noteStore.replace(id, note);
            return note;
        }
        log.debug("note id {} unknown", id);
        throw new RuntimeException("Note with id:" + id + "doesn't exists");
    }

    public void reset() {
        noteStore.clear();
    }
}
