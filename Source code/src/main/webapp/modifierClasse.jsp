<%@ page import="com.garderie.model.Classe" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modification de classe</title>
</head>
<body>
  <h2>Modification de classe</h2>
  <form action="ClasseServlet" method="GET">
    <label>Rechercher un classe :</label>
    <input type="text" id="search" name="search">
    <button type="submit">Rechercher</button>
  </form>
  <%
    List<Classe> classesTrouves = (List<Classe>)request.getAttribute("classesTrouves");
  %>
  <%
    if (classesTrouves != null){
  %>
  <br>
  <label>Liste des classes trouvées :</label>
  <br>
  <form method="GET" action="ClasseServlet">
    <select id="mySelect" name="mySelectOption">
      <option value="default" selected>-- Sélectionner un classe --</option>
      <%
        if(classesTrouves != null) {
          for(Classe classe : classesTrouves) {
      %>
      <option value="<%= classe.getId() %>"> <%= classe.getNom() %> </option>
      <%
          }
        }
      %>
    </select>
    <input type="submit" value="Modifier la classe">
  </form>
  <%
    }
  %>
  <%
    Classe classeToModifier = (Classe) request.getAttribute("classeToModifier");
  %>
  <%
    if (classeToModifier != null){
  %>
  <form method="POST" action="ClasseServlet" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="PUT">
    <label>ID:</label>
    <input type="text" name="id" value="<%= classeToModifier.getId() %>"readonly><br>

    <label>Nom:</label>
    <input type="text" name="nom" value="<%= classeToModifier.getNom() %>" required><br>

    <br>
    <button type="*" name="action" value="modifier">Modifier</button>
    <button type="*" name="action" value="supprimer">Supprimer la classe</button>
  </form>
  <%
    }
  %>
</body>
</html>
