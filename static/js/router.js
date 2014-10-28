define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
    'views/scoreboard',
    'views/manager'
    ],
    function(
        Backbone,
        main,
        game,
        login,
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
        }
    });

    return new Router();
});