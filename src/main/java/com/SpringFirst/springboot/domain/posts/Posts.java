package com.SpringFirst.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //클래스 내 모든 필드의 Getter메소드를 자동 생성
@NoArgsConstructor //기본 생성자 자동 추가. public Posts() {}와 같은 효과
@Entity //테이블링과 링크될 클래스임을 나타냄.
public class Posts {
    @Id //해당 테이블의  pk필드.
    //pk의 생성 규칙.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //칼럼 생성.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 필더 패턴 클래스를 생성.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
