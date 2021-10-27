package com.vbevilacqua.poller.auth;

import com.google.common.collect.Lists;
import com.vbevilacqua.poller.model.UsersModel;
import com.vbevilacqua.poller.repository.UsersRepo;
import com.vbevilacqua.poller.security.ApplicationUserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vbevilacqua.poller.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDAOService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepo repo;

    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder, UsersRepo repo) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        List<UsersModel> users = repo.findAll();

        return getApplicationUsers(users)
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(List<UsersModel> users) {
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        users.stream()
                .forEach(u -> applicationUsers.add(
                        new ApplicationUser(
                                ApplicationUserRole.valueOf(u.getRole()).getGrantedAuthority(),
                                u.getName(),
                                passwordEncoder.encode(u.getPassword()),
                                true,
                                true,
                                true,
                                true)));

        return applicationUsers;
    }
}
