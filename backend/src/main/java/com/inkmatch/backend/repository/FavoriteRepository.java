package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByCustomerId(Long customerId);

    Optional<Favorite> findByCustomerIdAndArtistId(Long customerId, Long artistId);
}
