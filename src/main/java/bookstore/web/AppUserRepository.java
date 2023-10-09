package bookstore.web;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}
