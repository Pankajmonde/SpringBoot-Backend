import TightCoupling.UserService;
import loose.EmailNotificationService;
import  loose.NotificationService;
import loose.SmsEmailNotificationService;


public class AppMain {

    public static void main(String[] args){
        //tight
        UserService userService= new UserService();
        userService.notifyUser("order placed");

        //  Loose
        NotificationService emailService= new EmailNotificationService();
        NotificationService smsService= new SmsEmailNotificationService();
        loose.UserService userServiceLoose=new loose.UserService(smsService);
        userServiceLoose.notifyUser("order processed");


        /*
        Constructor INjection - dependency is provided via constructor
        Setter Injection - dependecny i provided via setter method
        Field Injection- dependdency is assigned directly to a field
         */

        //setter injectio
        loose.UserService userserviceLooseSetter
                =new loose.UserService();
        userserviceLooseSetter.setNotificationService(emailService);

        userserviceLooseSetter.notificationService=smsService;
    }
}
