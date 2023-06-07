package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.controller.dto.SongsListResponseDto;
import org.example.controller.dto.SongsResponseDto;
import org.example.service.songs.SongsService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

class SongScore implements Comparable<SongScore> {
	String songId;
	double score;
	
	public SongScore(String songId, double score) {
		this.songId = songId;
		this.score = score;
	}

	@Override
	public String toString() {
		return "SongScore [songId=" + songId + ", score=" + score + "]";
	}

	@Override
	public int compareTo(SongScore o) {
		// TODO Auto-generated method stub
		if (this.score != o.score) return -Double.compare(this.score, o.score);
		else return this.songId.compareTo(o.songId);
		
	}	
}

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
	public List<String> findByPids(@PathVariable List<String> pids) {
		List<SongsListResponseDto> arr = songsService.findByPidIn(pids);
		Map<String, Double> map = new HashMap<>();
		
		for(SongsListResponseDto dto : arr) {
			List<String> recList = dto.getRec();
			
			for(int i = 0; i < recList.size(); i++) {
				String songId = recList.get(i);
				
				if (map.containsKey(songId)) {
					map.put(songId, map.get(songId) + ((double)(recList.size() - i)) / (recList.size()));
				}else {
					map.put(songId, ((double)(recList.size() - i)) / recList.size());
				}
			}
		}
		
		List<SongScore> scoreList = new ArrayList<>();
		List<String> recommendList = new ArrayList<>();
		
		for(Map.Entry<String, Double> entry : map.entrySet()) {
			scoreList.add(new SongScore(entry.getKey(), entry.getValue()));
		}
		
		Collections.sort(scoreList);

		for(int i = 0; i < Math.min(scoreList.size(), 100); i++) {
			recommendList.add(scoreList.get(i).songId);
		}
		
	    //return songsService.findByPidIn(pids);
		return recommendList;
	}
}