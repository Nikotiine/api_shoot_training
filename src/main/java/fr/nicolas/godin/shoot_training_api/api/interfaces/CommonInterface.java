package fr.nicolas.godin.shoot_training_api.api.interfaces;

import java.util.List;

public interface CommonInterface<T,D> {

    /**
     * Retourne la liste T des element actif
     * @return List<T>
     */
    List<T> getAllActive();

    /**
     * Creation de l'objet T avec son Dto de creation D
     * @param newObjectDto D
     * @return T
     */
    T create(D newObjectDto);

    /**
     * Mise a jour de l'objet sous son format Dto
     * @param updateObjectDto T
     * @return T
     */
    T update(T updateObjectDto);

    /**
     * Suppression de l'objet avec son id en param
     * @param id int
     * @return  List<T>
     */
    List<T> delete(int id);


}
