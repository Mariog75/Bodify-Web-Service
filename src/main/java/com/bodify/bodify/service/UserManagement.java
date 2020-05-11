package com.bodify.bodify.service;

import com.bodify.bodify.model.User;

public interface UserManagement<ID, USER> {
    USER registerUser (USER User) throws Exception;

    USER getUserById (ID userId) throws Exception;

    USER updateUser (USER User) throws Exception;

    void deleteUser (ID userId) throws Exception;

}
