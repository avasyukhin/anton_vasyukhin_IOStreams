package layer_converter;

/**
 * Created by о on 29.05.2016.
 */
public interface LayerConverter <T,R>{
    R convert(T inputLayer);
}
