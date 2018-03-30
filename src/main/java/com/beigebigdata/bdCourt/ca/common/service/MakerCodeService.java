package com.beigebigdata.bdCourt.ca.common.service;

import java.util.HashMap;

public interface MakerCodeService{

    String makeCode(String tag, HashMap<String, Object> options);
    
    String makeCode(String tag);
}