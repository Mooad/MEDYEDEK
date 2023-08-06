package comments;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.sid.services.dto.comment.CommentDto;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.serviceproxy.services.CommentServiceLevel0;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCommentTest {

    private CommentServiceLevel0 commentService;

    @Before("")
    public void setUp() {
        // You can mock any dependencies here, if needed
    }

    @Test
    public void testComment() {
        // Given
        CommentDto postCommentsInput = createSamplePostComments();
        // You can create the PostComments object with the necessary data

        // When
        Mono<CommentsGrappes> result = commentService.commentPost(postCommentsInput);

        // Then
        // Assert the expected result based on your implementation
        // You can use assertEquals or other assert methods depending on your data structure
        // For example:`
        assertEquals(postCommentsInput, result);
    }

    @Test
    public void testSearchPostComments() {
        // Given
        SearchCommentDto identifier  = new SearchCommentDto();
        identifier.setIdentifier("64b28107fb4e5e4fb635a984");
        // You can define the identifier based on your test data

        // When
        Mono<CommentsGrappes>  result = commentService.searchPostComments(identifier);

        // Then
        // Assert the expected result based on your implementation
        // For example:
      //  assertEquals(identifier, result.getId());
    }

    private CommentDto createSamplePostComments() {
        // Create and return a sample PostComments object with necessary data for testing
        // You can use mock data or create real instances as needed
        return new CommentDto(/* Pass the required data here */);
    }
}
