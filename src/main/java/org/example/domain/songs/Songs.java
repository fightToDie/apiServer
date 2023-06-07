package org.example.domain.songs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "playlist")
public class Songs {
	@Transient
    public static final String SEQUENCE_NAME = "songs_sequence";

    @Id
    private BigInteger id;
    @Field
    private String pid;
    private List<String> rec;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setPid(String pid) {
    	this.pid = pid;
    }

    @Builder
    public Songs(String pid, List<String> rec) {
        this.pid = pid;
        this.rec = rec;
    }

    public void update(String pid, List<String> rec) {
        this.pid = pid;
        this.rec = rec;
    }

    /*
    @Builder
    - 생성자 상단에 선언 시 생성자에 포함도니 필드만 빌더에 포함된다.
    - 데이터베이스에 데이터를 넣기 위한 Setter 메서드를 대체한다.
     */
}