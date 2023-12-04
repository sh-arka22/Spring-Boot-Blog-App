package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "CRUD REST APIs for Comment Resource")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*
     * creating a comment
     */
    @Operation(summary = "Creating a comment by User", 
                description = "with the post-id and comment body a usr creates post on that post")
    @ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
    // @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    



    /*
     * getting all the comments
     */
    @Operation(summary = "Getting all the conmments on that post", 
        description = "Using post-Id I get all all the comments")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    // @SecurityRequirement(name = "Bear Authentication")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }







    /*
     * get a comment
     */
    @Operation(summary = "Getting a particular comment", 
            description = "I get the comment by providing the comment-Id and the post-Id")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    // @SecurityRequirement(name = "Bear Authentication")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }






    /*
     * updating a comment
     */
    @Operation(summary = "Uising the post-id and comment-Id and the comment bosy I update the post", 
                description = "Create Post REST API is used to save post into database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 UPDATED")
    // @SecurityRequirement(name = "Bear Authentication")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }


    



    /*
     * delete a comment
     */
    @Operation(summary = "Create Post REST API", 
                description = "Delete Post using the post-id and the user-id")
    @ApiResponse(responseCode = "200", description = "Http Status 200 DELETED")
    // @SecurityRequirement(name = "Bear Authentication")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
