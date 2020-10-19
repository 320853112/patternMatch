package com.xu.whiteListFilter;

import com.xu.whiteList.DFANode;
import com.xu.whiteList.DFAWhiteList;
import com.xu.whiteList.WhiteList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DFAWhiteListFilter implements WhiteListFilter{

    public String getReplacedContext(String content, WhiteList whiteList) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return null;

    }

    public Integer getContextScore(String content, WhiteList whiteList) {
        if(!(whiteList instanceof DFAWhiteList)){
            log.warn("类型转换错误");
            return 0;
        }
        int maxScore = 0;
        if (StringUtils.isBlank(content)) {
            return 0;
        }
        content = StringUtils.trim(content);
        char[] chars = content.toCharArray();
        HashMap<Character, DFANode> whiteList1 = ((DFAWhiteList) whiteList).getWhiteList();
        for (int index = 0; index < chars.length; index++) {
            DFANode currNode = whiteList1.get(chars[index]);
            if(currNode == null){
                continue;
            }
            for(int i = index + 1; i < chars.length; i++){
                if(currNode.isEnd()){
                    System.out.println(StringUtils.substring(content, index, i));
                    if(maxScore<currNode.getScore()){
                        maxScore = currNode.getScore();
                    }
                }
                currNode = currNode.getChilds().get(chars[i]);
                if(currNode == null) {
                    break;
                }

            }
        }
        return maxScore;
    }

    public Integer getContextScore(List<String> contentList, WhiteList whiteList) {
        int score = 0;
        for (String content : contentList) {
            Integer contextScore = getContextScore(content, whiteList);
            if (score < contextScore) {
                score = contextScore;
            }
        }
        return score;
    }

    /**
     * 处理只包含String对象的数据对象
     *
     * @param clazz
     * @param target
     * @param whiteList
     * @return
     */
    public Integer getContextScore(Class<?> clazz, Object target, WhiteList whiteList) {
        List<String> contentList = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                Method m = clazz.getMethod("get" + name);
                Object res = m.invoke(target);
                contentList.add(res.toString());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        int score = 0;
        for (String content : contentList) {
            Integer contextScore = getContextScore(content, whiteList);
            if (score < contextScore) {
                score = contextScore;
            }
        }
        return score;
    }


}
