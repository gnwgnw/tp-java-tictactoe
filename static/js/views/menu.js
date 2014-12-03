/**
 * Created by gexogen on 29.11.14.
 */
define([
        'backbone',
        'models/user',
        'tmpl/menu'
    ],
    function(
        Backbone,
        user,
        tmpl
    )
    {
        var View = Backbone.View.extend({

            id: "menu",
            template: tmpl,
            model: user,

            events: {
                "submit #logout-form": "logout"
            },

            initialize: function () {
                this.listenTo(this.model, "change", this.render);
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model.toJSON()));
                return this;
            },

            show: function() {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            },

            logout: function (event) {
                event.preventDefault();
                this.model.logout();
            }
        });

        return new View();
    });