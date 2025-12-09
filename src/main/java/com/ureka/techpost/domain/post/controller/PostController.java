package com.ureka.techpost.domain.post.controller;

import com.ureka.techpost.domain.auth.dto.CustomUserDetails;
import com.ureka.techpost.domain.post.dto.PostResponseDTO;
import com.ureka.techpost.domain.post.dto.PostRequestDTO;
import com.ureka.techpost.domain.post.service.PostService;
import com.ureka.techpost.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @file PostController.java
 @author 최승언
 @version 1.0
 @since 2025-12-09
 @description 게시글 관련 API 요청(생성, 조회, 검색, 삭제)을 받아 서비스 계층으로 전달하고 응답을 반환하는 컨트롤러 클래스입니다.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ApiResponse<String> createPost(@RequestBody PostRequestDTO postRequestDTO,
                                          @AuthenticationPrincipal CustomUserDetails userDetails){

        postService.save(postRequestDTO, userDetails);

        return ApiResponse.onSuccess("게시글 등록 성공");
    }

    // 게시물 목록 가져오기 & 게시물 검색해서 목록 가져오기
    @GetMapping("")
    public  ApiResponse<Page<PostResponseDTO>> searchPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String publisher,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable
    ){
        return ApiResponse.onSuccess(postService.search(keyword, publisher, pageable));
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDTO> getPost(@PathVariable Long postId){

        return ApiResponse.onSuccess(postService.findById(postId));
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId,
                                          @AuthenticationPrincipal CustomUserDetails userDetails){

        postService.deletePost(postId, userDetails);

        return ApiResponse.onSuccess("게시물 삭제 성공");
    }

}
