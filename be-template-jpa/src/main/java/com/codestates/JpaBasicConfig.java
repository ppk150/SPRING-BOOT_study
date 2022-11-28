package com.codestates;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;



    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) { // 1
        this.em = emFactory.createEntityManager(); // 2
        this.tx = em.getTransaction();

        return args -> {
            // TODO 이 곳에 학습할 코드를 타이핑하세요!
//            example01();
//            example02();
//            example03();
//            example04();
//            example05();

            testEmailNotNull();
            testEmailUpdatable();
            testEmailUnique();


        };


    }

    private void example01(){
        Member member = new Member("hgd@gmail.com");

        em.persist(member); // 3

        Member resultMember = em.find(Member.class, 1L); // 4
        System.out.println("Id: " + resultMember.getMemberId() + ", email: " + resultMember.getEmail());
    }
    private void example02(){
        tx.begin();
        Member member = new Member("hgd@gmail.com");

        em.persist(member);

        tx.commit();

        Member resultMember1 = em.find(Member.class, 1L);

        System.out.println("Id: " + resultMember1.getMemberId() + ", email: " + resultMember1.getEmail());

        Member resultMember2 = em.find(Member.class, 2L);

        System.out.println(resultMember2 == null);


    }

    private void example03(){
        tx.begin();

        Member member1 =new Member("hgd1@gmail.com");
        Member member2 =new Member("hgd2@gmail.com");

        em.persist(member1);
        em.persist(member2);

        tx.commit();


    }

//    private void example04(){
//        tx.begin();
//        em.persist(new Member("hgd1@gmail.com"));
//        tx.commit();
//
//        tx.begin();
//        Member member1 = em.find(Member.class, 1L);
//        member1.setEmail("hgd1@yahoo.co.kr");
//        tx.commit();
//    }

    private void example05(){
        tx.begin();
        em.persist(new Member("hgd1@gmail.com"));
        tx.commit();

        tx.begin();
        Member member = em.find(Member.class, 1L);
        em.remove(member);
        tx.commit();
    }

    private void testEmailNotNull(){
        tx.begin();
        em.persist(new Member());
        tx.commit();
    }

    private void testEmailUpdatable(){
        tx.begin();
        em.persist(new Member("hgd@gmail.com"));
        Member member = em.find(Member.class, 1L);
        member.setEmail("hgd@yahoo.co.kr");
        tx.commit();
    }

    private void testEmailUnique(){
        tx.begin();
        em.persist(new Member("hgd@gmail.com"));
        em.persist(new Member("hgd@gmail.com"));
        tx.commit();
    }



}
