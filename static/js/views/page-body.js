/**
 * Created by gexogen on 27.11.14.
 */
define([
        'backbone',
        'models/user',
        'tmpl/page-body',
        'views/menu',
        'views/login',
        'views/signup',
        'views/scoreboard',
        'views/game'
    ],
    function(
        Backbone,
        user,
        tmpl,
        menu,
        login,
        signup,
        scoreboard,
        game
    )
    {
        var View = Backbone.View.extend({

            el: "#page-body",
            template: tmpl,
            model: user,

            views: {
                "menu": menu,
                "login": login,
                "signup": signup,
                "scoreboard": scoreboard,
                "game": game
            },

            initialize: function () {
                _.each(this.views, function (view) {
                    this.listenTo(view, 'show', this.show);
                }, this);

                this.render();
            },

            render: function () {
                this.$el.html(this.template());

                _.each(this.views, function (view) {
                    this.$el.find('#app-views').append(view.$el.hide());
                }, this);

                return this;
            },

            show: function (viewName) {
                _.each(this.views, function(view, name){
                    if (name != viewName) {
                        view.hide();
                    }
                });
            },

            getView: function (viewName) {
                return this.views[viewName];
            }
        });

        return new View();
    });