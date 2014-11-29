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

            el: "#page-body",
            template: tmpl,
            model: user,

            views: {
                "menu": menu
            },

            initialize: function () {
                this.render();
            },

            render: function () {
                this.$el.html(this.template());
                var that = this;
                _.each(this.views, function (view) {
                    that.$el.find('#app-views').append(view.$el.hide());
                });
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