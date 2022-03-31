package com.springsecdemo.auth;

import com.google.common.collect.Lists;
import com.springsecdemo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.springsecdemo.security.ApplicationUserRole.*;


@Repository("fake")
public class FakeApplicationUsersDaoService implements ApplicationUserDao {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUsersDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUsers().stream().filter(applicationUser ->
                        username.equalsIgnoreCase(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(STUDENT.getGrantedAuthorities(),
                        passwordEncoder.encode("password"), "kunal", true, true, true, true),
                new ApplicationUser(ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password") , "aditi", true, true, true, true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(),
                        passwordEncoder.encode("password"), "mau", true, true, true, true));

        return applicationUsers;
    }
}
