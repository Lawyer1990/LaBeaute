package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Posts;
import com.example.LaBeaute.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/posts")
    public String showPosts(Model model) {
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("titlePosts", "Posts information");
        return "post/postPage";
    }

    @PostMapping("/posts/add")
    public String addPostPage(@RequestParam String newPost, Model model) {
        Posts posts = new Posts(newPost);
        postsRepository.save(posts);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/editPosts")
    public String doAdminEditPostPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Update Post Page");
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> arrayList = new ArrayList<>();
        post.ifPresent(arrayList::add);
        model.addAttribute("postId", arrayList);
        return "post/updatePostPage";
    }

    @PostMapping("/posts/{id}/editPosts")
    public String updatePostAdminPage(@PathVariable(value = "id") long id, @RequestParam String newPost, Model model) {
        Posts posts = postsRepository.findById(id).orElseThrow();
        posts.setPost(newPost);
        postsRepository.save(posts);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/removePosts")
    public String removePostAdminPage(@PathVariable(value = "id") long id, Model model) {
        Posts posts = postsRepository.findById(id).orElseThrow();
        postsRepository.delete(posts);
        return "redirect:/posts";
    }
}
