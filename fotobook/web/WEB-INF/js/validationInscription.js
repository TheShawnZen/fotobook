$(document).ready(function () {
    $("#signup").validate({
        rules: {
            password: {
                required: true,
                minlength: 3
            },
            confirm: {
                required: true,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            username: {
                required: true
            }
        },
        messages: {
            password: {
                required: "Veuillez saisir votre mot de passe",
                minlength: "Votre mot de passe doit contenir au moins 3 caract\350res"
            },
            confirm: {
                required: "Veuillez confirmer votre mot de passe",
                equalTo: "Veuillez saisir le m\352me mot de passe"
            },
            email: {
                required: "Veuillez saisir un email valide",
                email: "Veuillez saisir un email valide"    
            },
            username: {
                required: "Veuillez saisir un nom d'utilisateur"  
            }
        }

    });
});


