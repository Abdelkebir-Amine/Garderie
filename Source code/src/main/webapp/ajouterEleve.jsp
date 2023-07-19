<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout d'un élève</title>
</head>
<body>
  <h2>Ajout d'un élève</h2>
  <!-- formulaire pour ajouter un nouvel élève -->
  <form method="POST" action="EleveServlet" enctype="multipart/form-data">
    <label>Nom:</label>
    <input type="text" name="nom" required><br>

    <label>Prénom:</label>
    <input type="text" name="prenom" required><br>

    <label>Niveau scolaire:</label>
    <input type="text" name="niveau_scolaire" required><br>

    <label>Date de naissance:</label>
    <input type="date" name="dateNaissance" required><br>

    <label>Prénom de Père:</label>
    <input type="text" name="pere_prenom" required><br>

    <label>Prénom de grand père:</label>
    <input type="text" name="grand_pere_prenom" required><br>

    <label>N° CIN de père :</label>
    <input type="text" name="pere_cin" maxlength="8" pattern="[0-9]+" required><br>

    <label>N° TEL de père:</label>
    <input type="text" name="pere_telephone" maxlength="8" pattern="[0-9]+" required><br>

    <label>Adresse:</label>
    <input type="text" name="adresse" required><br>

    <label>Prénom de mère:</label>
    <input type="text" name="mere_prenom" required><br>

    <label>Nom de mère:</label>
    <input type="text" name="mere_nom" required><br>

    <label>Image de profil:</label>
    <input type="file" name="image" accept="image/*"><br>

    <input type="submit" value="Enregistrer">
    <input type="reset" value="Effacer">
  </form>
</body>
</html>
