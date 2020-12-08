package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SamplerInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(

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
            if(info.getName() == name) {
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
