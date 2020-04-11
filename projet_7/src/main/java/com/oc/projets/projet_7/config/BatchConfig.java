package com.oc.projets.projet_7.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.oc.projets.projet_7.batch.Processor;
import com.oc.projets.projet_7.batch.JpaRepositoryItemReader;
import com.oc.projets.projet_7.batch.Writer;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.repository.EmpruntRepository;
import com.oc.projets.projet_7.repository.LivreRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	public JavaMailSender javaMailSender;
	
//	@Autowired
//	Job processJob;
	
	@Autowired
	private EmpruntRepository empruntRepository;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}
	
	@Bean Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<Emprunt, Emprunt>chunk(8)
				.reader(new JpaRepositoryItemReader<Emprunt>(this.empruntRepository)).processor(new Processor())
				.writer(new Writer(this.javaMailSender)).build();
	}
	
	@Bean
	JobExecutionListener listener() {
		return new JobExecutionListener() {
			
			@Override
			public void beforeJob(JobExecution jobExecution) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterJob(JobExecution jobExecution) {
				// TODO Auto-generated method stub
			}
		};
	}
	
//	@Scheduled(cron = "* /5 * * * *")
//	public void perform() throws Exception {
//		System.out.println("Entrée dans perform");
//		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
//				.toJobParameters();
//		jobLauncher.run(processJob, jobParameters);
//	}
	
//	@Bean
//	public JobBuilderFactory jobBuilderFactory2() {
//		return new JobBuilderFactory();
//	}
	
	
//	@Bean(name = "launcher")
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
