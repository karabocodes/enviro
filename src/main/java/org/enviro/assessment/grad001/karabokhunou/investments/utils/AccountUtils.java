package org.enviro.assessment.grad001.karabokhunou.investments.utils;
import java.time.Year;
import java.util.*;

public class AccountUtils {

    public static final String ACCOUNT_EXIST_CODE = "001";
    public  static final String ACCOUNT_EXISTS_MESSAGE = "The investor has an account already";
    public static final String ACCOUNT_CREATION_CODE = "002";
    public  static final String ACCOUNT_CREATION_MESSAGE = "Account has been created";

    public static final String ACCOUNT_FOUND_CODE = "003";
    public static final String ACCOUNT_FOUND_MESSAGE = "Account found";

    public static final String ACCOUNT_DOESNT_EXIST_FOUND_CODE = "004";
    public static final String ACCOUNT_DOESNT_EXIST_MESSAGE = "Account found";

    public static final String ACCOUNT_CREDITED_SUCCESS_CODE = "005";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "Account has been credited";

     public static  String generateAccountNumber(){
         /**
          *2023 + 6 random numbers
          */

         Year currentYear = Year.now();
         int max = 999999;
         int min = 100000;

         //generte a random number

         int randNumber = (int) Math.floor(Math.random()* (max -min +1) + min);
         String year = String.valueOf(currentYear);
         String randomNumber = String.valueOf(randNumber);
         StringBuilder accountNumber = new StringBuilder();

         return accountNumber.append(year).append(randNumber).toString();
     }
}
