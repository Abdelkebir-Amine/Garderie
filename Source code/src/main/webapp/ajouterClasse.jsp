<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout d'un Classe</title>
</head>
<body>
<h2>Ajout d'un classe</h2>
    <!-- formulaire pour ajouter un nouvel classe -->
    <form method="POST" action="ClasseServlet">
        <label>Nom:</label>
        <input type="text" name="nomClasse" required><br>

        <input type="submit" value="Enregistrer">
        <input type="reset" value="Effacer">
    </form>
</body>
</html>
