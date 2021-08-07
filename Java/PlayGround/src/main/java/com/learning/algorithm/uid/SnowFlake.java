package com.learning.algorithm.uid;

import java.util.Arrays;

public class SnowFlake {
    // Start Stamp
    private final static long START_STAMP = 1618656791339L;

    // Number of bits for each part
    private final static int SEQUENCE_BIT = 12;
    private final static int MACHINE_BIT = 5;
    private final static int DATACENTER_BIT = 5;

    // Maximum for each part
    private final static int MAX_SEQUENCE = ~(-1 << SEQUENCE_BIT);
    private final static int MAX_MACHINE = ~(-1 << MACHINE_BIT);
    private final static int MAX_DATACENTER = ~(-1 << DATACENTER_BIT);

    // Shift distance for each part
    private final static int SEQUENCE_SHIFT_LEFT = 0;
    private final static int MACHINE_SHIFT_LEFT = SEQUENCE_BIT;
    private final static int DATACENTER_SHIFT_LEFT = MACHINE_SHIFT_LEFT + MACHINE_BIT;
    private final static int STAMP_SHIFT_LEFT = DATACENTER_SHIFT_LEFT + DATACENTER_BIT;

    private final int datacenterId;
    private final int machineId;
    private int sequence = 0;
    private long lastStamp = -1;

    public SnowFlake(int datacenterId, int machineId) {
        if (datacenterId > MAX_DATACENTER || datacenterId < 0) throw new RuntimeException("Wrong datacenterId.");
        if (machineId > MAX_MACHINE || machineId < 0) throw new RuntimeException("Wrong machineId");
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    // Test
    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(11, 22);
        int n = 1000000;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = snowFlake.nextId();
        }
        System.out.println(Arrays.toString(res));
    }

    public synchronized long nextId() {
        long curStamp = getCurStamp();

        if (curStamp < lastStamp) {
            throw new RuntimeException("Cannot generate new UID, due to the stamp moved backward.");
        }

        if (curStamp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                curStamp = getNextStamp();
            }
        } else {
            sequence = 0;
        }

        lastStamp = curStamp;

        return (curStamp - START_STAMP) << STAMP_SHIFT_LEFT |
                datacenterId << DATACENTER_SHIFT_LEFT |
                machineId << MACHINE_SHIFT_LEFT |
                sequence;
    }

    public long getCurStamp() {
        return System.currentTimeMillis();
    }

    public long getNextStamp() {
        long curStamp = getCurStamp();
        while (curStamp == lastStamp) {
            curStamp = getCurStamp();
        }
        return curStamp;
    }
}
