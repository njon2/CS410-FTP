package com.company;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/*We will create a test file like this for every class that we write. The imports above import Junit 4 and 5
  then each class/method can be tested.
  The @Test keyword is used to signify Tests, and it basically tells if the
 */

public class MainTest {

    @Test //The @Test is placed before all Tests, to signify a Test
    public void StartConnection()
    {
        Assertions.assertEquals(1, 1); //Asserts that the first arg (Expected)
                                                        // is the same as the second(actual)
        Assertions.assertEquals("hello", "world"); //This will fail, and output data
                                                                    //About the number of passed vs failed tests
    }
}