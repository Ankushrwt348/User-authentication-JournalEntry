package com.ankush.project1.Repo;

import com.ankush.project1.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepo extends MongoRepository<User, ObjectId> {
     User findByUsername(String username);
     void deleteByUsername(String username);
}
