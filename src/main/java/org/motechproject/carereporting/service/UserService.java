package org.motechproject.carereporting.service;

import org.motechproject.carereporting.domain.AreaEntity;
import org.motechproject.carereporting.domain.PermissionEntity;
import org.motechproject.carereporting.domain.RoleEntity;
import org.motechproject.carereporting.domain.UserEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Set;

public interface UserService {

    String HAS_ROLE_MANAGE_SYSTEM_USERS = "hasRole('CAN_MANAGE_SYSTEM_USERS')";
    String HAS_ROLE_CAN_CREATE_ROLE = "hasRole('CAN_CREATE_ROLE')";
    String HAS_ROLE_CAN_EDIT_ROLE = "hasRole('CAN_EDIT_ROLE')";
    String HAS_ROLE_CAN_REMOVE_ROLE = "hasRole('CAN_REMOVE_ROLE')";

    @PreAuthorize(HAS_ROLE_MANAGE_SYSTEM_USERS)
    Set<UserEntity> getAllUsers();

    UserEntity getCurrentlyLoggedUser();

    UserEntity register(String username, AreaEntity area, Set<RoleEntity> roles);

    @PreAuthorize(HAS_ROLE_MANAGE_SYSTEM_USERS)
    void register(UserEntity userEntity);

    void updateUser(UserEntity user);

    Set<RoleEntity> getAllRoles();

    @PreAuthorize(HAS_ROLE_MANAGE_SYSTEM_USERS)
    RoleEntity getRoleById(Integer id);

    RoleEntity getRoleByName(String name);

    @PreAuthorize(HAS_ROLE_MANAGE_SYSTEM_USERS)
    void addRole(String roleName);

    UserEntity getUserById(Integer id);

    UserEntity getUserByName(String name);

    boolean doesUserExist(String userName);

    @PreAuthorize(HAS_ROLE_MANAGE_SYSTEM_USERS)
    void removeUserById(Integer userId);

    Set<PermissionEntity> getAllPermissions();

    PermissionEntity getPermissionById(Integer id);

    void createNewPermission(PermissionEntity permissionEntity);

    void updatePermission(PermissionEntity permissionEntity);

    void deletePermission(PermissionEntity permissionEntity);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_ROLE)
    void createNewRole(RoleEntity roleEntity);

    @PreAuthorize(HAS_ROLE_CAN_EDIT_ROLE)
    void updateRole(RoleEntity roleEntity);

    @PreAuthorize(HAS_ROLE_CAN_REMOVE_ROLE)
    void removeRoleById(Integer id);

}

