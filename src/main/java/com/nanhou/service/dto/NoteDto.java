package com.nanhou.service.dto;

public final class NoteDto {
    private final String title;
    private final String description;

    public NoteDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
