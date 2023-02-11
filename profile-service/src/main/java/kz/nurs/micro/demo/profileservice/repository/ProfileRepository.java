package kz.nurs.micro.demo.profileservice.repository;

import kz.nurs.micro.demo.profileservice.model.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
