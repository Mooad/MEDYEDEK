package org.sid.serviceproxy;

import org.sid.dto.post.PostDto;
import org.sid.dto.user.LastPost;
import org.sid.entities.Post;
import org.sid.mappers.PostMapper;
import org.sid.repositories.*;
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
    public List<PostDto> getAllPosts(LastPost lastPost) {

        List<Post> postsList=null;
        if (lastPost == null || lastPost.isLastPost=="") {
            postsList = postsRepo.findAll();
        }
        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : postsList) {
            PostDto postDto = postMapper.PosttoDto(p);

            postDto.postContent =  contentRepository.getContentsOfPost(p.getId_post());
            postDtos.add(postDto);
        }
        return postDtos;
    }
}
