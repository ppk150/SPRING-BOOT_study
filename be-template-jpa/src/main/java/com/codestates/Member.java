package com.codestates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.reflect.MemberSignature;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long memberId;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length =  100, nullable = false)
    private String name;

    @Column(length = 13, nullable = false,unique = true)
    private String phone;

    // 1
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;


    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

//    @Transient
//    private String age;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member( String email){
        this.email =email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    //2
    public enum MemberStatus{
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QYIT("탈퇴 상태");

        @Getter
        private String ststus;

        MemberStatus(String ststus){
            this.ststus = ststus;
        }
    }



}
