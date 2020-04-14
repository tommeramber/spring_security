package com.example.secdemo.security;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.example.secdemo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet()),

    ADMIN(Sets.newHashSet(COURSE_READ, 
                          COURSE_WRITE,
                          STUDENT_WRITE, 
                          STUDENT_READ)), 

    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, 
                                 STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

}