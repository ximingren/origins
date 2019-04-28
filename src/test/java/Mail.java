import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by 希明人 on 2017/2/28.
 */
public class Mail {
    public static  void main(String []args) throws UnsupportedEncodingException, MessagingException {
        Properties props=new Properties();
        Session session=Session.getDefaultInstance(props);
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress("987327263@qq.com","测试","UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress("yanlunka@163.com","User1","UTF-8"));
        message.setSubject("Test邮件","UTF-8");
        message.setContent("邮件正文","UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
    }
}
