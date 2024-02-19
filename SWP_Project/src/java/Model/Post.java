/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author hoang
 */
public class Post {

    private int postId;
    private String postTitle;
    private String postImg;
    private String postDetail1;
    private String postDetail2;
    private Date postStart;
    private Date postEnd;
    private PostType postTypeID;
    private Account postByUserMarketing ;

    public Post() {
    }

    public Post(int postId, String postTitle, String postImg, String postDetail1, String postDetail2, Date postStart, Date postEnd, PostType postTypeID, Account postByUserMarketing) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postImg = postImg;
        this.postDetail1 = postDetail1;
        this.postDetail2 = postDetail2;
        this.postStart = postStart;
        this.postEnd = postEnd;
        this.postTypeID = postTypeID;
        this.postByUserMarketing = postByUserMarketing;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getPostDetail1() {
        return postDetail1;
    }

    public void setPostDetail1(String postDetail1) {
        this.postDetail1 = postDetail1;
    }

    public String getPostDetail2() {
        return postDetail2;
    }

    public void setPostDetail2(String postDetail2) {
        this.postDetail2 = postDetail2;
    }

    public Date getPostStart() {
        return postStart;
    }

    public void setPostStart(Date postStart) {
        this.postStart = postStart;
    }

    public Date getPostEnd() {
        return postEnd;
    }

    public void setPostEnd(Date postEnd) {
        this.postEnd = postEnd;
    }

    public PostType getPostTypeID() {
        return postTypeID;
    }

    public void setPostTypeID(PostType postTypeID) {
        this.postTypeID = postTypeID;
    }

    public Account getPostByUserMarketing() {
        return postByUserMarketing;
    }

    public void setPostByUserMarketing(Account postByUserMarketing) {
        this.postByUserMarketing = postByUserMarketing;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", postTitle=" + postTitle + ", postImg=" + postImg + ", postDetail1=" + postDetail1 + ", postDetail2=" + postDetail2 + ", postStart=" + postStart + ", postEnd=" + postEnd + ", postTypeID=" + postTypeID + ", postByUserMarketing=" + postByUserMarketing + '}';
    }

    
}
