package com.codestates.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMemberService implements MemberService {//1

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    //2
    public InMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
    public Member createMember(Member member) {

        //3
        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());

        //4
        String encryptrdPassword = passwordEncoder.encode(member.getPassword());

        // 5
        UserDetails userDetails =new User(member.getEmail(), encryptrdPassword, authorities);

        //6
        userDetailsManager.createUser(userDetails);

        return member;
    }

    private List<GrantedAuthority> createAuthorities(String... roles){
        return Arrays.stream(roles).map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

}
