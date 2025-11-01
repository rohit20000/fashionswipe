package com.fashionswipe.repository;

import com.fashionswipe.model.Swipe;
import com.fashionswipe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    List<Swipe> findByUser(User user);
    
    @Query("SELECT s.product.id FROM Swipe s WHERE s.user.id = :userId")
    List<Long> findProductIdsSwipedByUser(Long userId);
}
