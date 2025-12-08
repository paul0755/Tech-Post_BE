package com.ureka.techpost.domain.auth.service;

import com.ureka.techpost.domain.auth.dto.SignupDto;
import com.ureka.techpost.domain.user.entity.User;
import com.ureka.techpost.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @file AuthController.java
 @author 김동혁, 구본문
 @version 1.0
 @since 2025-12-08
 @description 이 파일은 사용자 인증 관련 로직을 수행하는 클래스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signup(SignupDto signupDto) {
        // DB에 입력한 username이 존재하는지 확인
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 회원입니다.");
        }

        // 없으면 DB에 회원 저장
        User user = signupDto.toEntity(passwordEncoder.encode(signupDto.getPassword()));
        userRepository.save(user);
    }


}