package com.ou.foodie;

import com.ou.foodie.properties.ProjectConstant;
import org.junit.Test;

import javax.print.attribute.standard.Fidelity;
import java.io.File;

public class MyTest {

    @Test
    public void SeparatorTest(){


                String a = "xiaomeng2";
                final String b = "xiaomeng";
                String d = "xiaomeng";
                String c = b + 2;
                String e = d + 2;

                System.out.println((a == c));
                System.out.println((a == e));



    }
}
