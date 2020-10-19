package com.xu.whiteList;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DFANode {

    private char currChar;

    private boolean end;

    private HashMap<Character, DFANode> childs;

    private Integer score;

    public DFANode(char currChar) {
        this.currChar = currChar;
    }

    public void addChild(DFANode child){
        if (this.childs == null) {
            childs = Maps.newHashMap();
        }
        this.childs.put(child.getCurrChar(), child);
    }

    public void removeChild(DFANode child) {
        if (this.childs != null) {
            this.childs.remove(child.getCurrChar());
        }
    }



}
