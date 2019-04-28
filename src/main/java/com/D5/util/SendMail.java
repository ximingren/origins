package com.D5.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件
 */
public class SendMail {
    public static String myEmailAccount = "yanlunka@163.com";//发送者的邮箱
    public static String myEmailPassword = "test123456";//发送者的授权码
    public static String myEmailSMTPHost = "smtp.163.com";//发送者邮箱的SMTP地址
    public static String receiveMailAccount;//接收者邮箱
    public static int indentifyNum;//验证码

    public SendMail(String receiveMailAccount, int indentifyNum) {
        this.receiveMailAccount = receiveMailAccount;
        this.indentifyNum = indentifyNum;
    }

    public void send() throws UnsupportedEncodingException, MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");//SMTP协议
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);// 端口号
        props.put("mail.smtp.ssl.enable", "true");//用ssl安全连接
        Session session = Session.getInstance(props);
        session.setDebug(false);
        MimeMessage message = createMessage(session, myEmailAccount, receiveMailAccount);//编辑邮件主体
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private static MimeMessage createMessage(Session session, String myEmailAccount, String receiveMailAccount) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myEmailAccount, "溯源网站后台管理中心", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "User", "UTF-8"));
            message.setSubject("溯源网站修改个人信息","UTF-8");
            message.setContent("您正在修改个人信息，验证码为" + indentifyNum + "", "text/html;charset=UTF-8");
            message.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
