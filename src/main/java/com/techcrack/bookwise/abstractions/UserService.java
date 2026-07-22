package com.techcrack.bookwise.abstractions;

import com.techcrack.bookwise.entity.Users;

public interface UserService extends BasicCRUD<Users> {
    String authenticate(String username, String password);
}
