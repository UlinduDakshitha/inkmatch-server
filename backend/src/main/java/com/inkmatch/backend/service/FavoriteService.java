package com.inkmatch.backend.service;
import com.inkmatch.backend.entity.Favorite;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.FavoriteRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    public void toggleFavorite(Long userId, Long artistId){

        Optional<Favorite> existing =
                favoriteRepository.findByCustomerIdAndArtistId(userId, artistId);

        if(existing.isPresent()){
            favoriteRepository.delete(existing.get());
        } else {
            User customer = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            User artist = userRepository.findById(artistId)
                    .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

            Favorite fav = new Favorite();
            fav.setCustomer(customer);
            fav.setArtist(artist);
            favoriteRepository.save(fav);
        }
    }
}
