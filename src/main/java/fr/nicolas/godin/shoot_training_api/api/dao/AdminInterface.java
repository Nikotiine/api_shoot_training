package fr.nicolas.godin.shoot_training_api.api.dao;


import java.util.List;


public interface AdminInterface<T,D> extends CommonInterface<T,D>{
     /**
      * Compte le nombre d'entree total en base de donnée
      * @return long
      */
     long countTotalEntry();

     /**
      * Retourne la derniere entree de la base de donnée
      * @return T
      */
     T getLastEntry();

     /**
      * Retourne la liste complete de T actif ou non
      * @return List<T>
      */
     List<T> getAll();


}
