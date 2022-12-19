////package com.codestates.auth.userdetails;
////
////import com.codestates.oauth2_jwt.utils.CustomAuthorityUtils;
////import com.codestates.exception.BusinessLogicException;
////import com.codestates.exception.ExceptionCode;
////import com.codestates.member.entity.Member;
////import com.codestates.member.repository.MemberRepository;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Component;
////
////import java.util.Collection;
////import java.util.Optional;
////
////@Component
////public class MemberDetailsService implements UserDetailsService {
////    private final MemberRepository memberRepository;
////    private final CustomAuthorityUtils authorityUtils;
////
////    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
////        this.memberRepository = memberRepository;
////        this.authorityUtils = authorityUtils;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
////        Optional<Member> optionalMember = memberRepository.findByEmail(username);
////        Member findMember = optionalMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
////
////        return new MemberDetails(findMember);
////
////    }
////
////    private final class MemberDetails extends  Member implements  UserDetails{
////        MemberDetails(Member member){
////            setMemberId(member.getMemberId());
////            setEmail(member.getEmail());
////            setPassword(member.getPassword());
////            setRoles(member.getRoles());
////        }
////
////        @Override
////        public Collection<? extends  GrantedAuthority> getAuthorities(){
////            return authorityUtils.createAuthorities(this.getRoles());
////        }
////
////        @Override
////        public String getUsername(){
////            return getEmail();
////        }
////
////        @Override
////        public boolean isAccountNonExpired(){
////            return true;
////        }
////
////        @Override
////        public boolean isAccountNonLocked(){
////            return true;
////        }
////
////        @Override
////        public boolean isCredentialsNonExpired(){
////            return true;
////        }
////
////        @Override
////        public boolean isEnabled(){
////            return true;
////        }
////
////
////
////    }
////}
//package com.codestates.auth.userdetails;
//
//import com.codestates.exception.BusinessLogicException;
//import com.codestates.exception.ExceptionCode;
//import com.codestates.helper.event.MemberRegistrationApplicationEvent;
//import com.codestates.member.entity.Member;
//import com.codestates.member.repository.MemberRepository;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
///**
// *  - 메서드 구현
// *  - DI 적용
// *  - Spring Data JPA 적용
// *  - 트랜잭션 적용
// */
//@Transactional
//@Service
//public class MemberService {
//    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public Member createMember(Member member) {
//        if (!existsEmail(member.getEmail())) {
//            Member savedMember = memberRepository.save(member);
//            return savedMember;
//        }
//
//        return null;
//    }
//
//    public Member updateMember(Member member) {
//        Member findMember = findVerifiedMember(member.getMemberId());
//
//        return memberRepository.save(findMember);
//    }
//
//    @Transactional(readOnly = true)
//    public Member findMember(long memberId) {
//        return findVerifiedMember(memberId);
//    }
//
//    public Page<Member> findMembers(int page, int size) {
//        return memberRepository.findAll(PageRequest.of(page, size,
//                Sort.by("memberId").descending()));
//    }
//
//    public void deleteMember(long memberId) {
//        Member findMember = findVerifiedMember(memberId);
//
//        memberRepository.delete(findMember);
//    }
//
//    @Transactional(readOnly = true)
//    public Member findVerifiedMember(long memberId) {
//        Optional<Member> optionalMember =
//                memberRepository.findById(memberId);
//        Member findMember =
//                optionalMember.orElseThrow(() ->
//                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//        return findMember;
//    }
//
//    private boolean existsEmail(String email) {
//        Optional<Member> member = memberRepository.findByEmail(email);
//        return member.isPresent();
//    }
//}