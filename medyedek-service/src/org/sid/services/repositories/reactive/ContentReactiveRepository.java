package org.sid.services.repositories.reactive;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.services.entities.Content;
import org.sid.services.enumeration.ContentType;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;


@RequiredArgsConstructor
@Component
@Slf4j
public class ContentReactiveRepository {

    private final DatabaseClient databaseClient;

    public static final BiFunction<Row, RowMetadata, Content> MAPPING_FUNCTION = (row, rowMetaData) -> Content.builder()
            .id(row.get("id", Integer.class))
            .contentType(ContentType.getById(String.valueOf(row.get("contentType", Object.class))))
            .content(row.get("content", String.class))
            .post_id((Integer) row.get("post_id"))
            .build();

    public Flux<Content> getContentsOfPost(int postId) {
        return databaseClient.sql("SELECT * FROM content WHERE post_id =" + postId)
                .map(MAPPING_FUNCTION)
                .all();
    }
}
