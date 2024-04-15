package org.projeto.data.repositories.users;

import org.springframework.stereotype.Repository;
import org.projeto.data.entities.users.Engineer;

@Repository
public interface EngineerRepository extends UserRepository<Engineer>{
}
