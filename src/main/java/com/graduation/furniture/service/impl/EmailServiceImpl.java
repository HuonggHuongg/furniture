package com.graduation.furniture.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.graduation.furniture.dto.EmailDetails;
import com.graduation.furniture.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration configuration;

	@Value("${spring.mail.username}") private String sender;

	@Override
	public String sendMailPaymentSuccess(EmailDetails details) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getEmail());
			mimeMessageHelper.setSubject(details.getSubject());


			// Create the HTML content with CSS styling
			String htmlContent = "<!--\n" +
					"\n" +
					"Follow me on\n" +
					"Dribbble: https://dribbble.com/supahfunk\n" +
					"Twitter: https://twitter.com/supahfunk\n" +
					"Codepen: https://codepen.io/supah/\n" +
					"\n" +
					"-->\n" +
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
					"<title> Order confirmation </title>\n" +
					"<meta name=\"robots\" content=\"noindex,nofollow\" />\n" +
					"<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0;\" />\n" +
					"<style type=\"text/css\">\n" +
					"  @import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);\n" +
					"\n" +
					"  body {\n" +
					"    margin: 0;\n" +
					"    padding: 0;\n" +
					"    background: #e1e1e1;\n" +
					"  }\n" +
					"\n" +
					"  div,\n" +
					"  p,\n" +
					"  a,\n" +
					"  li,\n" +
					"  td {\n" +
					"    -webkit-text-size-adjust: none;\n" +
					"  }\n" +
					"\n" +
					"  .ReadMsgBody {\n" +
					"    width: 100%;\n" +
					"    background-color: #ffffff;\n" +
					"  }\n" +
					"\n" +
					"  .ExternalClass {\n" +
					"    width: 100%;\n" +
					"    background-color: #ffffff;\n" +
					"  }\n" +
					"\n" +
					"  body {\n" +
					"    width: 100%;\n" +
					"    height: 100%;\n" +
					"    background-color: #e1e1e1;\n" +
					"    margin: 0;\n" +
					"    padding: 0;\n" +
					"    -webkit-font-smoothing: antialiased;\n" +
					"  }\n" +
					"\n" +
					"  html {\n" +
					"    width: 100%;\n" +
					"  }\n" +
					"\n" +
					"  p {\n" +
					"    padding: 0 !important;\n" +
					"    margin-top: 0 !important;\n" +
					"    margin-right: 0 !important;\n" +
					"    margin-bottom: 0 !important;\n" +
					"    margin-left: 0 !important;\n" +
					"  }\n" +
					"\n" +
					"  .visibleMobile {\n" +
					"    display: none;\n" +
					"  }\n" +
					"\n" +
					"  .hiddenMobile {\n" +
					"    display: block;\n" +
					"  }\n" +
					"\n" +
					"  @media only screen and (max-width: 600px) {\n" +
					"    body {\n" +
					"      width: auto !important;\n" +
					"    }\n" +
					"\n" +
					"    table[class=fullTable] {\n" +
					"      width: 96% !important;\n" +
					"      clear: both;\n" +
					"    }\n" +
					"\n" +
					"    table[class=fullPadding] {\n" +
					"      width: 85% !important;\n" +
					"      clear: both;\n" +
					"    }\n" +
					"\n" +
					"    table[class=col] {\n" +
					"      width: 45% !important;\n" +
					"    }\n" +
					"\n" +
					"    .erase {\n" +
					"      display: none;\n" +
					"    }\n" +
					"  }\n" +
					"\n" +
					"  @media only screen and (max-width: 420px) {\n" +
					"    table[class=fullTable] {\n" +
					"      width: 100% !important;\n" +
					"      clear: both;\n" +
					"    }\n" +
					"\n" +
					"    table[class=fullPadding] {\n" +
					"      width: 85% !important;\n" +
					"      clear: both;\n" +
					"    }\n" +
					"\n" +
					"    table[class=col] {\n" +
					"      width: 100% !important;\n" +
					"      clear: both;\n" +
					"    }\n" +
					"\n" +
					"    table[class=col] td {\n" +
					"      text-align: left !important;\n" +
					"    }\n" +
					"\n" +
					"    .erase {\n" +
					"      display: none;\n" +
					"      font-size: 0;\n" +
					"      max-height: 0;\n" +
					"      line-height: 0;\n" +
					"      padding: 0;\n" +
					"    }\n" +
					"\n" +
					"    .visibleMobile {\n" +
					"      display: block !important;\n" +
					"    }\n" +
					"\n" +
					"    .hiddenMobile {\n" +
					"      display: none !important;\n" +
					"    }\n" +
					"  }\n" +
					"</style>\n" +
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
					"                          <td align=\"left\"> <img src=\"http://www.supah.it/dribbble/017/logo.png\" width=\"32\" height=\"32\" alt=\"logo\" border=\"0\" /></td>\n" +
					"                        </tr>\n" +
					"                        <tr class=\"hiddenMobile\">\n" +
					"                          <td height=\"40\"></td>\n" +
					"                        </tr>\n" +
					"                        <tr class=\"visibleMobile\">\n" +
					"                          <td height=\"20\"></td>\n" +
					"                        </tr>\n" +
					"                        <tr>\n" +
					"                          <td style=\"font-size: 12px; color: #5b5b5b; font-family: 'Open Sans', sans-serif; line-height: 18px; vertical-align: top; text-align: left;\">\n" +
					"                            Hello, <strong>"+details.getRecipient()+"<strong>\n" +
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
					"                          <td style=\"font-size: 21px; color: #ff0000; letter-spacing: -1px; font-family: 'Open Sans', sans-serif; line-height: 1; vertical-align: top; text-align: right;\">\n" +
					"                            Invoice\n" +
					"                          </td>\n" +
					"                        </tr>\n" +
					"                        <tr>\n" +
					"                        <tr class=\"hiddenMobile\">\n" +
					"                          <td height=\"50\"></td>\n" +
					"                        </tr>\n" +
					"                        <tr class=\"visibleMobile\">\n" +
					"                          <td height=\"20\"></td>\n" +
					"                        </tr>\n" +
					"                        <tr>\n" +
					"                          <td style=\"font-size: 12px; color: #5b5b5b; font-family: 'Open Sans', sans-serif; line-height: 18px; vertical-align: top; text-align: right;\">\n" +
					"                            <small>ORDER</small> #800000025<br />\n" +
					"                            <small>MARCH 4TH 2016</small>\n" +
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
					"                      <th style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; font-weight: normal; line-height: 1; vertical-align: top; padding: 0 0 7px;\" align=\"left\">\n" +
					"                        <small>SKU</small>\n" +
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
					"                    <tr>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #ff0000;  line-height: 18px;  vertical-align: top; padding:10px 0;\" class=\"article\">\n" +
					"                        Beats Studio Over-Ear Headphones\n" +
					"                      </td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\"><small>MH792AM/A</small></td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"center\">1</td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #1e2b33;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"right\">$299.95</td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td height=\"1\" colspan=\"4\" style=\"border-bottom:1px solid #e4e4e4\"></td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #ff0000;  line-height: 18px;  vertical-align: top; padding:10px 0;\" class=\"article\">Beats RemoteTalk Cable</td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\"><small>MHDV2G/A</small></td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"center\">1</td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #1e2b33;  line-height: 18px;  vertical-align: top; padding:10px 0;\" align=\"right\">$29.95</td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td height=\"1\" colspan=\"4\" style=\"border-bottom:1px solid #e4e4e4\"></td>\n" +
					"                    </tr>\n" +
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
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        Subtotal\n" +
					"                      </td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e; line-height: 22px; vertical-align: top; text-align:right; white-space:nowrap;\" width=\"80\">\n" +
					"                        $329.90\n" +
					"                      </td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        Shipping &amp; Handling\n" +
					"                      </td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #646a6e; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        $15.00\n" +
					"                      </td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #000; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        <strong>Grand Total (Incl.Tax)</strong>\n" +
					"                      </td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #000; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        <strong>$344.90</strong>\n" +
					"                      </td>\n" +
					"                    </tr>\n" +
					"                    <tr>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #b0b0b0; line-height: 22px; vertical-align: top; text-align:right; \"><small>TAX</small></td>\n" +
					"                      <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #b0b0b0; line-height: 22px; vertical-align: top; text-align:right; \">\n" +
					"                        <small>$72.40</small>\n" +
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
					"                      <td>\n" +
					"                        <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"col\">\n" +
					"\n" +
					"                          <tbody>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 11px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 1; vertical-align: top; \">\n" +
					"                                <strong>BILLING INFORMATION</strong>\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td width=\"100%\" height=\"10\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 20px; vertical-align: top; \">\n" +
					"                                Philip Brooks<br> Public Wales, Somewhere<br> New York NY<br> 4468, United States<br> T: 202-555-0133\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                          </tbody>\n" +
					"                        </table>\n" +
					"\n" +
					"                        <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"col\">\n" +
					"                          <tbody>\n" +
					"                            <tr class=\"visibleMobile\">\n" +
					"                              <td height=\"20\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 11px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 1; vertical-align: top; \">\n" +
					"                                <strong>PAYMENT METHOD</strong>\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td width=\"100%\" height=\"10\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 20px; vertical-align: top; \">\n" +
					"                                Credit Card<br> Credit Card Type: Visa<br> Worldpay Transaction ID: <a href=\"#\" style=\"color: #ff0000; text-decoration:underline;\">4185939336</a><br>\n" +
					"                                <a href=\"#\" style=\"color:#b0b0b0;\">Right of Withdrawal</a>\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                          </tbody>\n" +
					"                        </table>\n" +
					"                      </td>\n" +
					"                    </tr>\n" +
					"                  </tbody>\n" +
					"                </table>\n" +
					"              </td>\n" +
					"            </tr>\n" +
					"            <tr>\n" +
					"              <td>\n" +
					"                <table width=\"480\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullPadding\">\n" +
					"                  <tbody>\n" +
					"                    <tr>\n" +
					"                      <td>\n" +
					"                        <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"col\">\n" +
					"                          <tbody>\n" +
					"                            <tr class=\"hiddenMobile\">\n" +
					"                              <td height=\"35\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr class=\"visibleMobile\">\n" +
					"                              <td height=\"20\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 11px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 1; vertical-align: top; \">\n" +
					"                                <strong>SHIPPING INFORMATION</strong>\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td width=\"100%\" height=\"10\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 20px; vertical-align: top; \">\n" +
					"                                Sup Inc<br> Another Place, Somewhere<br> New York NY<br> 4468, United States<br> T: 202-555-0171\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                          </tbody>\n" +
					"                        </table>\n" +
					"\n" +
					"                        <table width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"col\">\n" +
					"                          <tbody>\n" +
					"                            <tr class=\"hiddenMobile\">\n" +
					"                              <td height=\"35\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr class=\"visibleMobile\">\n" +
					"                              <td height=\"20\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 11px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 1; vertical-align: top; \">\n" +
					"                                <strong>SHIPPING METHOD</strong>\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td width=\"100%\" height=\"10\"></td>\n" +
					"                            </tr>\n" +
					"                            <tr>\n" +
					"                              <td style=\"font-size: 12px; font-family: 'Open Sans', sans-serif; color: #5b5b5b; line-height: 20px; vertical-align: top; \">\n" +
					"                                UPS: U.S. Shipping Services\n" +
					"                              </td>\n" +
					"                            </tr>\n" +
					"                          </tbody>\n" +
					"                        </table>\n" +
					"                      </td>\n" +
					"                    </tr>\n" +
					"                  </tbody>\n" +
					"                </table>\n" +
					"              </td>\n" +
					"            </tr>\n" +
					"            <tr class=\"hiddenMobile\">\n" +
					"              <td height=\"60\"></td>\n" +
					"            </tr>\n" +
					"            <tr class=\"visibleMobile\">\n" +
					"              <td height=\"30\"></td>\n" +
					"            </tr>\n" +
					"          </tbody>\n" +
					"        </table>\n" +
					"      </td>\n" +
					"    </tr>\n" +
					"  </tbody>\n" +
					"</table>\n" +
					"<!-- /Information -->\n" +
					"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullTable\" bgcolor=\"#e1e1e1\">\n" +
					"\n" +
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
					"          <td height=\"50\"></td>\n" +
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
					"<p style='color: #555;'>Link reset password for your account: <a href='"+url+"'>"+url+"</a></p>" +
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
