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
                'signup': 'signupAction',
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
                page_body.show('login');
            },

            signupAction: function () {
                page_body.show('signup');
            }
        });

        return new Router();
    });
