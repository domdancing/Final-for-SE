
package com.mycompany.CodeforSefinal.Objects;


public class AuthenticationSingleton {
    
    private static AuthenticationSingleton instance;
    private boolean isAuthenticated;
    
    private AuthenticationSingleton() {
        this.isAuthenticated = false;   
}
    
    public static AuthenticationSingleton getInstance() {
        if (instance == null)
            instance = new AuthenticationSingleton();
        return instance; 
    }
    
    public boolean login(String username, String password){
        if ("admin".equals(username) && "admin".equals(password)) {
            this.isAuthenticated = true;
            return true;
        }
        return false;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }
    
}
