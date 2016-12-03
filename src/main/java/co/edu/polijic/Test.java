package co.edu.polijic;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        String value1 = StringUtils.trim("");
        String value2 = StringUtils.trim(null);
        String value3 = StringUtils.trim(" G ");
        String value4 = StringUtils.trim(" ");
        System.out.println(StringUtils.isEmpty(value1));
        System.out.println(StringUtils.isEmpty(value2));
        System.out.println(StringUtils.isEmpty(value3));
        System.out.println(StringUtils.isEmpty(value4
        ));
        System.out.println("".length());
    }
}
