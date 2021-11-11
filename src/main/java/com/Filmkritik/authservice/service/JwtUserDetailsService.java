package com.Filmkritik.authservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Filmkritik.authservice.dto.UserDto;
import com.Filmkritik.authservice.entities.SecurityQuestionsEntity;
import com.Filmkritik.authservice.entities.UserEntity;
import com.Filmkritik.authservice.entities.UserSecQuestMappingEntity;
import com.Filmkritik.authservice.repository.SecurityQuestionsRepository;
import com.Filmkritik.authservice.repository.UserRepository;
import com.Filmkritik.authservice.repository.UserSecQuestMappingRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(JwtUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SecurityQuestionsRepository securityQuestionsRepo;

	@Autowired
	private UserSecQuestMappingRepository userSecQuestMappingRepository;

	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Getting User Details by Username -"+ username);
		UserEntity user = userRepo.findByUsername(username);
		if (user == null) {
			logger.error("User not found with username");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}


	private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		});
		return authorities;
	}
	
	public String save(UserDto user) {
		UserEntity newUser = new UserEntity();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepo.save(newUser);
		return "Success";
	}
	
	public String update(UserDto user) {
		Long uID = getUserIdbyUsername(user.getUsername());
		Optional<UserEntity> newUser = findByUserId(uID);
		UserEntity obj_entity = newUser.stream().findFirst().get();
		obj_entity.setUsername(user.getUsername());
		obj_entity.setPassword(bcryptEncoder.encode(user.getPassword()));
		obj_entity.setFirstName(user.getFirstName());
		obj_entity.setLastName(user.getLastName());
		obj_entity.setMobileNumber(user.getMobileNumber());
		userRepo.save(obj_entity);
		return "Success";
	}
	
	public Optional<UserEntity> findByUserId(Long id) {
		return userRepo.findById(id);
	}
	
	public Long getUserIdbyUsername(String username) {
		return userRepo.findByUsername(username).getId();
	}


	public Map<String, String> getSQbyUserId(long userId) {
		// TODO Auto-generated method stub
		Map<String, String> SQA= new HashMap<String, String>();
		List<UserSecQuestMappingEntity> questions = userSecQuestMappingRepository.getByUserId(userId);
		questions.forEach((quest)->{
			
			SQA.put(securityQuestionsRepo.findById(quest.getSQ_id()).getQuestions(), quest.getAnswer());
		});
		return SQA;
	}


	public String sendSecurityCode(long userId) {
		// TODO Auto-generated method stub
		String uid= generateCode();
		sendEmail(userRepo.findById(userId).get().getUsername(), "Confirm Password has been Changed", uid);
		return uid;
	}
	
	 private String generateCode() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}


	public void sendEmail(String to, String subject, String code) {
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("filmkritik.se@gmail.com");
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText("Your Password has been changed.\\n Your New Password is:\\t" + code + "\\n\\n "
		        		+ "if you dont request changing password please contact to us.\\n\\n Thank you for trusting our Services."
		        		+ "\\n Have a great life.\"");
		        emailSender.send(message);
		    }
}