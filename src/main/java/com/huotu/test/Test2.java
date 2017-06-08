package com.huotu.test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

/**
 * Created by mensonges on 2017/5/18.
 */
public class Test2 {
        public static void main(String[] args) throws Exception {
            Method method = Test2.class.getMethod( "main", String[].class );
            for( final Parameter parameter: method.getParameters() ) {
                System.out.println( "Parameter: " + parameter.getName() );
            }
            Optional< String > fullName = Optional.ofNullable( "Tom" );
            System.out.println( "Full Name is set? " + fullName.isPresent() );
            System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
            System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
            System.out.println("aaaa");
        }
}
