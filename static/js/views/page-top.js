/**
 * Created by gexogen on 26.11.14.
 */
define([
        'backbone',
        'tmpl/page-top',
        'models/user'
    ],
    function(
        Backbone,
        tmpl,
        user
        )
    {
        var View = Backbone.View.extend({

            el: "#page-top",
            template: tmpl,
            model: user,

            events: {
                "click #login": "login",
                "click #logout": "logout"
            },

            initialize: function () {
                this.listenTo(this.model, "change", this.render);

                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model));
                return this;
            },

            login: function () {
                this.model.login();
            },

            logout: function () {
                this.model.logout();
            }

        });

        return new View();
    });