package org.example.controller.dto;

import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

import org.example.domain.songs.Songs;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class SongsListResponseDto {

    private BigInteger id;
    @Field
    private String pid;
    private List<String> rec;

    public SongsListResponseDto(Songs document) {
        this.id = document.getId();
        this.pid = document.getPid();
        this.rec = document.getRec();
    }

	@Override
	public String toString() {
		return "SongsListResponseDto [id=" + id + ", pid=" + pid + ", rec=" + rec + "]";
	}
}