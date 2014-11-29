define([
        'backbone',
        'views/page-top',
        'views/page-body'
    ],
    function(
        Backbone,
        page_top,
        page_body
    )
{
    var Router = Backbone.Router.extend({
        initialize: function() {
	},
        routes: {
            'scoreboard': 'scoreboardAction',
            'game': 'gameAction',
            'login': 'loginAction',
            'singup': 'signupAction',
            '*default': 'menuActions'
        },
        menuActions: function () {
            page_body.show('menu');
        },
        scoreboardAction: function () {
        },
        gameAction: function () {
        },
        loginAction: function () {            
        },
        signupAction: function () {
        }
    });

    return new Router();
});