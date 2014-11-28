/**
 * Created by gexogen on 27.11.14.
 */
define([
        'backbone',
        'models/user',
        'tmpl/page-main'
    ],
    function(
        Backbone,
        user,
        tmpl
        )
    {
        var View = Backbone.View.extend({

            el: "#page-main",
            template: tmpl,
            model: user,

            events: {
            },

            initialize: function () {
                this.listenTo(this.model, "change", this.render);
                
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model));
                return this;
            }
        });

        return new View();
    });