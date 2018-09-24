package server.electronics.util.mapper;


import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.function.Consumer;

public interface SuperConverter<T, U, R> {

    R convert(@NotNull T dto);

    Collection<R> convert(@NotNull Collection<T> result);

    R update(@NotNull T updater, @NotNull R result);

    static <E> void setIfNonNull(E value, Consumer<E> setterMethod){
        if(value != null)
            setterMethod.accept(value);
    }

    U convertToDto(R entity);
}
