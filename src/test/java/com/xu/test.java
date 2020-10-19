package com.xu;


import com.xu.whiteList.DFAWhiteList;
import com.xu.whiteList.WhiteList;
import com.xu.whiteList.Word;
import com.xu.whiteListFilter.DFAWhiteListFilter;
import com.xu.whiteListFilter.WhiteListFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class test {

    @Test
    public void testAddWord(){
        WhiteList whiteList = new DFAWhiteList();
        whiteList.add(new Word("123",12));
        whiteList.add(new Word("11",13));
        whiteList.add(new Word("21",14));
        System.out.println(whiteList);
    }

    @Test
    public void test(){
        String content = "中国人说中国话";
        WhiteList whiteList = new DFAWhiteList();
        whiteList.add(new Word("中国",3));
        whiteList.add(new Word("中国人",2));
        whiteList.add(new Word("说",1));
        DFAWhiteListFilter whiteListFilter = new DFAWhiteListFilter();
        System.out.println(whiteListFilter.getContextScore(content, whiteList));


    }
}
