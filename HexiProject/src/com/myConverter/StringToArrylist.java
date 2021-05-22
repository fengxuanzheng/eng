package com.myConverter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

public class StringToArrylist implements Converter<String, ArrayList<String>> {
    @Override
    public ArrayList<String> convert(String source) {
        ArrayList<String> strings = new ArrayList<>();
        if (source.contains("-"))
        {
            String[] split = source.split("-");
            for (int i=0;i<split.length;i++)
            {
                strings.add(split[i]);
            }
        }
        else {
            strings.add(source);
        }

        return strings;
    }
}
