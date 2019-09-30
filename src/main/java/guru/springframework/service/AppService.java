package guru.springframework.service;

import guru.springframework.api.domain.User;

import java.util.List;

public interface AppService {
    List<User> getUsers(Integer limit);
}
