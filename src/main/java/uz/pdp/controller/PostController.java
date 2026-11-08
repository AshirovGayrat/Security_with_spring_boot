package uz.pdp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.config.security.CustomUserDetails;
import uz.pdp.dto.PostDTO;
import uz.pdp.entity.AuthUser;
import uz.pdp.entity.Post;
import uz.pdp.enums.Status;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.repository.PostRepository;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final AuthUserRepository authUserRepository;
    private final PostRepository postRepository;

    public PostController(AuthUserRepository authUserRepository, PostRepository postRepository) {
        this.authUserRepository = authUserRepository;
        this.postRepository = postRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDTO dto, Principal principal) {

        System.out.println( principal.getName() );

        AuthUser authUser = authUserRepository.findAuthUserByUsername(principal.getName()).get();

        if ( authUser.getStatus().equals( Status.BLOCK ) ) {
            throw new RuntimeException("User is blocked");
        }

        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setUserId( authUser.getId() );

        postRepository.save(post);

        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }









}
