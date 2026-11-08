package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}