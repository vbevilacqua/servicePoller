package com.vbevilacqua.poller.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.vbevilacqua.poller.security.ApplicationUserPermission.*;

@Getter
public enum ApplicationUserRole {
    GUEST(Sets.newHashSet(READ)),
    USER(Sets.newHashSet(READ, WRITE)),
    SYSADMIN(Sets.newHashSet(READ, WRITE, UPDATE, DELETE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> permissions =  getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
