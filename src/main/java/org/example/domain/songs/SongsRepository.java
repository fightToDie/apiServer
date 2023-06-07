package org.example.domain.songs;

import java.math.BigInteger;
import java.util.List;

import org.example.controller.dto.SongsResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SongsRepository extends MongoRepository<Songs, BigInteger> {
	@Query(value = "{'pid' : ?0}")
	SongsResponseDto findByPid(String pid);
	
//	@Query(value = "{'pid': { $in: ?0 }}")
//	List<SongsResponseDto> findByPidIn(List<String> pids);
	
	@Query(value = "{'pid': { $in: ?0 }}")
	List<Songs> findByPidIn(List<String> pids);
}