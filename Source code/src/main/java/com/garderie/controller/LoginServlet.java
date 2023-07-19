package com.garderie.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.garderie.util.Connexion;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String cin = request.getParameter("cin");
        String motDePasse = request.getParameter("motDePasse");
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("select * from employe where cin=? and motDePasse=?");
            ps.setString(1, cin);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String is_directeur = rs.getString("is_directeur");
                if(is_directeur.equals("1")){ //1 => Directeur(Admin) | 0 => Utilisateur simple
                    request.setAttribute("cin", cin);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardAdmin.jsp");
                    dispatcher.forward(request, response);
                }else {
                    request.setAttribute("cin", cin);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                    dispatcher.forward(request, response);
                }
            }else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Nom d utilisateur ou mot de passe incorrect');");
                out.println("location='index.jsp';");
                out.println("</script>");
            }
            out.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}
