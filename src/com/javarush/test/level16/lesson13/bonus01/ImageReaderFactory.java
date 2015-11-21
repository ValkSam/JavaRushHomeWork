package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Valk on 16.01.15.
 */
public class ImageReaderFactory {
    public static ImageReader getReader(ImageTypes imageType){
        if (imageType == ImageTypes.BMP) return new BmpReader();
        if (imageType == ImageTypes.PNG) return new PngReader();
        if (imageType == ImageTypes.JPG) return new JpgReader();
        throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
