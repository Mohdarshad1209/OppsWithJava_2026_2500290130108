class Hello{
    void display(){
        System.out.println("Helloworld!");
    }
}

public class HelloWorldUsingObjectAndMethodCalling {
    public static void main(String[] args) {
        Hello obj=new Hello();
        obj.display();
    }
}