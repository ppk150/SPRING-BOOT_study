package com.codestates;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneUniDirectionConfig {

    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory){

        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneUniDirection();
        };

    }

    private void mappingManyToOneUniDirection(){

        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong",
                "010-1111-1111");

        //1
        em.persist(member);

        Order order = new Order();

        order.addMember(member);  //2

        em.persist(order);  //3

        tx.commit();

        //4
        Order findOder = em.find(Order.class,1L);

        //5
        System.out.println("findOrder: " + findOder.getMember().getMemberId() + ", "+ findOder.getMember().getEmail());



    }


}
