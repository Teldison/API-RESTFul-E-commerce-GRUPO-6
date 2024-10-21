package org.serratec.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	public String enviarEmail(String destinatario, String assunto, String mensagem) {
		SimpleMailMessage enviamensagem = new SimpleMailMessage();
		
		enviamensagem.setFrom("bookshopgp6@gmail.com");
		enviamensagem.setTo(destinatario);
		enviamensagem.setSubject(assunto);
		enviamensagem.setText(mensagem);
		
		try {
			sender.send(enviamensagem);
			return "E-mail enviado com sucesso!";
		} catch (Exception e) {
			return "Erro ao enviar mensagem. Verifique!";
		}
		
		
		
	}
}
