package com.codestates.coffee.service;

import org.hibernate.Transaction;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
//import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;



import java.util.HashMap;
import java.util.Map;

//1
@Configuration
public class TxConfig {
    private final TransactionManager transactionManager;

    //2
    public TxConfig(TransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice(){

        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();

        //3
        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        //4
        RuleBasedTransactionAttribute txFindAttribute = new RuleBasedTransactionAttribute();
        txFindAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txFindAttribute.setReadOnly(true);

        //5
        Map<String, TransactionAttribute> txMethods = new HashMap<>();
        txMethods.put("find*", txFindAttribute);
        txMethods.put("*", txAttribute);

        //6
        txAttributeSource.setNameMap(txMethods);

        //7
        return new TransactionInterceptor(transactionManager, txAttributeSource);

    }

    @Bean
    public Advisor txAvisor(){
        //8
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.codestates.coffee.service." + "CoffeeService.*(..))");

        return new DefaultPointcutAdvisor(pointcut, txAdvice());//9

    }


}
