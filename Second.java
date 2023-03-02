package 上机20230227;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Second {
    public static Map<String,Double>map=new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int xm,xl,d;
        xm=sc.nextInt();
        xl=sc.nextInt();
        d=sc.nextInt();
        int[] xmArr=new int[xm];
        int[] xlArr=new int[xl];
        int mNum=0;
        int lNum=0;
        for (int i = 0; i < xmArr.length; i++) {
            xmArr[i]=sc.nextInt();
            mNum+= xmArr[i];
        }
        for (int i = 0; i < xlArr.length; i++) {
            xlArr[i]=sc.nextInt();
            lNum+=xlArr[i];
        }
        if(d<lNum){
            System.out.println("0.0000");
            return;
        }
        if(d>=lNum+mNum){
            System.out.println("1.0000");
            return;
        }
        String bigDecimal = new BigDecimal(Recursion(xmArr, xlArr, d, 1,mNum,lNum)).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        int i = bigDecimal.indexOf(".");
        StringBuilder stringBuilder=new StringBuilder(bigDecimal);
        int n=bigDecimal.length()-i-5;
        if(n!=0){
            for (int j = 0; j < n; j++) {
                stringBuilder.append(0);
            }
        }
        System.out.println(stringBuilder);
    }
    public static double Recursion(int[] xm,int[] xl,int num,double prob,int mn,int ln){
        int mSize=0;
        int lSize=0;
        for (int i = 0; i < xm.length; i++) {
            if(xm[i]!=0)mSize++;
        }
        for (int i = 0; i < xl.length; i++) {
            if(xl[i]!=0)lSize++;
        }
        double m=0;
        if(ln>num)return 0;
        if(lSize==0)return prob;
        if(num<=0)return 0;
        Arrays.sort(xm);
        Arrays.sort(xl);
        String key= Arrays.toString(xm) +","+ Arrays.toString(xl);
        if(map.get(key)!=null){
            return map.get(key)*prob;
        }
        if(ln<num){
            for (int i = 0; i < xm.length; i++) {
                if(xm[i]==0)continue;
                xm[i]=xm[i]-1;
                double temp=Recursion(xm,xl,num-1,(1/(double)(mSize+lSize)),mn-1,ln);
                m+=temp;
                xm[i]=xm[i]+1;
                while (i<xm.length-1&&xm[i]==xm[i+1]){
                    i++;
                    m+=temp;
                }
            }
        }
        for (int i = 0; i < xl.length; i++) {
            if(xl[i]==0)continue;
            xl[i]=xl[i]-1;
            double temp=Recursion(xm,xl,num-1,(1/(double)(mSize+lSize)),mn,ln-1);
            xl[i]=xl[i]+1;
            m+=temp;
            while (i<xl.length-1&&xl[i]==xl[i+1]){
                m+=temp;
                i++;
            }
        }
        map.put(key,m);
        return m*prob;
    }
}