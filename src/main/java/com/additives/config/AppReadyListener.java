package com.additives.config;

import com.additives.entity.security.Role;
import com.additives.entity.security.User;
import com.additives.service.security.RoleService;
import com.additives.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppReadyListener {
    @Value("${default.admin.name}")
    private String defaultAdminName;

    @Value("${default.admin.password}")
    private String defaultAdminPassword;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady() {
        if (userService.getByEmail("admin") != null) {
            return;
        }

        addDefaultRoles();
        addAdminUser();
    }

    private void addDefaultRoles() {
        Role admin = new Role();
        admin.setRole("ADMIN");
        admin.setDescription("Адміністратор");
        roleService.save(admin);

        Role moderator = new Role();
        moderator.setRole("MODERATOR");
        moderator.setDescription("Модератор");
        roleService.save(moderator);
    }

    private void addAdminUser() {
        User adminUser = new User();
        adminUser.setEmail(defaultAdminName);
        adminUser.setPassword(defaultAdminPassword);
        adminUser.setName("Админ");
        adminUser.setLastName("Админ");

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ADMIN"));
        adminUser.setRoles(roles);

        userService.addNewUser(adminUser);
    }
}