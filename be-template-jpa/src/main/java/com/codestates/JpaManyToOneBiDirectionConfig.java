package com.codestates;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneBiDirectionConfig {

    private EntityManager em;
    private EntityTransaction  tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory){
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return  args -> {
            mappinManyToOneBiDirection();
        };

    }


    private void mappinManyToOneBiDirection(){

        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong",
                "010-1111-1111");

        Order order = new Order();

        member.addOrder(order); // 1
        order.addMember(member); // 2

        em.persist(member); // 3
        em.persist(order);  // 4

        tx.commit();

        // 5
        Member findMember = em.find(Member.class, 1L);

        // 6
        findMember
                .getOrders()
                .stream()
                .forEach(findOrder -> {
                    System.out.println("findOrder: " +
                            findOrder.getOrderId() + ", "
                            +findOrder.getOrderStatus());
                });

    }


}
