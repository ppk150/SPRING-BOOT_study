package com.codestates.section2week4.singleton;

public class SingletonService {

    private  static  final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){}

}
