package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.controller.dto.SongsListResponseDto;
import org.example.controller.dto.SongsResponseDto;
import org.example.service.songs.SongsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SongsController {
	
	private final SongsService songsService; 
	
	@GetMapping("/songs")
    public List<SongsListResponseDto> findAll() {
        return songsService.findAll();
    }
	
	//http://localhost:8080/songs/batch/3RqR44ne7N3ORaviV3TZl5
	@GetMapping("/songs/{pid}")
	public SongsResponseDto findByPid(@PathVariable String pid) {
		SongsResponseDto document = songsService.findByPid(pid);
		return document;
	}
	
	//http://localhost:8080/songs/batch/3RqR44ne7N3ORaviV3TZl5,3RpNWHT2fu4uIPsCUTmrUA
//	@GetMapping("/songs/batch/{pids}")
//	public List<SongsResponseDto> findByPids(@PathVariable List<String> pids) {
//	    return songsService.findByPidIn(pids);
//	}
	
	@GetMapping("/songs/batch/{pids}")
	public List<SongsListResponseDto> findByPids(@PathVariable List<String> pids) {
		List<SongsListResponseDto> arr = songsService.findByPidIn(pids);
		
		for(SongsListResponseDto dto : arr) {
			System.out.println(dto);
		}
		
	    return songsService.findByPidIn(pids);
	}
}