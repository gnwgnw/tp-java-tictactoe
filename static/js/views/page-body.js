/**
 * Created by gexogen on 27.11.14.
 */
define([
        'backbone',
        'models/user',
        'tmpl/page-body',
        'views/menu'
    ],
    function(
        Backbone,
        user,
        tmpl,
        menu
        )
    {
        var View = Backbone.View.extend({

            el: "#page-main",
            template: tmpl,
            model: user,

            views: {
                "menu": menu
            },

            events: {
            },

            initialize: function () {
                this.listenTo(this.model, "change", this.render);
                
                this.render();
            },

            render: function () {
                this.$el.html(this.template(this.model));
                return this;
            },

            show: function (viewName) {
                _.each(this.views, function(view, name){
                    if (name == viewName) {
                        view.show();
                    }
                    else {
                        view.hide();
                    }
                });
            }
        });

        return new View();
    });