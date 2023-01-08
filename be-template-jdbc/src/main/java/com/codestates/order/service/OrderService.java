package com.codestates.order.service;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.member.service.MemberService;
import com.codestates.order.repository.OrderRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
        // TODO should business logic
        // 1
        memberService.findVerifiedMember(order.getMemberId().getId());

        //2
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());
                });

        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        // TODO should business logic

        return findVerifiedOrder(orderId);
    }

    // TODO 주문 상태 수정 메서드는 JPA 학습에서 추가됩니다.

    public List<Order> findOrders() {
        // TODO should business logic

        return (List<Order>) orderRepository.findAll();
    }


    // 3
    public void cancelOrder(long orderId) {
        // TODO should business logic

        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();
        //4
        if(step >= 2){
            throw  new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);

            //5
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }

}
