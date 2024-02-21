package fr.nicolas.godin.shoot_training_api.api.dao;

import java.util.Date;
import java.util.Objects;

public interface AdminDao<T> {
     /**
      * Compte le nombre d'entree total en base de donnée
      * @return long
      */
     long countTotalEntry();

     /**
      * Routourne la derniere entree de la base de donnée
      * @return T
      */
     T findLastEntry();

}
