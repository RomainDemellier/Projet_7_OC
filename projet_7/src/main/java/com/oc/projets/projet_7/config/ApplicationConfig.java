package com.oc.projets.projet_7.config;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
//	@Bean
//	JobLauncher jobLauncher() {
//		return new JobLauncher() {
//			
//			@Override
//			public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException,
//					JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//	}
}
