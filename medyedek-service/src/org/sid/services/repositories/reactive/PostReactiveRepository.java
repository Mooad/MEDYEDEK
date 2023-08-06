package org.sid.services.repositories.reactive;

import io.r2dbc.spi.Row;
import lombok.extern.slf4j.Slf4j;
import org.sid.services.dto.post.PostDto;
import org.sid.services.entities.Post;
import org.sid.services.entities.Typepost;
import org.sid.services.entities.User;
import org.sid.services.mappers.PostMapper;
import org.sid.services.repositories.noreactive.IPostCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PostReactiveRepository {



    private final DatabaseClient databaseClient;

    @Autowired
    PostMapper postMapper;
    @Autowired
    ContentReactiveRepository contentReactiveRepository;
    @Autowired
    IPostCrudRepository iPostCrudRepository;

    public PostReactiveRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }


    public Flux<PostDto> getAllPosts() {
        return databaseClient.sql("SELECT * FROM post p  inner join user u on p.user = u.user_id")
                .map((row, rowMetadata) -> mapRowToPost(row)).all()
                .map(post -> postMapper.posttoDto(post));
    }

    public Mono<Post> getPostById(int post_id) {
        return databaseClient.sql("SELECT * FROM post p  inner join user u inner join typepost t on p.id_typepost = t.id_typepost where p.post_id = "+ post_id)
                .map((row, rowMetadata) -> mapRowToPost(row)).one();
    }


    private Post mapRowToPost(Row row) {
        // Map the row data to a PostInteraction object
        // Example:
        return Post.builder()
                .post_id(row.get("post_id", Integer.class))
                .textContent(row.get("text_Content", String.class))
                .likesNB(row.get("likesNB", Integer.class))
                .commentsNB(row.get("commentsNB", Integer.class))
                .sharesNB(row.get("sharesNB", Integer.class))
                .commentContent(row.get("commentContent", String.class))
                .typepost(Typepost.builder()
                        .id_typepost(row.get("id_typepost", Integer.class))
                        // Map other fields from the 'role' table
                        .build())
                // Map other fields from the 'user' table
                .user(User.builder()
                        .user_id(row.get("user", Integer.class))
                                .image(row.get("image", String.class))
                        // Map other field   s from the 'role' table
                        .build())
                .build();
        // Map other columns as needed
    }

    public void saveCommentGrappeToComment(int post_id ,String id_comment_grappe) {

        databaseClient.sql("update medyedek.post set commentContent = '" + id_comment_grappe + " ' where post_id = " + post_id)
                .fetch().first().subscribe();

    }

    public void savePost(Post post) {

        //**        R2dbcEntityTemplate template = new R2dbcEntityTemplate(databaseClient.getConnectionFactory());
        // template.insert(Post.class)
        //          .using(post)
        //          .as(postMono -> postMono ).subscribe();

        databaseClient.sql("insert into medyedek.post (post_id, text_content, id_typepost, user, commentsnb, likesnb, sharesnb) values (" + post.post_id + ",'" + post.textContent + "'," + post.typepost.getId_typepost()
                        + "," + post.user.user_id + "," + post.commentsNB + "," + post.likesNB + "," + post.sharesNB + ")")
                .fetch().first().doOnError(throwable -> {
                    if(throwable instanceof DuplicateKeyException)
                    databaseClient.sql("update medyedek.post set text_content = '" + post.textContent + " ' , likesnb = " + post.likesNB + " , sharesNB = " + post.sharesNB + " where post_id = " + post.getPost_id())
                            .fetch().first().subscribe();
                }).subscribe();


    }



}
