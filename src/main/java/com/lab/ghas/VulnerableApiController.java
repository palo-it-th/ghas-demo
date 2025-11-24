package com.lab.ghas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * VULNERABLE CODE - Intentionally insecure for demo purposes
 * This controller demonstrates vulnerabilities that will be caught by:
 * 1. CodeQL - SQL Injection vulnerability
 * 2. Secret Scanning - Hardcoded database credentials
 */
@RestController
public class VulnerableApiController {
    
    // VULNERABILITY 1: Hardcoded credentials (Secret Scanning will catch this)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "SuperSecret123!";
    
    /**
     * VULNERABILITY 2: SQL Injection (CodeQL will catch this)
     * This endpoint is vulnerable to SQL injection attacks
     * Example exploit: /api/user?username=admin' OR '1'='1
     */
    @GetMapping("/api/user")
    public String getUserData(@RequestParam String username) {
        try {
            // INSECURE: Direct string concatenation in SQL query
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            
            // SQL INJECTION VULNERABILITY
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);
            
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                result.append("User: ").append(rs.getString("username"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append("\n");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return result.toString();
            
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * VULNERABILITY 3: Cross-Site Scripting (XSS)
     * Unescaped user input returned in HTML response
     * Example exploit: /api/greet?name=<script>alert('XSS')</script>
     */
    @GetMapping("/api/greet")
    public String greetUser(@RequestParam String name) {
        // XSS VULNERABILITY: User input not sanitized
        return "<html><body><h1>Hello, " + name + "!</h1></body></html>";
    }
    
    /**
     * VULNERABILITY 4: Path Traversal
     * Allows reading arbitrary files from the filesystem
     * Example exploit: /api/file?path=../../etc/passwd
     */
    @GetMapping("/api/file")
    public String readFile(@RequestParam String path) {
        try {
            // PATH TRAVERSAL VULNERABILITY
            java.io.File file = new java.io.File(path);
            java.util.Scanner scanner = new java.util.Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return content.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
