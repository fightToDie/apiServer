package org.example.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

import org.example.domain.songs.Songs;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "playlist")
public class SongsResponseDto {
	@Id
    private BigInteger id;
	@Field
    private String pid;
	private List<String> rec;

    public SongsResponseDto(Songs document) {
        this.id = document.getId();
        this.pid = document.getPid();
        this.rec = document.getRec();
    }
}