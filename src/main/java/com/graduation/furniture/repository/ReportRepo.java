package com.graduation.furniture.repository;

import com.graduation.furniture.dto.*;
import com.graduation.furniture.entities.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepo extends JpaRepository<OrderUser, Integer> {
    @Query(value = "SELECT calendar.date AS time, COALESCE(SUM(CASE WHEN (ou.status_id = 2 OR ou.status_id IS NULL) THEN COALESCE(ou.total_order, 0) ELSE 0 END), 0) AS revenue " +
            "            FROM ( " +
            "               SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date " +
            "               FROM ( " +
            "                        SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "                    ) AS a " +
            "                        CROSS JOIN ( " +
            "                   SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "               ) AS b " +
            "                        CROSS JOIN ( " +
            "                   SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "               ) AS c " +
            "               WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate " +
            "                ) AS calendar " +
            "               LEFT JOIN order_user ou ON DATE(ou.updated_at) = calendar.date " +
            "            GROUP BY calendar.date " +
            "            ORDER BY calendar.date", nativeQuery = true)
    List<RevenueDto> revenueByPeriodTime(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT calendar.date AS time,COALESCE(SUM(CASE WHEN (ou.status_id = 2) THEN 1 ELSE 0 END), 0) AS totalOrders\n" +
            "FROM (\n" +
            "         SELECT DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date\n" +
            "         FROM (\n" +
            "                  SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9\n" +
            "              ) AS a\n" +
            "                  CROSS JOIN (\n" +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9\n" +
            "         ) AS b\n" +
            "                  CROSS JOIN (\n" +
            "             SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9\n" +
            "         ) AS c\n" +
            "         WHERE DATE(:startDate) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY <= :endDate\n" +
            "     ) AS calendar\n" +
            "         LEFT JOIN order_user ou ON DATE(ou.updated_at) = calendar.date\n" +
            "GROUP BY calendar.date\n" +
            "ORDER BY calendar.date;", nativeQuery = true)
    List<OrderDto> totalOrderByPeriodTime(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query(value = "select COALESCE(sum(oi.payment_order_item),0) from product p\n" +
            "join order_item oi on p.product_id = oi.product_id\n" +
            "join order_user ou on ou.order_id = oi.order_id\n" +
            "where ou.status_id = 2 and (date(ou.updated_at) between :startDate and :endDate);", nativeQuery = true)
    String totalRevenueByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select COALESCE(count(ou.order_id),0) from order_user ou\n" +
            "where ou.status_id = 2 and  (date(ou.updated_at) between :startDate and :endDate);", nativeQuery = true)
    String totalOrderByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT COALESCE(COUNT(DISTINCT ou.user_name),0) AS count_records\n" +
            "FROM order_user ou\n" +
            "WHERE ou.status_id = 2 AND (DATE(ou.updated_at) BETWEEN :startDate AND :endDate);", nativeQuery = true)
    String totalCustomerByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT COALESCE(COUNT(DISTINCT u.user_name),0) FROM users u\n" +
            "JOIN user_role ur on u.user_name = ur.user_name\n" +
            "WHERE (created_at BETWEEN :startDate AND :endDate)\n" +
            "AND ur.role_id = 2;", nativeQuery = true)
    String totalNewUserByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT c.category_name as name, sum(oi.payment_order_item) as revenue FROM category c\n" +
            "     JOIN product p on c.category_id = p.category_id\n" +
            "     JOIN order_item oi on p.product_id = oi.product_id\n" +
            "     JOIN order_user ou on oi.order_id = ou.order_id\n" +
            "where ou.status_id = 2 and (DATE(ou.updated_at) BETWEEN :startDate AND :endDate)\n" +
            "GROUP BY c.category_id\n" +
            "ORDER BY revenue DESC\n" +
            "LIMIT 6", nativeQuery = true)
    List<CategoryReportDto> topCategoriesByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT p.product_id as productId, p.image as image, p.product_name as productName, p.price as price,\n" +
            "       p.inventory_quantity as quantity, sum(oi.payment_order_item) as revenue,\n" +
            "       count(ou.order_id) as orders, c.category_name as categoryName\n" +
            "            FROM product p  \n" +
            "            JOIN order_item oi on p.product_id = oi.product_id  \n" +
            "            JOIN order_user ou on oi.order_id = ou.order_id\n" +
            "            JOIN category c on p.category_id = c.category_id\n" +
            "            where ou.status_id = 2 and (DATE(ou.updated_at) BETWEEN :startDate AND :endDate)  \n" +
            "            GROUP BY p.product_id  \n" +
            "            ORDER BY revenue DESC", nativeQuery = true)
    List<TopProductReportDto> topProductByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT p.product_id as productId, p.image as image, p.product_name as productName, p.price as price, \n" +
            "              p.inventory_quantity as quantity, sum(oi.quantity) as soldQuantity,\n" +
            "              count(ou.order_id) as orders, c.category_name as categoryName \n" +
            "                   FROM product p   \n" +
            "                   JOIN order_item oi on p.product_id = oi.product_id   \n" +
            "                   JOIN order_user ou on oi.order_id = ou.order_id \n" +
            "                   JOIN category c on p.category_id = c.category_id \n" +
            "                   where ou.status_id = 2 and (DATE(ou.updated_at) BETWEEN :startDate AND :endDate)   \n" +
            "                   GROUP BY p.product_id   \n" +
            "                   ORDER BY soldQuantity DESC", nativeQuery = true)
    List<TopProductTrendingDto> trendingProductByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT u.user_name as username, u.first_name as firstName, u.last_name as lastName , u.avatar as avatar,\n" +
            "       count(ou.order_id) as orders ,sum(ou.total_order) as spending FROM users u\n" +
            "JOIN order_user ou on u.user_name = ou.user_name\n" +
            "where ou.status_id = 2 and (DATE(ou.updated_at) BETWEEN :startDate AND :endDate)\n" +
            "GROUP BY u.user_name\n" +
            "ORDER BY spending desc;", nativeQuery = true)
    List<TopUserReportDto> topUserByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select oi.order_id as orderId, u.user_name as username, avatar , u.first_name as firstName, u.last_name as lastName,\n" +
            "       oi.product_id as productId, p.product_name as productName, oi.payment_order_item as amount,\n" +
            "       ou.status_id as statusId, oi.feedback_status as feedbackStatus, f.rating as rating, p.price as price,\n" +
            "       oi.quantity as quantity\n" +
            "from order_user ou\n" +
            "         JOIN order_item oi on ou.order_id = oi.order_id\n" +
            "         JOIN product p on oi.product_id = p.product_id\n" +
            "         JOIN users u on ou.user_name = u.user_name\n" +
            "         JOIN feedback f on p.product_id = f.product_id\n" +
            "where (DATE(ou.updated_at) BETWEEN :startDate AND :endDate)\n" +
            "group by ou.order_id, p.product_id, ou.created_at\n" +
            "order by ou.created_at desc;", nativeQuery = true)
    List<OrderRecentReportDto> orderRecentByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
