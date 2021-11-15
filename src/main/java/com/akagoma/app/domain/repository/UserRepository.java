package com.akagoma.app.domain.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.akagoma.app.domain.Dynamo;
import com.akagoma.app.domain.model.User;

@Repository
public class UserRepository {
    public void update(User user) {
        Dynamo.update(user, User.class);
    }

    public List<User> findAll() {
        return Dynamo.findAll(User.class);
    }
}
