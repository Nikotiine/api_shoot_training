package fr.nicolas.godin.shoot_training_api.api.tools;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class ModelMapperTool {


    public static <T, D> List<D> mapList(List<T> entityList, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        Function<T, D> mapFunction = entity -> modelMapper.map(entity, dtoClass);
        return entityList.stream().map(mapFunction).toList();
    }

    public static <T, D> D mapDto(T intialObject,Class<D> dtoClass){
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(intialObject, dtoClass);
    }

}
