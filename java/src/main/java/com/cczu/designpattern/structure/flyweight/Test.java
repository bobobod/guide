package com.cczu.designpattern.structure.flyweight;

public class Test {

    public static void main(String[] args) throws InterruptedException {
         ActivityController activityController = new ActivityController();

        for (int idx = 0; idx < 10; idx++) {
            Long req = 10001L;
            Activity activity = activityController.queryActivityInfo(req);
            System.out.println(activity);
            Thread.sleep(1000);
        }
    }

}