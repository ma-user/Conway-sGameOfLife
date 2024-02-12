package org.swiggy;

import java.util.Scanner;

public class UserInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInteger(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
}
