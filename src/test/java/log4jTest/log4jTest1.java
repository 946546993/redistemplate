//package log4jTest;
//
//import org.apache.log4j.Logger;
//
//public class log4jTest1 {
//    //使用指定的类XXX初始化日志对象，方便在日志输出的时候，可以打印出日志信息所属的类
//    final static Logger logger = Logger.getLogger(log4jTest1.class);
//
//    public static void main(String[] args) {
//        log4jTest1 obj = new log4jTest1();
//        obj.runMe("Harish Manana");
//
//        try {
//            int no = 1 / 0;
//        } catch (ArithmeticException ae) {
//            logger.error("Exception in division", ae);
//        }
//    }
//
//    private void runMe(String parameter) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("This is debug : " + parameter);
//        }
//
//        if (logger.isInfoEnabled()) {
//            logger.info("This is info : " + parameter);
//        }
//
//        logger.warn("This is warn : " + parameter);
//        logger.error("This is error : " + parameter);
//        logger.fatal("This is fatal : " + parameter);
//    }
//}