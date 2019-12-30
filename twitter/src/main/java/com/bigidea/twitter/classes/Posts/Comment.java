package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.enumerations.PostKind;

import java.util.ArrayList;

public class Comment extends Post{

    public Comment(){}

    public Comment(String content, ArrayList<HashTag> HashTags) {
        super(content, PostKind.COMMENT, HashTags);
    }

    public Comment(int Id, String content, ArrayList<HashTag> HashTags) {
        super(Id, content, PostKind.COMMENT, HashTags);
    }
}
