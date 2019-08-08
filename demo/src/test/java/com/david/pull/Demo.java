package com.david.pull;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Athor weimc
 * @CreateTime 2019/8/8 10:41
 * @Description: TODO
 */
public class Demo {

    public static void main(String[] args) {
        ConcurrentHashMap<Long, String> lockMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            PullTask pullTask = new PullTask(lockMap);
            Thread thread = new Thread(pullTask);
            thread.start();
        }
    }

}


