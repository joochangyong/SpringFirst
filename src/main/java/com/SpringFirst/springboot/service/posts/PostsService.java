package com.SpringFirst.springboot.service.posts;

import com.SpringFirst.springboot.domain.posts.Posts;
import com.SpringFirst.springboot.domain.posts.PostsRepository;
import com.SpringFirst.springboot.web.dto.PostsResponseDto;
import com.SpringFirst.springboot.web.dto.PostsSaveRequestDto;
import com.SpringFirst.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    //update기능에서 디비에 쿼리를 날리는 부분이 없어도 가능한 이유는 JPA의 영속성 컨텍스트 때문. 영속성 컨텍스트랑 엔티티를 영구저장하는 환경.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}