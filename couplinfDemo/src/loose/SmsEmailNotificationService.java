package loose;

public class SmsEmailNotificationService implements  NotificationService{
    @Override
    public  void send(String message){
        System.out.println("SMS  "+message);

    }
}
