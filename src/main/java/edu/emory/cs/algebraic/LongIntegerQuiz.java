package edu.emory.cs.algebraic;

import java.util.Arrays;

public class LongIntegerQuiz extends LongInteger {
    public LongIntegerQuiz() {
        super();
    }
//   Constructor w/ String parameter
    public LongIntegerQuiz(String n) {
        super(n);
    }

//   Constructor w/ LongInteger parameter
    public LongIntegerQuiz(LongInteger n) {
        super(n);
    }

    @Override
    protected void addDifferentSign(LongInteger n) {
        byte[] sum;
        byte[] neg;
        int m = Math.max(digits.length, n.digits.length);
        sum = new byte[m];
        neg = new byte[m];
        boolean thisBigger = (this.compareAbs(n) >= 0);

        if(thisBigger) {
            System.arraycopy(digits, 0, sum, 0, digits.length);
            System.arraycopy(n.digits, 0, neg, 0, n.digits.length);
        }
        else {
            System.arraycopy(n.digits, 0, sum, 0, n.digits.length);
            System.arraycopy(digits, 0, neg, 0, digits.length);
        }

        //Perform subtraction
        for (int i = 0; i < m; i++) {
            if(sum[i] < neg[i]) {
                sum[i] +=10;
                sum[i+1]--;
            }
            sum[i] -= neg[i];
        }

        //Check for extra zeros
        int i = m-1;
        while(i >= 1 && sum[i] == 0) i--;

        digits = i == m-1 ? sum : Arrays.copyOf(sum, i+1);
        this.sign = ((thisBigger && this.isNegative()) || (!thisBigger && this.isPositive())) ? Sign.NEGATIVE : Sign.POSITIVE;
    }
}
