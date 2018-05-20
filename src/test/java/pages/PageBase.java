package pages;

import java.util.Random;

public abstract class PageBase {
    static final int TIMEOUT_30S = 30;

    public int randomInRange(int minExclusive, int maxInclusive){
        Random r = new Random();
        return r.nextInt(maxInclusive-minExclusive) + minExclusive;
    }
}
