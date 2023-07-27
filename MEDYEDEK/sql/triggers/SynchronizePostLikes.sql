CREATE TRIGGER after_insert_postinteraction
    AFTER INSERT ON postinteraction
    FOR EACH ROW
BEGIN
    UPDATE post
    SET likesnb = likesnb + 1
    WHERE post_id = NEW.post_id;
END;


CREATE TRIGGER after_delete_postinteraction
    AFTER DELETE ON postinteraction
    FOR EACH ROW
BEGIN
    UPDATE post
    SET likesnb = likesnb - 1
    WHERE post_id = OLD.post_id;
END;


DELIMITER ;



SELECT TRIGGER_NAME, EVENT_OBJECT_TABLE, ACTION_TIMING, ACTION_STATEMENT
FROM information_schema.TRIGGERS
WHERE TRIGGER_SCHEMA = 'your_database_name';