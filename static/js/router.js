define([
        'backbone',
        'models/user',
        'views/page-top',
        'views/page-body'
    ],
    function(
        Backbone,
        user,
        page_top,
        page_body
    )
    {
        var Router = Backbone.Router.extend({

            initialize: function () {
                this.listenTo(user, 'signup:ok', this.afterSignup);
                this.listenTo(user, 'login:ok', this.afterLogin);
            },

            routes: {
                'scoreboard': 'scoreboardAction',
                'game': 'gameAction',
                'login': 'loginAction',
                'signup': 'signupAction',
                '*default': 'menuActions'
            },

            menuActions: function () {
                page_body.getView('menu').show();
            },

            scoreboardAction: function () {
                page_body.getView('scoreboard').show();
            },

            gameAction: function () {
                page_body.getView('game').show();
            },

            loginAction: function () {
                page_body.getView('login').show();
            },

            signupAction: function () {
                page_body.getView('signup').show();
            },

            afterSignup: function () {
                this.navigate('login', {trigger: true});
            },

            afterLogin: function () {
                this.navigate('', {trigger: true});
            }
        });

        return new Router();
    });
