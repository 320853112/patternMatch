package com.xu.whiteList;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

@Data
public class DFAWhiteList implements WhiteList{

    private HashMap<Character,DFANode> whiteList = new HashMap<Character, DFANode>();


    @Override
    public boolean add(Word word) {
        String content = word.getContent();
        if (StringUtils.isBlank(content)) {
            return false;
        }
        content = StringUtils.trim(content);

        char[] chars = content.toCharArray();
        HashMap<Character, DFANode> currMap = this.whiteList;
        DFANode nowNode;
        for (int i = 0; i < chars.length; i++) {
            nowNode = currMap.get(chars[i]);
            if (nowNode == null) {
                nowNode = new DFANode(chars[i]);
                if(i == chars.length-1){
                    nowNode.setEnd(true);
                    nowNode.setScore(word.getScore());
                }
                currMap.put(chars[i], nowNode);
            }
            if(nowNode.getChilds() == null){
                nowNode.setChilds(new HashMap<Character, DFANode>());
            }
            currMap = nowNode.getChilds();
        }
        return true;
    }


}
