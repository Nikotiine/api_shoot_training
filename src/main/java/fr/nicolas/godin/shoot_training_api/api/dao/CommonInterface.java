package fr.nicolas.godin.shoot_training_api.api.dao;

import java.util.List;

public interface CommonInterface<T,D> {

    List<T> getAllActive();

    T create(D newObjectDto);

    T update(T updateObjectDto);

    List<T> delete(T deleteObjectDto);


}
