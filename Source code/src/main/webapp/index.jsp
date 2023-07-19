<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
    <!-- FORMULAIRE DE CONNEXION -->
    <br><br><br><br><br>
    <div class="container">
        <div class="row">
            <div class="offset-md-2 col-lg-5 col-md-7 offset-lg-4 offset-md-3">
                <div class="panel border bg-white" style="width: 282px">
                    <div class="panel-heading" style="margin-left: 70px">
                        <h3 class="pt-3 font-weight-bold">Connexion</h3>
                    </div>
                    <div class="panel-body p-3">
                        <form method="POST" action="LoginServlet">
                            <div class="form-group py-2" >
                                <div class="input-field" >
                                    <span class="far fa-user p-2"></span>
                                    <input type="text" id="cin" name="cin" placeholder="CIN d'utilisateur" maxlength="8" pattern="[0-9]+" required>
                                </div>
                            </div>
                            <div class="form-group py-1 pb-2">
                                <div class="input-field" >
                                    <span class="fas fa-lock px-2"></span>
                                    <input type="password" id="motDePasse" name="motDePasse" placeholder="Mot de passe" required>
                                </div>
                            </div>
                            <div class="btn btn-primary btn-block mt-2" style="width: 227px; margin-left: 10px; background-color: #222222">
                                <button type="submit" style="background-color: #222222; border-color: #222222; color: #f2f2f2 ">Se connecter</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
