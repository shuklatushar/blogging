package com.blogging.blogging.services.impl;

import com.blogging.blogging.entity.Category;
import com.blogging.blogging.entity.Comments;
import com.blogging.blogging.entity.Post;
import com.blogging.blogging.entity.User;
import com.blogging.blogging.exceptions.ResourceNotFoundException;
import com.blogging.blogging.payloads.*;
import com.blogging.blogging.repositories.CategoryRepo;
import com.blogging.blogging.repositories.PostRepo;
import com.blogging.blogging.repositories.UserRepo;
import com.blogging.blogging.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        postDto.setImageName("default.png");
        postDto.setAddedDate(new Date());
        Post post=toPost(postDto);
        post.setCategory(category);
        post.setUser(user);
        return toPostDto(postRepo.save(post));

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(post.getImageName());
        Post updatedPost=postRepo.save(post);
        return toPostDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
            Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
            postRepo.deleteById(postId);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {

        Sort sort=(sortOrder.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageRequest=PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> postPage=postRepo.findAll(pageRequest);
        List<Post> postList=postPage.getContent();
        List<PostDto> posts=postList.stream().map((post)->toPostDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(posts);
        postResponse.setLastPage(postPage.isLast());
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return toPostDto(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","CategoryId",categoryId));
        List<Post> posts=postRepo.findByCategory(category);
        List<PostDto> postDtoList=posts.stream().map((post)->toPostDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userID",userId));
        List<Post> posts=postRepo.findByUser(user);
        List<PostDto> postDtoList=posts.stream().map((post)->toPostDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts=postRepo.findByTitleContaining("%"+keyword+"%");
        List<PostDto> postDtos=posts.stream().map((post)->toPostDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public PostDto toPostDto(Post post){
        PostDto postDto=new PostDto();

        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
       postDto.setCategory(toCategoryDto(post.getCategory()));
       postDto.setUser(userTouserDto(post.getUser()));
        postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());
        List<CommentsDto> commentsDtos=post.getComments().stream().map((comments -> toCommentsDto(comments))).collect(Collectors.toList());
        postDto.setComments(commentsDtos);
        return postDto;
    }
    public Post toPost(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
//        post.setCategory(postDto.getCategoryDto());
//        post.setUser(postDto.getUserDto());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());
        return post;
    }
    private UserDto userTouserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }
    public CommentsDto toCommentsDto(Comments comments){
        CommentsDto commentsDto=new CommentsDto();
        commentsDto.setCommentId(comments.getCommentId());
        commentsDto.setContent(comments.getContent());
        return commentsDto;
    }
}
