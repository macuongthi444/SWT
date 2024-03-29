package DAO;

import Model.Brand;
import Model.Category;
import Model.Color;
import Model.Coupon;
import Model.GraphicCard;
import Model.HardwareMemory;
import Model.Image;
import Model.Option;
import Model.Product;
import Model.ProductOption;
import Model.ProductWithImage;
import Model.RamMemory;
import Model.Resolution;
import Model.ScreenSize;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public static final ProductDAO INSTANCE = new ProductDAO();
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

//    public void updateImage(int imageId, String imageText, int productOptionId){
//        
//    }
    public boolean checkProductOptionIsExist(int productId, int brandId, int hardwareMemoryId, int ramMemoryId, int colorId, int screenSizeId, int resolutionId, int graphicCardId) {
        String sql = "select * from product_Option where productId = ? and brandId = ? and hardwareMemoryId = ? and ramMemoryId = ? and colorId = ? and "
                + "screenSizeId = ? and resolutionid = ? and graphicCardId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, brandId);
            ps.setInt(3, hardwareMemoryId);
            ps.setInt(4, ramMemoryId);
            ps.setInt(5, colorId);
            ps.setInt(6, screenSizeId);
            ps.setInt(7, resolutionId);
            ps.setInt(8, graphicCardId);
            ResultSet rst = ps.executeQuery();
            if (rst.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error at checkProductOptionIsExist " + e.getMessage());
            return true;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public void deleteImageByProductOptionId(int productOptionId) {
        String sql = "delete from [Image] where product_optionId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at deleteImageByProductOptionId " + e.getMessage());
        }
    }

    public Product getProductById(int productId) {
        String sql = "select * from product where productId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rst = ps.executeQuery();
            if (rst.next()) {
                return new Product(rst.getInt("productId"), rst.getString("productName"), getCategoryById(rst.getInt("categoryId")), rst.getString("productDetail"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductById " + e.getMessage());
        }
        return null;
    }

    public void deleteProductOption(int productOptionid) {
        String sql = "delete from product_option where productOptionid = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionid);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at deleteProductOption");
        }
    }

    public void deleteProduct(int productId) {
        String sql = "delete from product where productId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at deleteProduct " + e.getMessage());

        }
    }

    public void deleteImage(int imageId) {
        String sql = "delete from [image] where imageId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, imageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error at deleteImage " + e.getMessage());
        }
    }

    public void updateProduct(int productId, String productName, int categoryId, String productDetail) {
        String sql = "update product set productName = ?, categoryId = ?, productDetail = ? where productId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setInt(2, categoryId);
            ps.setString(3, productDetail);
            ps.setInt(4, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error at updateProduct " + e.getMessage());
        }
    }

    public void updateProductOption(int productOptionId, int productId, int brandId, int hardwareMemoryId, int ramMemoryId, int colorId, int screenSizeId, int resolutionId, int graphicCardId, double price, int numberInStock, int quantitySold) {
        String sql = "update product_option set brandId = ?, hardwareMemoryId = ?, ramMemoryId = ?, colorId = ?, screenSizeId = ?, resolutionId = ?, graphicCardId = ?, "
                + "price = ?, numberInStock = ?, quantitySold = ? where productOptionId = ? and productId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, brandId);
            ps.setInt(2, hardwareMemoryId);
            ps.setInt(3, ramMemoryId);
            ps.setInt(4, colorId);
            ps.setInt(5, screenSizeId);
            ps.setInt(6, resolutionId);
            ps.setInt(7, graphicCardId);
            ps.setDouble(8, price);
            ps.setInt(9, numberInStock);
            ps.setInt(10, quantitySold);
            ps.setInt(11, productOptionId);
            ps.setInt(12, productId);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at updateProductOption " + e.getMessage());
        }
    }

    public ProductOption getProductOptionById(int id) {
        String sql = "select * from product_option where productOptionid = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductOption(rs.getInt("productOptionId"), rs.getInt("productId"), rs.getInt("brandId"), rs.getInt("hardwareMemoryId"), rs.getInt("ramMemoryId"),
                        rs.getInt("colorId"), rs.getInt("screensizeId"), rs.getInt("resolutionId"), rs.getInt("graphicCardId"), rs.getDouble("price"),
                        rs.getInt("numberInStock"), rs.getInt("quantitySold"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductOptionById");
        }
        return null;
    }

    public List<Image> getImagesByProductOptionId(int productOptionId) {
        String sql = "select * from [image] where product_optionId = ?";
        List<Image> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Image(rs.getInt("imageId"), rs.getString("imageText"), rs.getInt("product_optionId")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getImagesByProductOptionId " + e.getMessage());
        }
        return list;
    }

    public void insertProductOption(int productId, int brandId, int hardwareMemoryId, int ramMemoryId, int colorId, int screenSizeId,
            int resolutionId, int graphicCardId, double price, int numberInStock, int quantitySold) {
        String sql = "insert into product_Option values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, brandId);
            ps.setInt(3, hardwareMemoryId);
            ps.setInt(4, ramMemoryId);
            ps.setInt(5, colorId);
            ps.setInt(6, screenSizeId);
            ps.setInt(7, resolutionId);
            ps.setInt(8, graphicCardId);
            ps.setDouble(9, price);
            ps.setInt(10, numberInStock);
            ps.setInt(11, quantitySold);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.InsertProduct_Option " + e.getMessage());
        }
    }

    public List<ProductOption> getProductOptionByProductId(int productId) {
        String sql = "select * from product_option where productId = ?";
        List<ProductOption> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductOption(rs.getInt("productOptionId"), rs.getInt("productId"), rs.getInt("brandId"), rs.getInt("hardwareMemoryId"), rs.getInt("ramMemoryId"),
                        rs.getInt("colorId"), rs.getInt("screensizeId"), rs.getInt("resolutionId"), rs.getInt("graphicCardId"), rs.getDouble("price"),
                        rs.getInt("numberInStock"), rs.getInt("quantitySold")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductOptionByProductId");
        }
        return list;
    }

    public List<ProductOption> getOtherProductOptionByProductId(int productOptionid) {
        String sql = "select * from product_option where productId = (select productId from Product_Option where productOptionId = ?) and productOptionId != ?";
        List<ProductOption> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionid);
            ps.setInt(2, productOptionid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductOption(rs.getInt("productOptionId"), rs.getInt("productId"), rs.getInt("brandId"), rs.getInt("hardwareMemoryId"), rs.getInt("ramMemoryId"),
                        rs.getInt("colorId"), rs.getInt("screensizeId"), rs.getInt("resolutionId"), rs.getInt("graphicCardId"), rs.getDouble("price"),
                        rs.getInt("numberInStock"), rs.getInt("quantitySold")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductOptionByProductId " + e.getMessage());
        }
        return list;
    }

    public Product getProductByProductOptionId(int productOptionId) {
        String sql = "select * from Product where productId = (select productId from Product_Option where productOptionId = ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionId);
            ResultSet rst = ps.executeQuery();
            if (rst.next()) {
                return new Product(rst.getInt("productId"), rst.getString("productName"), getCategoryById(rst.getInt("categoryId")), rst.getString("productDetail"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductByProductOptionId " + e.getMessage());
        }
        return null;
    }

    public List<Product> getProductList() {
        String sql = "select * from Product";
        List<Product> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list.add(new Product(rst.getInt("productId"), rst.getString("productName"), getCategoryById(rst.getInt("categoryId")), rst.getString("productDetail")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductList " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "select * from category where categoryId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(id, rs.getString("categoryName"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getCategoryById " + e.getMessage());
        }
        return null;
    }

    public Coupon getCouponById(int id) {
        String sql = "select * from coupon where couponId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Coupon(rs.getInt("couponId"), rs.getDouble("discountRate"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getBoolean("isUsed"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getCouponById " + e.getMessage());
        }
        return null;
    }

    public List<ScreenSize> getScreenSizeList() {
        String sql = "select * from ScreenSize";
        List<ScreenSize> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ScreenSize(rs.getInt("screenSizeId"), rs.getString("screenSize")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getScreenSizeList");
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<GraphicCard> getGraphicCardList() {
        String sql = "select * from GraphicCard";
        List<GraphicCard> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new GraphicCard(rs.getInt("graphicCardId"), rs.getString("graphicCard")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getGraphicCard " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Resolution> getResolutionList() {
        String sql = "select * from resolution";
        List<Resolution> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Resolution(rs.getInt("resolutionId"), rs.getString("resolution")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getResolutionList " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<ProductOption> getProductOptionList() {
//        String sql = "select po.productOptionId, p.productId, p.productName, p.categoryId, p.productDetail, c.color, hm.hardwareMemory, "
//                + "rm.ramMemory, b.brandName, sc.screenSize, r.resolution, gc.graphicCard from Brand b, Product_Option po, product p, "
//                + "HardwareMemory hm, RamMemory rm,\n"
//                + " Color c, ScreenSize sc, Resolution r, GraphicCard gc where po.productId = p.productId and po.hardwareMemoryId = hm.hardwareMemoryId and po.resolutionId = r.resolutionId\n"
//                + " and po.ramMemoryId = rm.ramMemoryId and po.colorId = c.colorId and po.brandId = b.brandId and po.graphicCardId = gc.graphicCardId and po.ScreenSizeId = sc.screenSizeId\n";
//        String sql = "select * from Brand b, Product_Option po, product p, "
//                + "HardwareMemory hm, RamMemory rm,\n"
//                + " Color c, ScreenSize sc, Resolution r, GraphicCard gc where po.productId = p.productId and po.hardwareMemoryId = hm.hardwareMemoryId and po.resolutionId = r.resolutionId\n"
//                + " and po.ramMemoryId = rm.ramMemoryId and po.colorId = c.colorId and po.brandId = b.brandId and po.graphicCardId = gc.graphicCardId and po.ScreenSizeId = sc.screenSizeId\n";
        String sql = "select * from product_Option";
        List<ProductOption> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductOption(rs.getInt("productOptionId"), rs.getInt("productId"), rs.getInt("brandId"), rs.getInt("hardwareMemoryId"), rs.getInt("ramMemoryId"),
                        rs.getInt("colorId"), rs.getInt("screensizeId"), rs.getInt("resolutionId"), rs.getInt("graphicCardId"), rs.getDouble("price"),
                        rs.getInt("numberInStock"), rs.getInt("quantitySold")));
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductOptionList " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public int getProductQuantitySold() {
        return 0;
    }

    public Color getColorById(int id) {
        String sql = "select * from color where colorId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Color(rs.getInt("colorId"), rs.getString("color"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getColorById " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Brand getBrandById(int id) {
        String sql = "select * from brand where brandId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getInt("brandId"), rs.getString("brandName"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getBrandById " + e.getMessage());
        } finally {
            if (ps != null)
                try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public GraphicCard getGraphicCardById(int id) {
        String sql = "select * from graphicCard where graphicCardId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new GraphicCard(rs.getInt("graphicCardId"), rs.getString("graphicCard"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getGraphicCardById " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public HardwareMemory getHardwareMemoryById(int id) {
        String sql = "select * from hardwareMemory where hardwareMemoryId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new HardwareMemory(rs.getInt("hardwareMemoryId"), rs.getString("hardwareMemory"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getGraphicCardById " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public RamMemory getRamMemoryById(int id) {
        String sql = "select * from ramMemory where ramMemoryId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new RamMemory(rs.getInt("ramMemoryId"), rs.getString("ramMemory"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getRamMemoryById " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Resolution getResolutionById(int id) {
        String sql = "select * from resolution where resolutionId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Resolution(rs.getInt("resolutionId"), rs.getString("resolution"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getResolutionById " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ScreenSize getScreenSizeById(int id) {
        String sql = "select * from screenSize where screenSizeId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new ScreenSize(rs.getInt("screenSizeId"), rs.getString("screenSize"));
            }
        } catch (SQLException e) {
            System.out.println("Error at getScreenSizeById " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

//    public List<Object> getProductOptionList(){
////        String sql = "select * From product_option";
//        String sql = "select p.productId, p.productName, c.color, hm.hardwareMemory, rm.ramMemory, b.brandName from Brand b, Product_Option po, product p, HardwareMemory hm, RamMemory rm, Color c where po.productId = p.productId and po.hardwareMemoryId = hm.hardwareMemoryId\n" +
//"	and po.ramMemoryId = rm.ramMemoryId and po.colorId = c.colorId and po.brandId = b.brandId";
//        List<Object> list = new ArrayList();
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                list.add(new Object(){
//                    public final String name = rs.getString("productName");
//                    public final String color = rs.getString("color");
//                    public final String hardwareMemory = rs.getString("hardwareMemory");
//                    public final String ramMemory = rs.getString("ramMemory");
//                    public final String brandName = rs.getString("brandName");
//                    @Override
//                    public String toString(){
//                        return name + " " + color + " " + hardwareMemory;
//                    }
//                    public String getName(){
//                        return name;
//                    }
//                });
//            }
//        } catch (SQLException e) {
//            System.out.println("Error at getProductOptionList " + e.getMessage());
//        }
//        return list;
//    }
    public List<Color> getColorList() {
        String sql = "select * from color";
        List<Color> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Color(rs.getInt("colorId"), rs.getString("color")));
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getColorList " + e.getMessage());

        } finally {
            if (ps != null)
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<RamMemory> getRamMemoryList() {
        String sql = "select * from RamMemory";
        List<RamMemory> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RamMemory(rs.getInt("ramMemoryId"), rs.getString("ramMemory")));
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getRamMemoryList " + e.getMessage());

        } finally {
            if (ps != null)
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<HardwareMemory> getHardwareMemoryList() {
//        System.out.println("skjefhjskjfh");
        String sql = "select * from hardwareMemory";
        List<HardwareMemory> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HardwareMemory(rs.getInt("hardwareMemoryId"), rs.getString("hardwareMemory")));
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getHardwareMemoryList " + e.getMessage());

        } finally {
            if (ps != null)
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Brand> getBrandList() {
        String sql = "select * from brand";
        List<Brand> brandList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                brandList.add(new Brand(rs.getInt("brandId"), rs.getString("brandName")));
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getBrandList " + e.getMessage());
        } finally {
            if (ps != null)
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return brandList;
    }

    public Option getOptionIdByName(String optionName) {
        String sql = "select * from [Option] where optionName = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, optionName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Option(rs.getInt("optionId"), rs.getString("optionName"));
            }
        } catch (SQLException e) {
            System.out.println("Error at productDAO.getOptionIdByName " + e.getMessage());
        }
        return null;
    }

    public int getProductOptionId(int productId, int brandId, int hardwareMemoryId, int ramMemory, int colorId, int screenSizeId, int resolutionId, int graphicCardId) {
        String sql = "select * From product_option where productId = ? and brandId = ? and hardwareMemoryId = ? and ramMemoryId = ? and colorId = ? and "
                + "screenSizeId = ? and resolutionId = ? and graphicCardId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, brandId);
            ps.setInt(3, hardwareMemoryId);
            ps.setInt(4, ramMemory);
            ps.setInt(5, colorId);
            ps.setInt(6, screenSizeId);
            ps.setInt(7, resolutionId);
            ps.setInt(8, graphicCardId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("productOptionId");
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductOptionid " + e.getMessage());
        }
        return -1;
    }

    public int getProductOptionId(int productId, int optionId, String optionDetail) {
        String sql = "select * from product_option where productId = ? and optionId = ? and optionDetail = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, optionId);
            ps.setString(3, optionDetail);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("productOptionId");
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getProductOptionId ");
        }
        return -1;
    }

    public int getProductOptionId(int productId, String optionDetail) {
        String sql = "select * from product_option where productId = ? and optionDetail = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setString(2, optionDetail);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("productOptionId");
            }
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getProductOptionId ");
        }
        return -1;
    }

    public void insertProduct(int id, String productName, int categoryId, String productDetail) {
        String sql = "insert into Product values(?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, productName);
            ps.setInt(3, categoryId);
            ps.setString(4, productDetail);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.insertProduct " + e.getMessage());
        }
    }

    public void insertOption(String optionName) {
        String sql = "insert into [option] values (?) ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, optionName);
            ps.execute();
        } catch (SQLException e) {
            status = "Error at ProductDAO.insertOption " + e.getMessage();
            System.out.println(status);
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public void insertProductOption(int productId, int optionId, String optionDetail, double price, int numberInStock, int quantitySold) {
//        String sql = "insert into [product_option] values (?, ?, ?, ?, ?, ?) ";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, productId);
//            ps.setInt(2, optionId);
//            ps.setString(3, optionDetail);
//            ps.setDouble(4, price);
//            ps.setInt(5, numberInStock);
//            ps.setInt(6, quantitySold);
//            ps.execute();
//        } catch (SQLException e) {
//            status = "Error at ProductDAO.insertProductOption " + e.getMessage();
//            System.out.println(status);
//        }
//    }
//    public void insertProductOption(int productId, String optionDetail, double price, int numberInStock, int quantitySold){
//        String sql = "insert into [product_option] values (?, ?, ?, ?, ?, ?) ";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, productId);
//            ps.setString(2, optionDetail);
//            ps.setDouble(3, price);
//            ps.setInt(4, numberInStock);
//            ps.setInt(5, quantitySold);
//            ps.execute();
//        } catch (SQLException e) {
//            status = "Error at ProductDAO.insertProductOption " + e.getMessage();
//            System.out.println(status);
//        }
//    }
    public Category getCategoryByName(String categoryName) {
        String sql = "select * from category where categoryName = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("categoryId"), categoryName);
            }
        } catch (SQLException e) {
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int getProductQuantitySold(int productId, int optionId, String optionDetail) {
        String sql = "";
        return 0;
    }

    public int getProductQuantitySold(int productId, String optionDetail) {
        String sql = "";
        return 0;
    }

    public int getProductNumberInStock() {
        return 0;
    }

    public boolean checkOptionNameIsExist(String optionName) {
        String sql = "select * from [option] where optionName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, optionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error at checkoutOptionnameIsExist " + e.getMessage());
        }
        return false;
    }

    public void insertImage(String imageText, int productOptionId) {
        String sql = "insert into Image values(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, imageText);
            ps.setInt(2, productOptionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at productDAO.insertImage " + e.getMessage();
            System.out.println(status);
        }

    }

    public List<ProductWithImage> getProductListWithImage() {
        String sql = "select distinct p.productId,p.productName,p.productDetail,i.imageText,po.price,po.numberInStock "
                + "from Product p, Product_Option po,Image i where p.productId=po.productId "
                + "and po.productOptionId=i.product_OptionId";
        List<ProductWithImage> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductWithImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<ProductWithImage> NewestProductWithImage() {
        String sql = "select distinct top 4  p.productId,p.productName,p.productDetail,i.imageText,po.price,po.numberInStock\n"
                + "from Product p, Product_Option po,Image i where p.productId=po.productId \n"
                + "and po.productOptionId=i.product_OptionId order by p.productId desc";
        List<ProductWithImage> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductWithImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Image> getImageListByProductOptionId(int productOptionid) {
        String sql = "select * from [image] where product_OptionId = ?";
        List<Image> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productOptionid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Image(rs.getInt("imageId"), rs.getString("imageText"), productOptionid));
            }
        } catch (SQLException e) {
            System.out.println("Error at getImageListByProductOptionId " + e.getMessage());
        }
        return list;
    }

    public ProductWithImage getProductWithImageByPid(int pid) {

        String sql = "SELECT DISTINCT p.productId,p.productName, p.productDetail, i.imageText, po.price, po.numberInStock\n"
                + "FROM Product p\n"
                + "JOIN Product_Option po ON p.productId = po.productId\n"
                + "JOIN Image i ON po.productOptionId = i.product_OptionId\n"
                + "WHERE p.productId = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductWithImage p = new ProductWithImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));
                return p;
            }

        } catch (Exception e) {
        }
        return null;
    }
    
    

    public static void main(String[] args) {
//        System.out.println(ProductDAO.INSTANCE.checkOptionNameIsExist("color"));
//        ProductDAO.INSTANCE.insertProduct(3, "test", 1, null);
        System.out.println(ProductDAO.INSTANCE.getCategoryByName("mouse"));
//        System.out.println(ProductDAO.INSTANCE.getProductOptionId(1, ProductDAO.INSTANCE.getOptionIdByName("color").getOptionId(), "red"));
        ProductDAO.INSTANCE.getBrandList().forEach((e) -> System.out.print(e + " "));
        System.out.println("");
        ProductDAO.INSTANCE.getHardwareMemoryList().forEach((e) -> System.out.print(e + " "));
        System.out.println("");
        ProductDAO.INSTANCE.getRamMemoryList().forEach((e) -> System.out.print(e + " "));
        System.out.println("");

        ProductDAO.INSTANCE.getResolutionList().forEach((e) -> {
            System.out.println(e);
        });
        ProductDAO.INSTANCE.getGraphicCardList().forEach((e) -> {
            System.out.println(e);
        });
        ProductDAO.INSTANCE.getScreenSizeList().forEach((e) -> {
            System.out.println(e);
        });
        ProductDAO.INSTANCE.getProductOptionList().forEach((e) -> System.out.println(e));
    }
}
