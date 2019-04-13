package com.additives.service.security;

import com.additives.entity.security.Role;
import com.additives.entity.security.User;
import com.additives.repository.security.RoleRepository;
import com.additives.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void addNewUser(User user) {
        Role userRole = roleService.getRoleByName("USER");

        user.getRoles().add(userRole);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void setPassword(User user, String password) {
        String passwordHash = passwordEncoder.encode(password);
        user.setPassword(passwordHash);
    }

    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getUser() {
        return userRepository.getUserByEmail(getUsername());
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.getRoleByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}