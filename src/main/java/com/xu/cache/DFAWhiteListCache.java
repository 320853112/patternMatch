package com.xu.cache;

import com.xu.whiteList.DFANode;
import com.xu.whiteList.WhiteList;
import com.xu.whiteList.Word;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

@Slf4j
public class DFAWhiteListCache implements WhiteListCache{

    private WhiteList whiteList;



    private static class SingleFactory {
        private static final DFAWhiteListCache INSTANCE = new DFAWhiteListCache();
    }

    public static final DFAWhiteListCache getInstance() {
        return SingleFactory.INSTANCE;
    }

    @Override
    public void init(WhiteList whiteList) {
        this.whiteList = whiteList;
        log.info("初始化白名单缓存完成");
    }

    @Override
    public boolean add(Word word) {
        return this.whiteList.add(word);
    }




}
