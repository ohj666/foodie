package com.ou.foodie.util;

import org.n3r.idworker.UniqueOrderGenerate;

public class SidUtils {

        //这里的0，0分别是      * @param workerId 工作ID (0~31)     * @param datacenterId 数据中心ID (0~31)，可以写在配置文件中。
        private static UniqueOrderGenerate idWorker = new UniqueOrderGenerate(0, 0);
        public static SidUtils getInstance() {
            return new SidUtils();
        }

        public static String get() {
        return String.valueOf(idWorker.nextId());
    }

//    public static void main(String[] args) {
//        System.out.println(getInstance().get());
//        System.out.println(getInstance().get());
//        System.out.println(getInstance().get());
//    }
}
