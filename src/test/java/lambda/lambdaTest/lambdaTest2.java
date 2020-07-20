package lambda.lambdaTest;

public class lambdaTest2 {
    public static void main(String[] args){
        int n = 0;
        sayMessage sayMessage = message -> {
            //Variable used in lambda expression should be final or effectively final
            //lambda表达式中使用的变量应该是final或有效的final
//           n = 1;

            System.out.println("Hello,"+message);
        };

        sayMessage.sayMessage("world!");
    }

    interface sayMessage{
        void sayMessage(String message);
    }
}
