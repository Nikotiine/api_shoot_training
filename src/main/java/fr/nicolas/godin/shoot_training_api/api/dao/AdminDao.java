package fr.nicolas.godin.shoot_training_api.api.dao;

import java.util.Date;
import java.util.Objects;

public interface AdminDao<T> {
     long countTotalEntry();

     T findLastEntry();

}
