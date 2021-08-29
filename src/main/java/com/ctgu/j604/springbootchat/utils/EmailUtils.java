package com.ctgu.j604.springbootchat.utils;

import org.springframework.beans.factory.annotation.Value;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    @Value("${Email.account}")
    private static String myEmailAccount;

    @Value("${Email.password}")
    private static String myEmailPassword;

    @Value("${Email.SMTPHost}")
    private static String myEmailSMTPHost;

    public static boolean sendMimeMessage(String verifyCode,String receiveAccount,String sessionId){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.host",myEmailSMTPHost );
        props.setProperty("mail.smtp.auth", "true");        // 需要请求认证
        Session session = Session.getInstance(props);

        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        try {
            MimeMessage message = createMimeMessage(session,myEmailAccount,receiveAccount,verifyCode,sessionId);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private static MimeMessage createMimeMessage(Session session,String myEmailAccount,String receiveAccount, String verifyCode,String sessionId) throws Exception{

        //创建一封邮件
        MimeMessage message = new MimeMessage(session);

            //发件人
        message.setFrom(myEmailAccount);


        //收件人
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveAccount,"新注册用户","UTF-8"));


        //邮件主题
        message.setSubject("新用户注册资格验证","UTF-8");

        message.setContent("尊敬的猿圈客户你好，请点击以下链接获取注册资格\n <a href='localhost/emailVerify/"+sessionId+"/"+verifyCode+"'>点击获取注册资格</a>","text/html;charset=UTF-8");

        //设置发送时间
        message.setSentDate(new Date());

        //保存设置
        message.saveChanges();

        return message;
    }
}
