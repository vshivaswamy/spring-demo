package io.vinays.cassandra.user;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {


    //Optional<User> findById(UUID id);
}
