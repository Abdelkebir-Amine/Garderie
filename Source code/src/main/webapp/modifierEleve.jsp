<%@ page import="com.garderie.model.Eleve" %>
<%@ page import="java.util.List" %>
<%@ page import="static java.awt.SystemColor.window" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modification d'élève</title>
</head>
<body>
    <h2>Modification d'élève</h2>
    <form action="EleveServlet" method="GET">
        <label>Rechercher un élève :</label>
        <input type="text" id="search" name="search">
        <button type="submit">Rechercher</button>
    </form>
    <%
        List<Eleve> elevesTrouves = (List<Eleve>)request.getAttribute("elevesTrouves");
    %>
    <%
        if (elevesTrouves != null){
    %>
    <br>
    <label>Liste des élèves trouvées :</label>
    <br>
    <form method="GET" action="EleveServlet">
        <select id="mySelect" name="mySelectOption">
            <option value="default" selected>-- Sélectionner un élève --</option>
            <%
                if(elevesTrouves != null) {
                    for(Eleve eleve : elevesTrouves) {
            %>
            <option value="<%= eleve.getId() %>"> <%= eleve.getPrenom() %> <%= eleve.getNom() %> </option>
            <%
                    }
                }
            %>
        </select>
        <input type="submit" value="Modifier l'élève">
    </form>
    <%
        }
    %>
    <%
        Eleve eleveToModifier = (Eleve)request.getAttribute("eleveToModifier");
    %>
    <%
        if (eleveToModifier != null){
    %>
            <form method="POST" action="EleveServlet" enctype="multipart/form-data">
                <input type="hidden" name="_method" value="PUT">
                <label>ID:</label>
                <input type="text" name="id" value="<%= eleveToModifier.getId() %>"readonly><br>

                <label>Nom:</label>
                <input type="text" name="nom" value="<%= eleveToModifier.getPrenom() %>" required><br>

                <label>Prénom:</label>
                <input type="text" name="prenom" value="<%= eleveToModifier.getNom() %>" required><br>

                <label>Niveau scolaire:</label>
                <input type="text" name="niveau_scolaire" value="<%= eleveToModifier.getNiveau_scolaire() %>" required><br>

                <label>Date de naissance:</label>
                <input type="date" name="dateNaissance" value="<%= eleveToModifier.getDate_naissance() %>" required><br>

                <label>Prénom de Père:</label>
                <input type="text" name="pere_prenom" value="<%= eleveToModifier.getPere_prenom() %>" required><br>

                <label>Prénom de grand père:</label>
                <input type="text" name="grand_pere_prenom" value="<%= eleveToModifier.getGrand_pere_prenom() %>" required><br>

                <label>N° CIN de père :</label>
                <input type="text" name="pere_cin" value="<%= eleveToModifier.getPere_cin() %>" required><br>

                <label>N° TEL de père:</label>
                <input type="text" name="pere_telephone" value="<%= eleveToModifier.getPere_telephone() %>"required><br>

                <label>Adresse:</label>
                <input type="text" name="adresse" value="<%= eleveToModifier.getAdresse() %>" required><br>

                <label>Prénom de mère:</label>
                <input type="text" name="mere_prenom" value="<%= eleveToModifier.getMere_prenom() %>" required><br>

                <label>Nom de mère:</label>
                <input type="text" name="mere_nom" value="<%= eleveToModifier.getMere_nom() %>" required><br>

                <label>Image de profil:</label>
                <input type="file" name="image" accept="image/*" value="<%= eleveToModifier.getImage() %>"><br>

                <br>
                <button type="submit" name="action" value="modifier">Modifier</button>
                <button type="submit" name="action" value="supprimer">Supprimer l'élève</button>
            </form>
    <%
        }
    %>
</body>
</html>
