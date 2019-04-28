import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by 希明人 on 2017/2/28.
 */
public class Send {
    public static  String myEmailAccount="yanlunka@163.com";
    public static String myEmailPassword="test123456";
    public static String myEmailSMTPHost="smtp.163.com";
    public static String receiveMailAccount="987327263@qq.com";
    public static void main(String []args) throws UnsupportedEncodingException, MessagingException {
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host",myEmailSMTPHost);
        props.setProperty("mail.smtp.auth","true");
        props.put("mail.smtp.port", 465);// 端口号
        props.put("mail.smtp.ssl.enable", "true");
        Session session=Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message=createMessage(session,myEmailAccount,receiveMailAccount);
        Transport transport=session.getTransport();
        transport.connect(myEmailAccount,myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
 }

    private static MimeMessage createMessage(Session session, String myEmailAccount, String receiveMailAccount) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmailAccount,"后台管理中心","UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMailAccount,"User1","UTF-8"));
        message.setSubject("验证码","UTF-8");
        message.setContent("你正在修改个人信息，验证码为"+3+"","text/html;charset=UTF-8");
        message.setSentDate(new Date());
        return message;
    }
}
