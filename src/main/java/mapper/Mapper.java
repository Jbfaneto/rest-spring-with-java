package mapper;

//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    //private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private static final ModelMapper mapper =  new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationList = new ArrayList<>();
        for (Object object : origin) {
            destinationList.add(mapper.map(object, destination));
        }
        return destinationList;
    }
}
