package com.graduation.furniture.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.graduation.furniture.dto.EmailDetails;
import com.graduation.furniture.dto.EmailPaymentSuccess;
import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.entities.OrderUser;
import com.graduation.furniture.service.EmailService;
import com.graduation.furniture.service.OrderItemService;
import com.graduation.furniture.service.OrderUserService;
import com.graduation.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderUserService orderUserService;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendMailPaymentSuccess(EmailPaymentSuccess details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getEmail());
            mimeMessageHelper.setSubject(details.getSubject());
            StringBuilder content = new StringBuilder();
            OrderUser orderUser = orderUserService.findById(details.getOrderId()).orElse(null);
            List<OrderItem> orderItemList = orderItemService.findOrderItemByOrderUserOrderId(details.getOrderId());
            orderItemList.forEach(orderItem -> {


                content.append("                    <tr>\n" +
                        "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #ff0000;  line-height: 18px;  vertical-align: top; padding:10px 0;\" class=\"article\">\n" +
                        orderItem.getProduct().getProductName() +
                        "                      </td>\n" +
                        "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"center\">" + orderItem.getProduct().getPrice() + "</td>\n" +
                        "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"center\">" + orderItem.getQuantity() + "</td>\n" +
                        "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #1e2b33;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"right\">$" + orderItem.getPaymentOrderItem() + "</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                      <td height=\"1\" colspan=\"4\" style=\"border-bottom:1px solid #e4e4e4\"></td>\n" +
                        "                    </tr>\n");
            });
            // Create the HTML content with CSS styling
            String htmlContent = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                    "<title> Order confirmation </title>\n" +
                    "<meta name=\"robots\" content=\"noindex,nofollow\" />\n" +
                    "<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0;\" />\n" +
                    "<style type=\"text/css\">\n" +
                    "  @import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);\n" +
                    "  body { margin: 0; padding: 0; background: #e1e1e1; }\n" +
                    "  div, p, a, li, td { -webkit-text-size-adjust: none; }\n" +
                    "  .ReadMsgBody { width: 100%; background-color: #ffffff; }\n" +
                    "  .ExternalClass { width: 100%; background-color: #ffffff; }\n" +
                    "  body { width: 100%; height: 100%; background-color: #e1e1e1; margin: 0; padding: 0; -webkit-font-smoothing: antialiased; }\n" +
                    "  html { width: 100%; }\n" +
                    "  p { padding: 0 !important; margin-top: 0 !important; margin-right: 0 !important; margin-bottom: 0 !important; margin-left: 0 !important; }\n" +
                    "  .visibleMobile { display: none; }\n" +
                    "  .hiddenMobile { display: block; }\n" +
                    "\n" +
                    "  @media only screen and (max-width: 600px) {\n" +
                    "  body { width: auto !important; }\n" +
                    "  table[class=fullTable] { width: 96% !important; clear: both; }\n" +
                    "  table[class=fullPadding] { width: 85% !important; clear: both; }\n" +
                    "  table[class=col] { width: 45% !important; }\n" +
                    "  .erase { display: none; }\n" +
                    "  }\n" +
                    "\n" +
                    "  @media only screen and (max-width: 420px) {\n" +
                    "  table[class=fullTable] { width: 100% !important; clear: both; }\n" +
                    "  table[class=fullPadding] { width: 85% !important; clear: both; }\n" +
                    "  table[class=col] { width: 100% !important; clear: both; }\n" +
                    "  table[class=col] td { text-align: left !important; }\n" +
                    "  .erase { display: none; font-size: 0; max-height: 0; line-height: 0; padding: 0; }\n" +
                    "  .visibleMobile { display: block !important; }\n" +
                    "  .hiddenMobile { display: none !important; }\n" +
                    "  }\n" +
                    "</style>\n" +
                    "\n" +
                    "\n" +
                    "<!-- Header -->\n" +
                    "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#e1e1e1\">\n" +
                    "  <tr>\n" +
                    "    <td height=\"20\"></td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>\n" +
                    "      <table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#ffffff\" style=\"border-radius: 10px 10px 0 0;\">\n" +
                    "        <tr class=\"hiddenMobile\">\n" +
                    "          <td height=\"40\"></td>\n" +
                    "        </tr>\n" +
                    "        <tr class=\"visibleMobile\">\n" +
                    "          <td height=\"30\"></td>\n" +
                    "        </tr>\n" +
                    "\n" +
                    "        <tr>\n" +
                    "          <td>\n" +
                    "            <table width=\"480\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullPadding\">\n" +
                    "              <tbody>\n" +
                    "                <tr>\n" +
                    "                  <td>\n" +
                    "                    <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"col\">\n" +
                    "                      <tbody>\n" +
                    "                        <tr>\n" +
                    "                          <td align=\"left\"> <img src=\"https://firebasestorage.googleapis.com/v0/b/jolie-house.appspot.com/o/image%2F1686322536338_logo2.jpg?alt=media&token=da14d58d-2056-47d8-99d8-15a00a0b1dd9\" width=\"100\" height=\"100\" alt=\"logo\" border=\"0\" /></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                          <td style=\"font-size: 12px; color: #5b5b5b; font-family: 'Open Sans', sans-serif; line-height: 18px; vertical-align: top; text-align: left;\">\n" +
                    "                            Hello, <strong>" + details.getReceiver() + "</strong>.\n" +
                    "                            <br> Thank you for shopping from our store and for your order.\n" +
                    "                          </td>\n" +
                    "                        </tr>\n" +
                    "                      </tbody>\n" +
                    "                    </table>\n" +
                    "                    <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"col\">\n" +
                    "                      <tbody>\n" +
                    "                        <tr class=\"visibleMobile\">\n" +
                    "                          <td height=\"20\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                          <td height=\"5\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                          <td style=\"font-size: 21px; color: #ff0000; letter-spacing: -1px; font-family: 'Open Sans', sans-serif; line-height: 1; vertical-align: top; text-align: right; padding-top: 20px;\">\n" +
                    "                            Invoice\n" +
                    "                          </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                        <tr class=\"hiddenMobile\">\n" +
                    "                          <td height=\"15\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr class=\"visibleMobile\">\n" +
                    "                          <td height=\"20\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                          <td style=\"font-size: 12px; color: #5b5b5b; font-family: 'Open Sans', sans-serif; line-height: 18px; vertical-align: top; text-align: right;\">\n" +
                    "                            <small>ORDER</small>#" + details.getOrderId() + "<br />\n" +
                    "                            <small>"+details.getCreatedAt() +"</small>\n" +
                    "                          </td>\n" +
                    "                        </tr>\n" +
                    "                      </tbody>\n" +
                    "                    </table>\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "              </tbody>\n" +
                    "            </table>\n" +
                    "          </td>\n" +
                    "        </tr>\n" +
                    "      </table>\n" +
                    "    </td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "<!-- /Header -->\n" +
                    "<!-- Order Details -->\n" +
                    "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#e1e1e1\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td>\n" +
                    "        <table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#ffffff\">\n" +
                    "          <tbody>\n" +
                    "            <tr>\n" +
                    "            <tr class=\"hiddenMobile\">\n" +
                    "              <td height=\"60\"></td>\n" +
                    "            </tr>\n" +
                    "            <tr class=\"visibleMobile\">\n" +
                    "              <td height=\"40\"></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "              <td>\n" +
                    "                <table width=\"480\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullPadding\">\n" +
                    "                  <tbody>\n" +
                    "                    <tr>\n" +
                    "                      <th style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; font-weight: normal; line-height: 1; vertical-align: top; padding: 0 10px 7px 0;\" width=\"52%\" align=\"left\">\n" +
                    "                        Item\n" +
                    "                      </th>\n" +
                    "                      <th style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; font-weight: normal; line-height: 1; vertical-align: top; padding: 0 0 7px;\" align=\"center\">\n" +
                    "                        Price\n" +
                    "                      </th>\n" +
                    "                      <th style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; font-weight: normal; line-height: 1; vertical-align: top; padding: 0 0 7px;\" align=\"center\">\n" +
                    "                        Quantity\n" +
                    "                      </th>\n" +
                    "                      <th style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #1e2b33; font-weight: normal; line-height: 1; vertical-align: top; padding: 0 0 7px;\" align=\"right\">\n" +
                    "                        Subtotal\n" +
                    "                      </th>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                      <td height=\"1\" style=\"background: #bebebe;\" colspan=\"4\"></td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                      <td height=\"10\" colspan=\"4\"></td>\n" +
                    "                    </tr>\n" +
                    content +
                    "                  </tbody>\n" +
                    "                </table>\n" +
                    "              </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "              <td height=\"20\"></td>\n" +
                    "            </tr>\n" +
                    "          </tbody>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "<!-- /Order Details -->\n" +
                    "<!-- Total -->\n" +
                    "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#e1e1e1\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td>\n" +
                    "        <table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#ffffff\">\n" +
                    "          <tbody>\n" +
                    "            <tr>\n" +
                    "              <td>\n" +
                    "\n" +
                    "                <!-- Table Total -->\n" +
                    "                <table width=\"480\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullPadding\">\n" +
                    "                  <tbody>\n" +
                    "                    <tr>\n" +
                    "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #000; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
                    "                        <strong>Grand Total</strong>\n" +
                    "                      </td>\n" +
                    "                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #000; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
                    "                        <strong> $" +orderUser.getTotalOrder() +"</strong>\n" +
                    "                      </td>\n" +
                    "                    </tr>\n" +
                    "                  </tbody>\n" +
                    "                </table>\n" +
                    "                <!-- /Table Total -->\n" +
                    "\n" +
                    "              </td>\n" +
                    "            </tr>\n" +
                    "          </tbody>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "<!-- /Total -->\n" +
                    "<!-- Information -->\n" +
                    "<!-- /Information -->\n" +
                    "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#e1e1e1\">\n" +
                    "  <tr>\n" +
                    "    <td>\n" +
                    "      <table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#ffffff\" style=\"border-radius: 0 0 10px 10px;\">\n" +
                    "        <tr>\n" +
                    "          <td>\n" +
                    "            <table width=\"480\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullPadding\">\n" +
                    "              <tbody>\n" +
                    "                <tr>\n" +
                    "                  <td style=\"font-size: 12px; color: #5b5b5b; font-family: 'Open Sans', sans-serif; line-height: 18px; vertical-align: top; text-align: left;\">\n" +
                    "                    Have a nice day.\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "              </tbody>\n" +
                    "            </table>\n" +
                    "          </td>\n" +
                    "        </tr>\n" +
                    "        <tr class=\"spacer\">\n" +
                    "          <td height=\"30\"></td>\n" +
                    "        </tr>\n" +
                    "\n" +
                    "      </table>\n" +
                    "    </td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td height=\"20\"></td>\n" +
                    "  </tr>\n" +
                    "</table>";

            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendMailForgotPassword(String email, String url) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Reset Password for Jolie House Website");
            String htmlContent = "<html><body style='background-color: #f4f4f4; font-family: Arial, sans-serif;'>" +
                    "<div style='background-color: white; padding: 20px; border-radius: 5px;'>" +
                    "<h1 style='color: #333;'>Welcome to Jolie House!</h1>" +
                    "<p style='color: #555;'>Dear <strong>" + email + "</strong>,</p>" +
                    "<p style='color: #555;'>Link reset password for your account: <a href='" + url + "'>" + url + "</a></p>" +
                    "<p style='color: #555;'>Please click above link to reset your password</p>" +
                    "<ul style='color: #555;'>" +
                    "</ul>" +
                    "<p style='color: #555;'>If you have any questions, feel free to contact us.</p>" +
                    "<p style='color: #555;'>Best regards,</p>" +
                    "<p style='color: #555;'>Jolie House Team</p>" +
                    "</div></body></html>";
            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            return "200";
        } catch (MessagingException e) {
            return "400";
        }
    }

    @Override
    public String sendOTPSignUp(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getEmail());
            mimeMessageHelper.setSubject(details.getSubject());
            String htmlContent = "<html><body style='background-color: #f4f4f4; font-family: Arial, sans-serif;'>" +
                    "<div style='background-color: white; padding: 20px; border-radius: 5px;'>" +
                    "<h1 style='color: #333;'>Welcome to Jolie House!</h1>" +
                    "<p style='color: #555;'>Dear <strong>" + details.getUsername() + "</strong>,</p>" +
                    "<p style='color: #555;'>Thank you for signing up.</p>" +
                    "<p style='color: #555;'>Otp code to confirm your account is:</p>" +
                    "<ul style='color: #555;'>" +
                    "<li>OTP: " + details.getMsgBody() + "</li>" +
                    "</ul>" +
                    "<p style='color: #555;'>If you have any questions, feel free to contact us.</p>" +
                    "<p style='color: #555;'>Best regards,</p>" +
                    "<p style='color: #555;'>Jolie House Team</p>" +
                    "</div></body></html>";
            mimeMessageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendMailSignUpSuccess(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getEmail());
            mimeMessageHelper.setSubject(details.getSubject());
            String htmlContent = "<html><body style='background-color: #f4f4f4; font-family: Arial, sans-serif;'>" +
                    "<div style='background-color: white; padding: 20px; border-radius: 5px;'>" +
                    "<h1 style='color: #333;'>Welcome to Jolie House!</h1>" +
                    "<p style='color: #555;'>Dear <strong>" + details.getUsername() + "</strong>,</p>" +
                    "<p style='color: #555;'>Thank you for signing up. Your account has been successfully created.</p>" +
                    "<p style='color: #555;'>Here are some important details:</p>" +
                    "<ul style='color: #555;'>" +
                    "<li>Username: <strong>" + details.getUsername() + "</strong> </li>" +
                    "<li>Email: " + details.getEmail() + "</li>" +
                    "</ul>" +
                    "<p style='color: #555;'>If you have any questions, feel free to contact us.</p>" +
                    "<p style='color: #555;'>Best regards,</p>" +
                    "<p style='color: #555;'>Jolie House Team</p>" +
                    "</div></body></html>";
            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

}
