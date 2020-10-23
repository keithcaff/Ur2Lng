package com.keithcaff.Ur2Lng.repository;

import com.keithcaff.Ur2Lng.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByLongUrl(String url);

}