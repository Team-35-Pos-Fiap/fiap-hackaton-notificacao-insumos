package br.com.gerenciamento.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.gerenciamento.dto.MensagemDto;
import br.com.gerenciamento.services.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${app.email.remetente}")
	private String remetente;
	
	@Override
	public void enviarEmail(MensagemDto mensagem) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
	        
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
	        
	        helper.setTo(mensagem.destinatario());
	        helper.setSubject(mensagem.titulo());
	        helper.setText(mensagem.email(), true);
	        
	        mailSender.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("Erro ao tentar enviar email " + e.getLocalizedMessage());
		}
	}

	
	
}
