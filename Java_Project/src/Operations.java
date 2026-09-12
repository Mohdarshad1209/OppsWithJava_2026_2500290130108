import java.util.*;

class op{
    static void add(int a, int b){
        System.out.println(a+b);
    }

    static void sub(int a,int b){
        System.out.println(a-b);
    }

    static void mul(int a,int b){
        System.out.println(a*b);
    }

    static void div(int a,int b){
        if(b!=0){
            System.out.println(a/b);
        }else{
            System.out.println("Invalid");
        }
    }
}

public class Operations {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        op.add(a,b);
        op.sub(a,b);
        op.mul(a,b);
        op.div(a,b);
    }
    
}
