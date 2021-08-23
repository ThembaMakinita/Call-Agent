package com.jtm.assessment.callcentre.commons.converters;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Data
@Scope("prototype")
public class BeanConverter {

    private ModelMapper modelMapper;

    public BeanConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public  <T, E> E mapBean(T source, Class<E> typeDestination) {
        return modelMapper.map(source,typeDestination);
    }
}
