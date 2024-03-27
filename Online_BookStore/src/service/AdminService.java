package service;

import dao.AdminDao;
import model.Admin;
import java.sql.Connection;
import java.util.List;

public class AdminService {
    // Create a new admin
    public static void createAdmin(Admin admin, Connection connection) {
        AdminDao.createAdmin(connection, admin);
    }

    // Retrieve all admins
    public static List<Admin> getAllAdmins(Connection connection) {
        return AdminDao.getAllAdmins(connection);
    }

    // Update admin information
    public static void updateAdmin(Admin admin, Connection connection) {
        AdminDao.updateAdmin(connection, admin);
    }

    // Delete an admin by ID
    public static void deleteAdminById(int adminId, Connection connection) {
        AdminDao.deleteAdminById(connection, adminId);
    }
}
