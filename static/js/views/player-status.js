/**
 * Created by gexogen on 07.12.14.
 */
define([
        'backbone',
        'tmpl/player-status'
    ],
    function(
        Backbone,
        tmpl
    )
    {
        var View = Backbone.View.extend({

            template: tmpl,

            events:{
            },

            initialize: function () {
                this.listenTo(this.model, "change", this.render);
                this.hide();
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model.toJSON()));
                return this;
            },

            show: function () {
                this.$el.show();
            },

            hide: function () {
                this.$el.hide();
            }
        });

        return View;
    });
