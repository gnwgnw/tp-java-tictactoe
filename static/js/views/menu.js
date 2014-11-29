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

            initialize: function () {
                this.listenTo(this.model, "change", this.render);

                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model));
                return this;
            },

            show: function() {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            }
        });

        return new View();
    });