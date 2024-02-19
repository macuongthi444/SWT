/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.Post;
import Model.PostType;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class PostDAO extends DBContext {

    public static final PostDAO INSTANCE = new PostDAO();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public PostType getPostTypeById(int id) {

        try {
            String sql = "select * from [PostType] where [postTypeID] = ?";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PostType(rs.getInt("postTypeID"), rs.getString("postTypeDetail"));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Post> getAllPost() {
        ArrayList<Post> post = new ArrayList<>();

        try {
            String sql = "select * from Post ";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                post.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                ));
            }

        } catch (Exception e) {

        }
        return post;
    }
    public List<Post> getAllPostDesc() {
        ArrayList<Post> post = new ArrayList<>();

        try {
            String sql = "select * from Post ORDER BY [postId] DESC;";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                post.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                ));
            }

        } catch (Exception e) {

        }
        return post;
    }

    public List<Post> getPostTop3() {
        ArrayList<Post> post = new ArrayList<>();

        try {
            String sql = "  select top 3*from  [Post]  ORDER BY [postId] DESC;";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                post.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                ));
            }

        } catch (Exception e) {

        }
        return post;
    }

    public Post getPostById(int id) {

        try {
            String sql = "select * from Post where postId=?";
            conn = new DBContext().connection;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                Post post = new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                );
                return post;
            }

        } catch (Exception e) {

        }
        return null;
    }

    public List<Post> searchPostByTitle(String searchTxt) {
        List<Post> list = new ArrayList<>();

        String sql = "select *from [Post] \n"
                + "                  where [postTitle] like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (searchTxt == null || searchTxt.isEmpty()) {
                searchTxt = "";
            }
            ps.setString(1, "%" + searchTxt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                ));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<PostType> getAllPostType() {
        List<PostType> list = new ArrayList<>();

        String sql = "select * from [PostType]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PostType(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Post> getPostByType(String type) {
        List<Post> list = new ArrayList<>();

        String sql = "select  *from Post p join PostType pt on p.postTypeID=pt.postTypeID\n"
                + "  where pt.postTypeDetail  like ?";
        try {
            if (type == null || type.isEmpty()) {
                type = "";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + type + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        PostDAO.INSTANCE.getPostTypeById(rs.getInt("postTypeID")),
                        UserDao.INSTANCE.getAccountById(rs.getInt("postByUserMarketing"))
                ));

            }
        } catch (SQLException e) {

        }
        return list;
    }

//    public void addPost(String postTitle, , String postDetail1, String postDetail2, Date postStart, Date postEnd, int postTypeID, int postByUserMarketing) {
//        String sql = "insert into Post values(?,?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, postTitle);
//            ps.setString(2, password);
//            ps.setString(3, postDetail1 );
//            ps.setString(4, postDetail2);
//            ps.setDate(5, postStart);
//            ps.setDate(6, postEnd);
//            ps.setInt(7, postTypeID);
//            ps.setInt(8, postByUserMarketing);
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//        }
//    }

    public static void main(String[] args) {
        PostDAO p = new PostDAO();
        PostType pt = p.getPostTypeById(1);
        List<Post> pl = p.getAllPost();
        Post pid = p.getPostById(1);
        List<PostType> pt1 = p.getAllPostType();
        List<Post> pbyid = p.getPostByType("Hot");
        System.out.println(pbyid);
        //System.out.println(pt1);
        //  System.out.println(pid);
        // System.out.println(pl);
        //System.out.println(pt);

    }
}
