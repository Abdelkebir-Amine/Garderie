package com.garderie.controller;

import com.garderie.dao.EleveDaoImpl;
import com.garderie.model.Eleve;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.HttpMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EleveServlet", value = "/EleveServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EleveServlet extends HttpServlet {

    // Appeler EleveDaoImpl pour accéder à la base de données
    private EleveDaoImpl eleveDaoImpl;
    @Override
    public void init() {
        eleveDaoImpl = new EleveDaoImpl();
    }

    // GET pour afficher la liste des élèves
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MotCleToSearch = request.getParameter("search");
        String mySelectOption = request.getParameter("mySelectOption");
        if (MotCleToSearch != null){
            try {
                List<Eleve> elevesTrouves = eleveDaoImpl.rechercherEleves(MotCleToSearch);
                request.setAttribute("elevesTrouves", elevesTrouves);
                RequestDispatcher dispatcher = request.getRequestDispatcher("modifierEleve.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if(mySelectOption != null){
            try {
                Eleve eleveToModifier = eleveDaoImpl.getEleveById(Integer.parseInt(mySelectOption));
                request.setAttribute("eleveToModifier", eleveToModifier);
                RequestDispatcher dispatcher = request.getRequestDispatcher("modifierEleve.jsp");
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
                // Appeler la méthode doPut() pour modifier l'eleve
                doPut(request, response);
            } else if ("supprimer".equals(action)) {
                // Appeler la méthode doDelete() pour supprimer l'eleve'
                doDelete(request, response);
            }
        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            //Récupération des valeurs des champs de formulaire
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissance = request.getParameter("dateNaissance");
            int niveau_scolaire = Integer.parseInt(request.getParameter("niveau_scolaire"));
            String pere_prenom = request.getParameter("pere_prenom");
            String grand_pere_prenom = request.getParameter("grand_pere_prenom");
            String pere_cin = request.getParameter("pere_cin");
            String pere_telephone = request.getParameter("pere_telephone");
            String adresse = request.getParameter("adresse");
            String mere_prenom = request.getParameter("mere_prenom");
            String mere_nom = request.getParameter("mere_nom");
            //---Debut traitement champ image de profil
            Part imagePart = request.getPart("image");
            InputStream inputStream = imagePart.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] image = outputStream.toByteArray();
            //---Fin

            // Créer un nouvel objet Eleve avec ces valeurs
            Eleve eleve = new Eleve(1,nom,prenom,pere_prenom,grand_pere_prenom,mere_nom,
                    mere_prenom,pere_cin,pere_telephone,dateNaissance,adresse,image,niveau_scolaire);

            // Ajout d'élève à la base de données
            try {
                boolean rowInserted = eleveDaoImpl.ajouterEleve(eleve);
                if(rowInserted){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Eleve ajouté avec succès');");
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

    // PUT pour modifier un élève
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            // récupérer les paramètres de la request
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissance = request.getParameter("dateNaissance");
            int niveau_scolaire = Integer.parseInt(request.getParameter("niveau_scolaire"));
            String pere_prenom = request.getParameter("pere_prenom");
            String grand_pere_prenom = request.getParameter("grand_pere_prenom");
            String pere_cin = request.getParameter("pere_cin");
            String pere_telephone = request.getParameter("pere_telephone");
            String adresse = request.getParameter("adresse");
            String mere_prenom = request.getParameter("mere_prenom");
            String mere_nom = request.getParameter("mere_nom");
            //---Debut traitement champ image de profil
            Part imagePart = request.getPart("image");
            InputStream inputStream = imagePart.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] image = outputStream.toByteArray();
            //---Fin

            // créer un objet Eleve modifié
            Eleve eleve = new Eleve(id,nom,prenom,pere_prenom,grand_pere_prenom,mere_nom,
                    mere_prenom,pere_cin,pere_telephone,dateNaissance,adresse,image,niveau_scolaire);

            // mettre à jour l'élève dans la base de données
            eleveDaoImpl.modifierEleve(eleve);

            // rediriger vers la liste des élèves
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Eleve modifié avec succès');");
            out.println("location='EleveServlet';");
            out.println("</script>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE pour supprimer un élève
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // récupérer l'ID de l'élève à supprimer
            int id = Integer.parseInt(request.getParameter("id"));

            // supprimer l'élève de la base de données
            eleveDaoImpl.supprimerEleve(id);

            // rediriger vers la liste des élèves
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Eleve supprimé avec succès');");
            out.println("location='EleveServlet';");
            out.println("</script>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
