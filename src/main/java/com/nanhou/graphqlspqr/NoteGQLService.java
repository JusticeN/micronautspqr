package com.nanhou.graphqlspqr;

import com.nanhou.domain.Note;
import com.nanhou.service.NoteService;
import com.nanhou.service.dto.NoteDto;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class NoteGQLService {

    private final NoteService noteService;

    public NoteGQLService(NoteService noteService) {
        this.noteService = noteService;
    }

    @GraphQLQuery
    public Note findNoteById(@GraphQLArgument(name = "id") String id) {
        return noteService.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @GraphQLQuery
    public Collection<Note> findAllNotes() {
        return noteService.allNotes();
    }

    @GraphQLMutation
    public Note createNote(@GraphQLNonNull NoteDto note) {
        return noteService.createNewNote(note);
    }

    @GraphQLMutation
    public Note updateNote(@GraphQLNonNull String id, @GraphQLNonNull NoteDto note) {
        return noteService.updateNote(id, note);
    }
}
