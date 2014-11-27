define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
    'views/registration',
    'views/scoreboard',
    'views/manager'
    ],
    function(
        Backbone,
        main,
        game,
        login,
        registration,
        scoreboard,
        manager
    )
{
    var Router = Backbone.Router.extend({
        initialize: function() {
	},
        routes: {
            'scoreboard': 'scoreboardAction',
            'game': 'gameAction',
            'login': 'loginAction',
            'registration': 'registrationAction',
            '*default': 'defaultActions'
        },
        defaultActions: function () {            
            main.show();            
        },
        scoreboardAction: function () {            
            scoreboard.show();
        },
        gameAction: function () {            
            game.show()            
        },
        loginAction: function () {            
            login.show();
        },
        registrationAction: function () {               
            registration.show();
        }
    });

    return new Router();
});