package com.sampler.common;

import com.sampler.ApplicationListenerSample;
import com.sampler.GdxGeneratedSample;
import com.sampler.GdxModuleInfoSample;
import com.sampler.GdxReflectionSample;
import com.sampler.InputListeningSample;
import com.sampler.InputPollingSample;
import com.sampler.OrthographicCameraSample;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SamplerInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GdxGeneratedSample.SAMPLE_INFO,
            GdxModuleInfoSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO

    );

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<>();

        for(SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);

        return ret;
    }

    public static SampleInfo find(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name argument is required!");
        }
        SampleInfo ret = null;

        for(SampleInfo info : ALL) {
            if(info.getName().equals(name)) {
                ret = info;
                break;
            }
        }

        if(ret == null){
            throw new IllegalArgumentException("could not find sample with name= " + name);
        }
        return ret;
    }

    private SamplerInfos(){

    }
}