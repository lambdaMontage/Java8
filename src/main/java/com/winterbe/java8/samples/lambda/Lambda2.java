package com.winterbe.java8.samples.lambda;

/**
 * @author montage
 */
public class Lambda2 {

    @FunctionalInterface
    public static interface Converter<F, T> {
        T convert(F from);
    }

    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {

        /** Integer 转 String */
        Converter<Integer,String> IntegerToString = (from) -> String.valueOf(from);
        System.out.println(IntegerToString.convert(123456));


        /** Integer 转 String */
        Converter<Integer,String>  IntegerToString2 = String::valueOf;
        System.out.println(IntegerToString2.convert(654321));

        Something something = new Something();

        Converter<String, String> stringConverter = something::startsWith;
        String converted3 = stringConverter.convert("Java");
        System.out.println(converted3);    // result J

        // constructor reference

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.getClass().getName());
    }
}
