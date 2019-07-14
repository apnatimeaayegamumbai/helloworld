package com.apnatimeaayega.sample.service;

import com.apnatimeaayega.sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



}
