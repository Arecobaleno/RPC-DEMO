package org.example.user.remote;



import org.example.user.model.User;

import java.util.List;

public interface UserRemote {
    public Object saveUser(User user);

    public Object saveUsers(List<User> users);
}
