package com.oc.projets.projet_7.restController;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.batch.EnvoiMail;

@RestController
public class BatchController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job processJob;
	
//	@Autowired
//	private JavaMailSender javaMailSender;
	
	@RequestMapping("/batch")
	public String handle() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJob, jobParameters);
		return "Batch done";
	}
	
	@RequestMapping("/envoiMail")
	public void EnvoiMail() {
		JavaMailSender javaMailSender = new JavaMailSenderImpl();
		System.out.println("Dans envoi mail");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("romaindemellier@gmail.com");
		
        msg.setSubject("Testing from Spring Boot 2");
        msg.setText("Hello World \n Spring Boot Email");
        System.out.println("Dans envoi mail 2");
        System.out.println(javaMailSender);

        javaMailSender.send(msg);
        System.out.println("Dans envoi mail fin");
	}
}
