package com.garderie.controller;

import com.garderie.dao.ClasseDaoImpl;
import com.garderie.dao.EleveDaoImpl;
import com.garderie.model.Classe;
import com.garderie.model.Eleve;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ClasseServlet", value = "/ClasseServlet")
public class ClasseServlet extends HttpServlet {

    // Appeler ClasseDaoImpl pour accéder à la base de données
    private ClasseDaoImpl classeDaoImpl;
    @Override
    public void init() {
        classeDaoImpl = new ClasseDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MotCleToSearch = request.getParameter("search");
        String mySelectOption = request.getParameter("mySelectOption");
        if (MotCleToSearch != null){
            try {
                List<Classe> classesTrouves = classeDaoImpl.rechercherClasses(MotCleToSearch);
                request.setAttribute("classesTrouves", classesTrouves);
                RequestDispatcher dispatcher = request.getRequestDispatcher("modifierClasse.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if(mySelectOption != null){
            try {
                Classe classeToModifier = classeDaoImpl.getClasseById(Integer.parseInt(mySelectOption));
                request.setAttribute("classeToModifier", classeToModifier);
                RequestDispatcher dispatcher = request.getRequestDispatcher("modifierClasse.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si la demande POST est une demande simulée PUT => Rediriger la demande vers doPut()
        String method = request.getParameter("_method");
        if (method != null && method.equalsIgnoreCase("PUT")) {
            // Vérifier quelle action a été sélectionnée
            String action = request.getParameter("action");
            if ("modifier".equals(action)) {
                // Appeler la méthode doPut() pour modifier la classe
                doPut(request, response);
            } else if ("supprimer".equals(action)) {
                // Appeler la méthode doDelete() pour supprimer la classe
                doDelete(request, response);
            }
        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            //Récupération des valeurs des champs de formulaire
            String nomClasse = request.getParameter("nomClasse");

            // Créer un nouvel objet Classe avec ces valeurs
            Classe classe = new Classe(1,nomClasse);

            // Ajout classe à la base de données
            try {
                boolean rowInserted = classeDaoImpl.ajouterClasse(classe);
                if(rowInserted){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Classe ajouté avec succès');");
                    out.println("location='EleveServlet';");
                    out.println("</script>");
                    out.close();
                }else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Opération interrompu!');");
                    out.println("location='EleveServlet';");
                    out.println("</script>");
                    out.close();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            // récupérer les paramètres de la request
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");

            // créer un objet Classe modifié
            Classe classe = new Classe(id,nom);

            // mettre à jour la classe dans la base de données
            classeDaoImpl.modifierClasse(classe);

            // rediriger vers la Dashboard
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Eleve modifié avec succès');");
            out.println("location='EleveServlet';");
            out.println("</script>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
