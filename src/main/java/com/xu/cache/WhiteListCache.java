package com.xu.cache;

import com.xu.whiteList.WhiteList;
import com.xu.whiteList.Word;

public interface WhiteListCache {

    public void init(WhiteList whiteList);

    public  boolean add(Word word);







}
