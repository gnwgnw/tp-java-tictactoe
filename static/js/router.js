define([
        'backbone',
        'views/page-top',
        'views/page-main'
    ],
    function(
        Backbone,
        page_top,
        page_main
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
        },
        scoreboardAction: function () {
        },
        gameAction: function () {
        },
        loginAction: function () {            
        },
        registrationAction: function () {
        }
    });

    return new Router();
});