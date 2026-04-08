package org.example.repository;
import java.util.Optional;
import org.example.model.Group;

public interface GroupRepository {

    Optional<Group> findById(String id);
    void save(Group group);
    
} 
