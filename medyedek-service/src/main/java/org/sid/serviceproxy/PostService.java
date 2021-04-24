package org.sid.serviceproxy;

import org.sid.dto.post.PostDto;
import org.sid.entities.Post;
import org.sid.mappers.PostMapper;
import org.sid.repositories.ContentRepository;
import org.sid.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostService {

    @Autowired
    PostsRepository postsRepo;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    PostMapper postMapper;

    /**
     * Class used to get all the postDto and return them to front End App
     * @return
     */
    public List<PostDto> getAllPosts() {

        List<Post> PostsList = postsRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : PostsList) {
            PostDto postDto = postMapper.PosttoDto(p);

            postDto.postContent =  contentRepository.getContentsOfPost(p.getId_post());
            postDtos.add(postDto);
        }
        return postDtos;
    }
}
