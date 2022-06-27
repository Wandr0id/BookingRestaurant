package com.proyecto.bookingrestaurantapi.services.Impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.proyecto.bookingrestaurantapi.dtos.EmailTemplateDto;
import com.proyecto.bookingrestaurantapi.entities.Notification;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.proyecto.bookingrestaurantapi.exceptions.NotFountException;
import com.proyecto.bookingrestaurantapi.repositories.NotificationRepository;
import com.proyecto.bookingrestaurantapi.services.EmailService;

@Service
public class EmailServiceImpl  implements EmailService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public String processSendEmail(String receiver, String templateCode, String currentName)
			throws BookingException {
		final EmailTemplateDto emailTemplateDto = findTemplateAndReplace(templateCode, currentName);
		this.sendEmail(receiver, emailTemplateDto.getSubject(), emailTemplateDto.getTemplate());
		return "EMAIL_SENT";
	}
	
	
	//creando metodo privado para el envio de emails
	private void sendEmail(final String receiver, final String subject, final String template) throws BookingException{
		final MimeMessage email = javaMailSender.createMimeMessage();
		final MimeMessageHelper content;
		try {
			content = new MimeMessageHelper (email,true);
			content.setSubject(subject);
			content.setTo(receiver);
			content.setText(template,true);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		javaMailSender.send(email);
	}
	
	//creando metodo privado para reemplazar el nombre del usuario
	private EmailTemplateDto findTemplateAndReplace(final String templateCode, final String currentName) throws BookingException{
		final Notification notificationTemplate= notificationRepository.findByTemplateCode(templateCode)
			.orElseThrow(() -> new NotFountException("TEMPLATE_NOT_FUND","CODE_TEMPLATE_NOT_FOUND"));
		
		final EmailTemplateDto emailTemplateDto = new EmailTemplateDto();	
		emailTemplateDto.setSubject(notificationTemplate.getTemplateCode());
		emailTemplateDto.setTemplate(notificationTemplate.getTemplate().replaceAll("\\{"+"name"+"\\}", currentName));
		return emailTemplateDto;
	}

}
