package gestion.ventas.entities.beans;

import gestion.ventas.entities.beans.util.DataConnect;
import gestion.ventas.entities.beans.util.SessionBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergio Alcaraz
 */
// @ManagedBean
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private boolean logueado = false;

    public LoginController() {
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean validate(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select nombre, "
                    + "password "
                    + "from usuario "
                    + "where nombre = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public String validateUsernamePassword() {
        boolean valid = validate(username, password);

        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", username);
            this.logueado = true;
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario y Contraseña invalida",
                            "Por favor ingrese Usuario y Contraseña"));
            return "Login";
        }
    }

    //logout
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "Login";
    }
}
