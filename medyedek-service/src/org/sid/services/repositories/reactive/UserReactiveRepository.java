package org.sid.services.repositories.reactive;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.sid.services.dto.profile.ProfileDto;
import org.sid.services.entities.*;
import org.sid.services.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;


@Component
@Slf4j
public class UserReactiveRepository {

    private final DatabaseClient databaseClient;

    private final MappingR2dbcConverter converter;

    @Autowired
    private ProfileMapper profileMapper;


    @Autowired
    public UserReactiveRepository(DatabaseClient databaseClient, MappingR2dbcConverter converter) {
        this.databaseClient = databaseClient;
        this.converter = converter;
    }

    public static final BiFunction<Row, RowMetadata, User> MAPPING_FUNCTION = (row, rowMetadata) ->

            User.builder()
                    .user_id(row.get("user_id", Integer.class))
                    .email(row.get("email", String.class))
                    .firstname(row.get("firstname", String.class))
                    .lastname(row.get("lastname", String.class))
                    .cin(row.get("cin", String.class))
                    .phone_number(row.get("phone_number", String.class))
                    .isValidUser(row.get("is_valid_user", String.class))
                    //          .role(row.get("id_role", String.class))
                    //        .domaine(row.get("domaine_id", String.class))
                    //     .address(row.get("adresse_id", String.class))
                    .password(row.get("password", String.class))
                    .userToken(row.get("userToken", String.class))
                    .temp_pass(row.get("temp_pass", String.class)).build();


    public Mono<User> findByEmail(String email) {

        String query = "SELECT u.*, r.*, a.* ,d.* FROM user u " +
                "JOIN role r ON u.id_role = r.role_id " +
                " inner join " +
                " address a on a.adress_id = u.adresse_id inner join domaine d " +
                "on d.domaine_id = u.domaine_id WHERE u.email = " + "'" + email + "'" ;

        return databaseClient.sql(query)
                .map((row, rowMetadata) -> mapRowToUser(row)) // Map other columns as needed
                .one();
    }

    public Mono<ProfileDto> findProfileByEmail(String email) {
        String query = "SELECT u.*, r.*, a.* ,d.* FROM user u " +
                "JOIN role r ON u.id_role = r.role_id " +
                " inner join " +
                " address a on a.adress_id = u.adresse_id inner join domaine d " +
                "on d.domaine_id = u.domaine_id WHERE u.email = " + "'" + email + "'" ;

        return databaseClient.sql(query)
                .map((row, rowMetadata) -> mapRowToUser(row)).one().map(
                (user -> profileMapper.UtilisateurToProfileDto(user)));
    }



    public Mono<Void> validateUser(String token) {
        return databaseClient.sql("UPDATE user SET medyedek.user.is_valid_user = '01' WHERE medyedek.user.user_token = :token")
                .bind("token", token)
                .then();
    }

    public Mono<Void> updateTemporaryPassword(String email, String temp_password) {
        return databaseClient.sql("UPDATE user SET temp_pass = :temp_password WHERE email = :email")
                .bind("email", email)
                .bind("temp_password", temp_password)
                .then();
    }

    public Mono<User> findByToken(String token) {
        return databaseClient.sql("SELECT * FROM user WHERE user_token = :token")
                .bind("token", token)
                .map(MAPPING_FUNCTION)
                .one();
    }

    public Mono<User> findById(Integer user_id) {
        return databaseClient.sql("SELECT * FROM user WHERE user_id = :user_id")
                .bind("user_id", user_id)
                .map(MAPPING_FUNCTION)
                .one();
    }

    private User mapRowToUser(Row row) {
        return User.builder()
                .user_id(row.get("user_id", Integer.class))
                .firstname(row.get("firstname", String.class))
                .lastname(row.get("lastname", String.class))
                .email(row.get("email", String.class))
                .cin(row.get("cin", String.class))
                .phone_number(row.get("phone_number", String.class))
                .image(row.get("image", String.class))
                .pseudo(row.get("pseudo", String.class))
                .role(Role.builder()
                        .role_id(row.get("role_id", Integer.class))
                        .rolename(row.get("rolename", String.class))
                        // Map other fields from the 'role' table
                        .build())
                .address(Adress.builder()
                        .city(row.get("city", String.class))
                        .country(row.get("country", String.class))
                        .district(row.get("district", String.class))
                        .build())
                        .build();
                

    }

    public Mono<User> findByEmailWithRole(String email) {
        String query = "SELECT u.*, r.* FROM user u " +
                "JOIN role r ON u.id_role = r.role_id " +
                "WHERE u.email = " + "'" + email + "'";

        return databaseClient.sql(query)
                .map((row, metadata) -> {
                    User user = User.builder()
                            .user_id(row.get("user_id", Integer.class))
                            .firstname(row.get("firstname", String.class))
                            .password(row.get("password", String.class))
                            .lastname(row.get("lastname", String.class))
                            .email(row.get("email", String.class))
                            // Map other fields from the 'user' table
                            .role(Role.builder()
                                    .role_id(row.get("role_id", Integer.class))
                                    .rolename(row.get("rolename", String.class))
                                    // Map other fields from the 'role' table
                                    .build())
                            .build();

                    return user;
                })
                .one();
    }

}
