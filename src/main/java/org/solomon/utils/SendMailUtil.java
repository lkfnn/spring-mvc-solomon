package org.solomon.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * <p>
 * Title: 邮件发送工具类
 * </p>
 * <p>
 * Description: 邮件发送工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 长城数字[www.e-u.cn]
 * </p>
 * 
 */
public class SendMailUtil {

    /** 发件人地址 */
    private String mailFrom = "";

    /** 邮件标题 */
    private String subject = "";

    /** 邮件内容 */
    private String content = "";

    /** 附件名称 */
    private String affixName = "";

    /** 附件地址 */
    private String affixPath = "";

    /** 收件人地址 */
    private String mailTo = "";

    /**
     * 设置发件人信息
     * 
     * @param mailFrom
     *            发件人地址
     * @param subject
     *            邮件标题
     * @param content
     *            邮件内容
     * 
     */
    public void setFrom(String mailFrom, String subject, String content) {
        this.mailFrom = mailFrom;
        this.content = content;
        this.subject = subject;
    }

    /**
     * 设置收件人信息
     * 
     * @param mailTo
     *            收件人地址
     * 
     */
    public void setTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * 设置附件信息
     * 
     * @param affixName
     *            附件名称
     * @param affixPath
     *            附件地址
     * 
     */
    public void setAffix(String affixName, String affixPath) {
        this.affixName = affixName;
        this.affixPath = affixPath;
    }

    /**
     * 发送邮件
     * 
     * @param smtpHost
     *            SMTP地址
     * @param smtpPort
     *            SMTP端口
     * @param mailUser
     *            用户名
     * @param mailPassWord
     *            授权码
     * 
     */
    public void send(String smtpHost, String smtpPort, String mailUser, String mailPassWord) {
        // 设置发送邮件的邮件服务器的属性
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", true);

        Session session = Session.getDefaultInstance(props);

        // 调试模式，在发送邮件的过程中在控制台上看到发送邮件的过程，供调试使。
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(mailFrom));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);

            // 添加附件
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(affixPath);
            // 添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            // 添加附件的标题
            messageBodyPart.setFileName(MimeUtility.encodeText(affixName));
            multipart.addBodyPart(messageBodyPart);

            // 将Multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(smtpHost, mailUser, mailPassWord);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendMailUtil cn = new SendMailUtil();
        // 设置发件人、邮件标题、邮件内容。
        cn.setFrom("XXXX@qq.com", "【SendMailUtil-Test】这是邮件标题", "这里是邮件内容。");

        // 设置收件人
        cn.setTo("XXXX@qq.com");

        // 设置要发送附件的名称和路径
        cn.setAffix("XXXX.zip", "D:\\XXXX.zip");

        // 设置SMTP服务器以及邮箱的帐号和密码，并发送邮件。
        cn.send("smtp.qq.com", "465", "XXXX@qq.com", "simlswqewelfcbcb");
    }
}