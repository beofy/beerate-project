package cn.beerate.dao;

import org.springframework.util.AntPathMatcher;

public class PathTest {

    public static void main(String[] args) {
      AntPathMatcher matcher  = new AntPathMatcher();


        System.out.println(matcher.match("/user/*/**","/user/loan/add"));
        System.out.println(matcher.match("/user/*","/user/loan/add"));  ;
    }
}
