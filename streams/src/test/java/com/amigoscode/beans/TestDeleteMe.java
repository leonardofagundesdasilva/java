package com.amigoscode.beans;

public class TestDeleteMe {
    private static final long ONE_BILLION = 1_000_000_000;

    // Add any helper functions you may need here


    /*
    g =  [1.1, 1.2, 1.3]
    response = 79
    m = 128
    65 - 128
    g^t
    */
    int getBillionUsersDay(float[] growthRates) {
        //Find multiplier value that makes gr over 1B
        int multiplier = getMultiplier(growthRates);
        System.out.println("multiplier " + multiplier);

        //Binary search from (multiplier / 2) + 1 to multiplier to find response
        int from = (multiplier / 2) + 1;
        System.out.println("from " + from);
        return findDay(growthRates, from, multiplier);
    }

    private int getMultiplier(float[] growthRates) {
        int day = 1;

        while (getTotalGrowthRate(growthRates, day) < ONE_BILLION) {
            day *= 2;
        }

        return day;
    }

    //TODO: make it work with odd, even, only 1, to indexes beside each other
    //TODO: If greater or smaller, check the one right before/after it
  /*
  9 - 16
  (15)
  8 <
  9
  10
  11
  12 <
  13
  14
  15
  16 >
  */
    private int findDay(float[] growthRates, int from, int to) {
        int day = (int) Math.ceil((from + to) / 2);

        double growthRate1 = getTotalGrowthRate(growthRates, day - 1);
        double growthRate2 = getTotalGrowthRate(growthRates, day);

        //System.out.println("day " + day);
        //System.out.println("growthRate1 " + (day - 1) + " - " + (growthRate1 >= ONE_BILLION));
        //System.out.println("growthRate2 " + day + " - " + (growthRate2 >= ONE_BILLION));

        if(growthRate1 < ONE_BILLION && growthRate2 >= ONE_BILLION) {
            return day;
        } else if (growthRate1 < ONE_BILLION && growthRate2 < ONE_BILLION) {
            return findDay(growthRates, day, to);
        } else {
            return findDay(growthRates, from, day);
        }
    }

    private double getTotalGrowthRate(float[] growthRates, int day) {
        int growthRate = 0;

        for(float f: growthRates) {
            growthRate += Math.pow(f,day);
        }

        return growthRate;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
    float[] test_1 = {1.1f, 1.2f, 1.3f};
    int expected_1 = 79;
    int output_1 = getBillionUsersDay(test_1);
    System.out.println("Output: " + output_1);
    check(expected_1, output_1);

        float[] test_2 = {1.01f, 1.02f};
        int expected_2 = 1047;
        int output_2 = getBillionUsersDay(test_2);
        System.out.println("Output: " + output_2);
        check(expected_2, output_2);


        // Add your own test cases here

    }
    public static void main(String[] args) {
        new TestDeleteMe().run();
    }
}