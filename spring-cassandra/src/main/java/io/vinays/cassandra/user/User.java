package io.vinays.cassandra.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @PrimaryKey
    private @NonNull UUID id = UUID.randomUUID();
    private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull String email;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
