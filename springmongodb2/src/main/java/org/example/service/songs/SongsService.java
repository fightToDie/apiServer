package org.example.service.songs;

import lombok.RequiredArgsConstructor;

import org.example.controller.dto.SongsListResponseDto;
import org.example.controller.dto.SongsResponseDto;
import org.example.domain.songs.DatabaseSequence;
import org.example.domain.songs.Songs;
import org.example.domain.songs.SongsRepository;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SongsService {

    private final SongsRepository songsRepository;

    @Transactional(readOnly = true)
    public SongsResponseDto findByPid(String pid) {
        SongsResponseDto document = songsRepository.findByPid(pid);
        return document;
    }
    
    public List<SongsListResponseDto> findByPidIn(List<String> pids) {
    	return songsRepository.findByPidIn(pids).stream()
                .map(SongsListResponseDto::new)
                .collect(Collectors.toList());
    }
    
    // 글 목록
    @Transactional(readOnly = true)
    public List<SongsListResponseDto> findAll() {
        return songsRepository.findAll().stream()
                .map(SongsListResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
    @Transactional(readOnly = true)
    - 조회 기능만 남겨두어, 조회 속도가 개선되도록 한다.

    public List<PostListResponseDto> findAll()
    .map(PostsListResponseDto::new)
    .collect(Collectors.toList());
    - postsRepository의 결과인 Posts의 stream을 map을 통해 변환한다.
     */
}