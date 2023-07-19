<%@ page import="com.garderie.dao.EleveDaoImpl" %>
<%@ page import="com.garderie.model.Eleve" %>
<%@ page import="java.util.List" %>
<%@ page import="com.garderie.dao.ClasseDaoImpl" %>
<%@ page import="com.garderie.model.Classe" %>
<%@ page import="com.garderie.dao.EmployeDaoImpl" %>
<%@ page import="com.garderie.model.Employe" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Dashboard Admin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

    <!-- NAVIGATION BAR -->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="dashboardAdmin.jsp">Garderie Name</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="dashboardAdmin.jsp">Home</a></li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Les élèves<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="ajouterEleve.jsp">Ajouter</a></li>
                        <li><a href="modifierEleve.jsp">Modifier/Supprimer</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Les employés<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Ajouter</a></li>
                        <li><a href="#">Modifier/Supprimer</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Les classes<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="ajouterClasse.jsp">Ajouter</a></li>
                        <li><a href="modifierClasse.jsp">Modifier/Supprimer</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Les activités<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Ajouter</a></li>
                        <li><a href="#">Modifier/Supprimer</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a>
                        <span class="glyphicon glyphicon-user"></span>
                            <%
                                HttpSession jspSession = request.getSession();
                                String cinUser="";
                                if((String)request.getAttribute("cin") != null){
                                    cinUser = (String)request.getAttribute("cin");

                                    // Enregistrement d'une valeur dans la session
                                    jspSession.setAttribute("cinSession", cinUser);

                                }else if ((String) jspSession.getAttribute("cinSession") != null){
                                    cinUser = (String) jspSession.getAttribute("cinSession");
                                }
                                // Récupération nom d'utilisateur
                                EmployeDaoImpl employeDaoImpl = new EmployeDaoImpl();
                                Employe employe = employeDaoImpl.getEmployeByCin(cinUser);
                            %>
                            <%= employe.getPrenom() %> <%= employe.getNom() %>
                    </a>
                </li>
                <li><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Se déconnecter </a></li>
            </ul>
        </div>
    </nav>

    <!-- LISTES DES ELEVES -->
    <br><br>
    <div class="container">
        <div class="row">
            <div class="offset-md-2 col-lg-5 col-md-7 offset-lg-4 offset-md-3">
                <div class="panel border bg-white" style="width: 1100px">
                    <div class="panel-heading">
                        <h3 class="pt-3 font-weight-bold">Liste des èleves</h3>
                        <div class="panel-body p-3">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Prénom</th>
                                    <th scope="col">Nom</th>
                                    <th scope="col">Date-Naiss</th>
                                    <th scope="col">Niveau scolaire</th>
                                    <th scope="col">Adresse</th>
                                    <th scope="col">N° Tel père</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%
                                    // Récupération de la liste des élèves depuis La DB
                                    EleveDaoImpl eleveDaoImpl = new EleveDaoImpl();
                                    List<Eleve> eleves = eleveDaoImpl.getTousLesEleves();
                                    if(eleves != null) {
                                        for(Eleve eleve : eleves) {
                                    %>
                                            <tr>
                                                <th scope="row"><%= eleve.getId() %></th>
                                                <td><%= eleve.getPrenom() %> Ben <%= eleve.getPere_prenom() %> </td>
                                                <td><%= eleve.getNom() %></td>
                                                <td><%= eleve.getDate_naissance() %></td>
                                                <td>
                                                    <%
                                                        // Récupération du nom de classe
                                                        ClasseDaoImpl classeDaoImpl = new ClasseDaoImpl();
                                                        Classe classe = classeDaoImpl.getClasseById(eleve.getNiveau_scolaire());
                                                    %>
                                                    <%=
                                                        classe.getNom()
                                                    %>
                                                </td>
                                                <td><%= eleve.getAdresse() %></td>
                                                <td><%= eleve.getPere_telephone() %></td>
                                            </tr>
                                    <%
                                        }
                                    }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- LISTES DES EMPLOYES -->
    <div class="container">
        <div class="row">
            <div class="offset-md-2 col-lg-5 col-md-7 offset-lg-4 offset-md-3">
                <div class="panel border bg-white" style="width: 1100px">
                    <div class="panel-heading">
                        <h3 class="pt-3 font-weight-bold">Liste des employés</h3>
                        <div class="panel-body p-3">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Prénom</th>
                                    <th scope="col">Nom</th>
                                    <th scope="col">Date-Naiss</th>
                                    <th scope="col">Adresse</th>
                                    <th scope="col">N° Tel</th>
                                    <th scope="col">Poste</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    // Récupération de la liste des employes depuis La DB
                                    List<Employe> employes = employeDaoImpl.getTousLesEmployes();
                                    if(employes != null) {
                                        for(Employe employeForList : employes) {
                                %>
                                            <tr>
                                                <th scope="row"><%= employeForList.getCin() %></th>
                                                <td><%= employeForList.getPrenom() %> Ben <%= employeForList.getPere_prenom() %> </td>
                                                <td><%= employeForList.getNom() %></td>
                                                <td><%= employeForList.getDate_naissance() %></td>
                                                <td><%= employeForList.getAdresse() %></td>
                                                <td><%= employeForList.getTelephone() %></td>
                                                <%
                                                    if(employeForList.getIs_directeur()){
                                                %>
                                                        <td>Directeur</td>
                                                <%
                                                    }else{
                                                %>
                                                        <td>Animateur</td>
                                                <%
                                                    }
                                        }
                                    }
                                                %>
                                            </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- LISTES DES CLASSES -->
    <br><br>
    <div class="container">
        <div class="row">
            <div class="offset-md-2 col-lg-5 col-md-7 offset-lg-4 offset-md-3">
                <div class="panel border bg-white" style="width: 400px">
                    <div class="panel-heading">
                        <h3 class="pt-3 font-weight-bold">Liste des classes</h3>
                        <div class="panel-body p-3">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nom</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    // Récupération de la liste des classes depuis La DB
                                    ClasseDaoImpl classeDaoImpl = new ClasseDaoImpl();
                                    List<Classe> classes = classeDaoImpl.getTousLesClasses();
                                    if(classes != null) {
                                        for(Classe classe : classes) {
                                %>
                                <tr>
                                    <th scope="row"><%= classe.getId() %></th>
                                    <td><%= classe.getNom() %></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
