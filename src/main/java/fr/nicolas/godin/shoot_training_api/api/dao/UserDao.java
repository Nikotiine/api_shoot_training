package fr.nicolas.godin.shoot_training_api.api.dao;

import java.util.List;

public interface UserDao<T> {

    List<T> getAllActive();
}
