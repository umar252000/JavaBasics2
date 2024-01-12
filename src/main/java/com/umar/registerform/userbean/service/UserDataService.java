package com.umar.registerform.userbean.service;


import java.util.*;




import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umar.registerform.userbean.UserData;
import com.umar.registerform.userbean.repository.UserDataRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserDataService {
    @Autowired

    private UserDataRepository userRepository;

    @Autowired
  private JavaMailSender javaMailSender;

    public void saveUserData(UserData userData) {
        userRepository.save(userData);
    }
    
    public List<UserData> getAll() {
        List<UserData> findAll = userRepository.findAll();
        System.out.println("Number of records fetched: " + findAll.size()); 
        return findAll;
    }
    
    public void delete(long id)
    {
    	userRepository.deleteById(id);
    	
    }
    
    public UserData login(String email)
    {
    	 UserData findByEmail = userRepository.findByEmail(email);
    	 return findByEmail;
    }
    
    public void sendEmail(String to) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("teckteck788@gmail.com");
            helper.setTo(to);
            helper.setSubject("Registration Successful");
            helper.setText("Register Successful");
            javaMailSender.send(message);
            System.out.println("Sent email successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public UserData updateUser(long userId, UserData updatedUserData) {
        // Check if the user exists
        Optional<UserData> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            UserData user = existingUser.get();
            // Update the user data
            user.setName(updatedUserData.getName());
            user.setEmail(updatedUserData.getEmail()); 
            // Save the updated user
            return userRepository.save(user);
        } else {
            // Handle the case where the user does not exist
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }
   
    public boolean isUserExists(String email) {
        // Check if a user with the given email already exists in the repository
         UserData findByEmail = userRepository.findByEmail(email);
         if(findByEmail.equals(null))
         {
        return true;
    }
         return false;
    }

    
}
