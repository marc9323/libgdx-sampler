package com.sampler.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;

public class SampleFactory {

    public static SampleBase newSample(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name parameter is required");
        }

        SampleInfo info = SamplerInfos.find(name);

        try {
            return (SampleBase)ClassReflection.newInstance(info.getClazz());
        } catch(Exception e) {
            throw new RuntimeException("Cannot create sample with name= " + name, e);
        }
    }

    private SampleFactory() {

    }
}
