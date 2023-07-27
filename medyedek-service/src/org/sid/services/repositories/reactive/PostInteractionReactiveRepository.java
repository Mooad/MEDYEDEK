package org.sid.services.repositories.reactive;
import io.r2dbc.spi.Row;


import lombok.extern.slf4j.Slf4j;
import org.sid.services.dto.interaction.PostInteractionDto;
import org.sid.services.entities.Postinteraction;
import org.sid.services.enumeration.InteractionType;
import org.sid.services.mappers.PostInteractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PostInteractionReactiveRepository {

    private final DatabaseClient databaseClient;

    @Autowired
    public PostInteractionReactiveRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Mono<PostInteractionDto> getLikePostInteractionWithUserId(Integer postId, Integer userId) {

        return databaseClient.sql("SELECT * FROM postinteraction WHERE post_id = " + postId +
                        " AND user_id =" + userId + " AND (interaction_type = 0 OR interaction_type = 1)")
                .map((row, rowMetadata) -> mapRowToPostInteraction(row)).first().map(
                        (PostInteractionMapper::entityToDto));

    }

    public Mono<PostInteractionDto> deletePostInteraction(Integer postId, Integer userId) {

        return databaseClient.sql("delete FROM postinteraction WHERE post_id = " + postId +
                        " AND user_id =" + userId + " AND (interaction_type = 0 OR interaction_type = 1)")

                .map((row, rowMetadata) -> mapRowToPostInteraction(row)).first().map(
                        (PostInteractionMapper::entityToDto));

    }

    private Postinteraction mapRowToPostInteraction(Row row) {
        // Map the row data to a PostInteraction object
        // Example:
        return Postinteraction.builder()
                .id(row.get("id", Integer.class))
                .post_id(row.get("post_id", Integer.class))
                .interaction_type(InteractionType.values()[row.get("interaction_type", Integer.class)])
                .comment(row.get("comment", String.class))
                // Map other fields from the 'user' table
                .user_id(row.get("user_id", Integer.class))
                .build();
        // Map other columns as needed
    }

    public void savePostInteraction(Postinteraction postinteraction) {

        //**        R2dbcEntityTemplate template = new R2dbcEntityTemplate(databaseClient.getConnectionFactory());
        // template.insert(Post.class)
        //          .using(post)
        //          .as(postMono -> postMono ).subscribe();
        int postInteractionType = postinteraction.interaction_type.ordinal();
        if (InteractionType.LIKE.ordinal() == postInteractionType)
            databaseClient.sql("insert into medyedek.postinteraction (post_id, user_id, interactionType, comment, interaction_type)" +
                            " values (" + postinteraction.getPost_id() +
                            "," + postinteraction.getUser_id() +
                            "," + postInteractionType +
                            ",'" + postinteraction.comment +
                            "'," + postInteractionType + ")")
                    .fetch().first().subscribe();
        {

        }

    }


}
